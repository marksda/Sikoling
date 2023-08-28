package org.Sikoling.ejb.main.integrator.onlyoffice.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.helpers.FileUtility;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.utils.ConvertErrorType;
import org.Sikoling.ejb.main.integrator.onlyoffice.OnlyOfficeTokenManager;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.stream.JsonParser;
import jakarta.servlet.http.HttpServletResponse;

import static org.Sikoling.ejb.abstraction.entity.onlyoffice.utils.Constants.CONVERT_TIMEOUT_MS;
import static org.Sikoling.ejb.abstraction.entity.onlyoffice.utils.Constants.MAX_KEY_LENGTH;
import static org.Sikoling.ejb.abstraction.entity.onlyoffice.utils.Constants.CONVERTATION_ERROR_MESSAGE_TEMPLATE;
import static org.Sikoling.ejb.abstraction.entity.onlyoffice.utils.Constants.FULL_LOADING_IN_PERCENT;

public class ServiceConverter {

	private final int convertTimeout;
	private final String DOCUMENT_CONVERTER_URL;
    private final String DOCUMENT_JWT_HEADER;
    private final OnlyOfficeTokenManager tokenManager;
	
	public ServiceConverter(Properties properties, OnlyOfficeTokenManager tokenManager) {
		int timeout = Integer.parseInt(properties.getProperty("TIMEOUT_DOC_SERVICES"));
		this.convertTimeout = timeout > 0 ? timeout : CONVERT_TIMEOUT_MS;
		this.DOCUMENT_CONVERTER_URL = properties.getProperty("URL_DOC_SERVICE_CONVERTER");
		this.DOCUMENT_JWT_HEADER = properties.getProperty("DOC_SERVICES_HEADER");
		this.tokenManager = tokenManager;
	}

	public String getDOCUMENT_CONVERTER_URL() {
		return DOCUMENT_CONVERTER_URL;
	}

	public String getDOCUMENT_JWT_HEADER() {
		return DOCUMENT_JWT_HEADER;
	}

	public int getConvertTimeout() {
		return convertTimeout;
	}
	
	// get the url of the converted file
    public Map<String, String> getConvertedData(String documentUri, String fromExtension, 
    		String toExtension, String documentRevisionId, String filePass, Boolean isAsync, String lang) throws Exception {
        // check if the fromExtension parameter is defined; if not, get it from the document url
        String fromExt = fromExtension == null || fromExtension.isEmpty() ? FileUtility.getFileExtension(documentUri) : fromExtension;
        // check if the file name parameter is defined; if not, get random uuid for this file
        String title = FileUtility.getFileName(documentUri);
        title = title == null || title.isEmpty() ? UUID.randomUUID().toString() : title;
        String documentRevId = documentRevisionId == null || documentRevisionId.isEmpty() ? documentUri : documentRevisionId;
        documentRevId = generateRevisionId(documentRevId);  // create document token
        // write all the necessary parameters to the body object
        ConvertBody body = new ConvertBody();
        body.setRegion(lang);
        body.setUrl(documentUri);
        body.setOutputtype(toExtension.replace(".", ""));
        body.setFiletype(fromExt.replace(".", ""));
        body.setTitle(title);
        body.setKey(documentRevId);
        body.setPassword(filePass);
        if (isAsync) {
            body.setAsync(true);
        }

        String headerToken = "";
        if (tokenManager.tokenEnabled() && tokenManager.tokenUseForRequest()) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("region", lang);
            map.put("url", body.getUrl());
            map.put("outputtype", body.getOutputtype());
            map.put("filetype", body.getFiletype());
            map.put("title", body.getTitle());
            map.put("key", body.getKey());
            map.put("password", body.getPassword());
            if (isAsync) {
                map.put("async", body.getAsync());
            }

            // add token to the body if it is enabled
            String token = tokenManager.createToken(map);
            body.setToken(token);

            Map<String, Object> payloadMap = new HashMap<String, Object>();
            payloadMap.put("payload", map);  // create payload object
            headerToken = tokenManager.createToken(payloadMap);  // create header token
        }

        Jsonb jsonb = JsonbBuilder.create();
        String bodyString = jsonb.toJson(body);

        byte[] bodyByte = bodyString.getBytes(StandardCharsets.UTF_8);

        // specify request parameters
        URL url = new URL(DOCUMENT_CONVERTER_URL);
        java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setFixedLengthStreamingMode(bodyByte.length);
        connection.setRequestProperty("Accept", "application/json");
        connection.setConnectTimeout(convertTimeout);

        // write header token to the request
        if (tokenManager.tokenEnabled()) {
            connection.setRequestProperty(DOCUMENT_JWT_HEADER.equals("") ? "Authorization" : DOCUMENT_JWT_HEADER, "Bearer " + headerToken);
        }

