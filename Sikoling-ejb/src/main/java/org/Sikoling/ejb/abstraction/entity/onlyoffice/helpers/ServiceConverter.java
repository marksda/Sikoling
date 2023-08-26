package org.Sikoling.ejb.abstraction.entity.onlyoffice.helpers;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

import static org.Sikoling.ejb.abstraction.entity.onlyoffice.utils.Constants.CONVERT_TIMEOUT_MS;

public class ServiceConverter implements Serializable {

	private static final long serialVersionUID = 2986517411024135119L;
	private final int convertTimeout;
	
	public ServiceConverter(Properties properties) {
		int timeout = Integer.parseInt(properties.getProperty("TIMEOUT_DOC_SERVICES"));
		this.convertTimeout = timeout > 0 ? timeout : CONVERT_TIMEOUT_MS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getConvertTimeout() {
		return convertTimeout;
	}
	
	// get the url of the converted file
    public Map<String, String> getConvertedData(String documentUri, String fromExtension, String toExtension, String documentRevisionId, String filePass, Boolean isAsync, String lang) throws Exception {
        // check if the fromExtension parameter is defined; if not, get it from the document url
        String fromExt = fromExtension == null || fromExtension.isEmpty() ? FileUtility.getFileExtension(documentUri) : fromExtension;

        // check if the file name parameter is defined; if not, get random uuid for this file
        String title = FileUtility.getFileName(documentUri);
        title = title == null || title.isEmpty() ? UUID.randomUUID().toString() : title;

        String documentRevId = documentRevisionId == null || documentRevisionId.isEmpty()
                ? documentUri : documentRevisionId;

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
        if (DocumentManager.tokenEnabled() && DocumentManager.tokenUseForRequest()) {
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
            String token = DocumentManager.createToken(map);
            body.setToken(token);

            Map<String, Object> payloadMap = new HashMap<String, Object>();
            payloadMap.put("payload", map);  // create payload object
            headerToken = DocumentManager.createToken(payloadMap);  // create header token
        }

        Gson gson = new Gson();
        String bodyString = gson.toJson(body);

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
        if (DocumentManager.tokenEnabled()) {
            connection.setRequestProperty(DOCUMENT_JWT_HEADER.equals("")
                    ? "Authorization" : DOCUMENT_JWT_HEADER, "Bearer " + headerToken);
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

        public void setRegion(final String regionParam) {
            this.region = regionParam;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(final String urlParam) {
            this.url = urlParam;
        }

        public String getOutputtype() {
            return outputtype;
        }

        public void setOutputtype(final String outputtypeParam) {
            this.outputtype = outputtypeParam;
        }

        public String getFiletype() {
            return filetype;
        }

        public void setFiletype(final String filetypeParam) {
            this.filetype = filetypeParam;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(final String titleParam) {
            this.title = titleParam;
        }

        public String getKey() {
            return key;
        }

        public void setKey(final String keyParam) {
            this.key = keyParam;
        }

        public Boolean getAsync() {
            return async;
        }

        public void setAsync(final Boolean asyncParam) {
            this.async = asyncParam;
        }

        public void setToken(final String tokenParam) {
            this.token = tokenParam;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(final String passwordParam) {
            this.password = passwordParam;
        }
    }
	
}