        connection.connect();


        try (OutputStream os = connection.getOutputStream()) {
            os.write(bodyByte);
        }
        int statusCode = connection.getResponseCode();
        if (statusCode != HttpServletResponse.SC_OK) {  // checking status code
            connection.disconnect();
            throw new Exception("Conversion service returned status: " + statusCode);
        }

        InputStream stream = connection.getInputStream();

        if (stream == null) {
            throw new Exception("Could not get an answer");
        }

        // convert string to json
        String jsonString = convertStreamToString(stream);

        connection.disconnect();

        return getResponseData(jsonString);
    }
    
    // generate document key
    public String generateRevisionId(String expectedKey) {
    	String formatKey = expectedKey.length() > MAX_KEY_LENGTH ? Integer.toString(expectedKey.hashCode()) : expectedKey;
        String key = formatKey.replace("[^0-9-.a-zA-Z_=]", "_");

        return key.substring(0, Math.min(key.length(), MAX_KEY_LENGTH));  // the resulting key length is 20 or less
    }

    // create an error message for an error code
    private void processConvertServiceResponceError(int errorCode) throws Exception {
        String errorMessage = CONVERTATION_ERROR_MESSAGE_TEMPLATE + ConvertErrorType.labelOfCode(errorCode);

        throw new Exception(errorMessage);
    }

    // get the response data
    private Map<String, String> getResponseData(String jsonString) throws Exception {
    	Reader reader = new StringReader(jsonString);
    	JsonParser parser = Json.createParser(reader);
    	JsonObject jsonObj = parser.getObject();
    	
//        JSONObject jsonObj = convertStringToJSON(jsonString);

        Object error = jsonObj.get("error");
        if (error != null) {  // if an error occurs
            processConvertServiceResponceError(Math.toIntExact((long) error));  // then get an error message
        }

        // check if the conversion is completed and save the result to a variable
        Boolean isEndConvert = jsonObj.getBoolean("endConvert");

        Long resultPercent = 0L;
        String responseUri = null;
        String responseFileType = null;
        Map<String, String> responseData = new HashMap<>();

        if (isEndConvert) {  // if the conversion is completed
            resultPercent = FULL_LOADING_IN_PERCENT;
            responseUri = jsonObj.getString("fileUrl");  // get the file url
            responseFileType = jsonObj.getString("fileType");  // get the file type
            responseData.put("fileUrl", responseUri);
            responseData.put("fileType", responseFileType);
        } else {  // if the conversion isn't completed
            resultPercent = jsonObj.getJsonNumber("percent").longValue();
            responseData.put("fileUrl", "");
            resultPercent = resultPercent >= FULL_LOADING_IN_PERCENT ? FULL_LOADING_IN_PERCENT - 1 : resultPercent;  // get the percentage value
        }

        return responseData;
    }    
    

    // convert stream to string
    public String convertStreamToString(InputStream stream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(stream);  // create an object to get incoming stream
        StringBuilder stringBuilder = new StringBuilder();  // create a string builder object

        // create an object to read incoming streams
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = bufferedReader.readLine();  // get incoming streams by lines

        while (line != null) {
            stringBuilder.append(line);  // concatenate strings using the string builder
            line = bufferedReader.readLine();
        }

        String result = stringBuilder.toString();

        return result;
    }
	
    // convert string to json
    public JsonObject convertStringToJSON(String jsonString) throws IllegalStateException {
        Reader reader = new StringReader(jsonString);
    	JsonParser parser = Json.createParser(reader);
    	JsonObject jsonObj = parser.getObject();       

        return jsonObj;
    }
    
	public class ConvertBody {
        private String region;
        private String url;
        private String outputtype;
        private String filetype;
        private String title;
        private String key;
        private Boolean async;
        private String token;
        private String password;

        public String getRegion() {
            return region;
        }
        
        public void setRegion(String regionParam) {
            this.region = regionParam;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String urlParam) {
            this.url = urlParam;
        }

        public String getOutputtype() {
            return outputtype;
        }

        public void setOutputtype(String outputtypeParam) {
            this.outputtype = outputtypeParam;
        }

        public String getFiletype() {
            return filetype;
        }

        public void setFiletype(String filetypeParam) {
            this.filetype = filetypeParam;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String titleParam) {
            this.title = titleParam;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String keyParam) {
            this.key = keyParam;
        }

        public Boolean getAsync() {
            return async;
        }

        public void setAsync(Boolean asyncParam) {
            this.async = asyncParam;
        }

        public String getToken() {
            return token;
        }
        
        public void setToken(String tokenParam) {
            this.token = tokenParam;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String passwordParam) {
            this.password = passwordParam;
        }
    }
	
}
