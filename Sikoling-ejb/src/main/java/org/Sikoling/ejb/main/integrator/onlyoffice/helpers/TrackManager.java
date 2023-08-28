package org.Sikoling.ejb.main.integrator.onlyoffice.helpers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.RequestBodyPost;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.helpers.DocumentManager;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.helpers.FileUtility;
import org.Sikoling.ejb.main.integrator.onlyoffice.OnlyOfficeTokenManager;

import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.http.HttpServletResponse;

import static org.Sikoling.ejb.abstraction.entity.onlyoffice.utils.Constants.FILE_SAVE_TIMEOUT;
import static org.Sikoling.ejb.abstraction.entity.onlyoffice.utils.Constants.BUFFER_SIZE;
import static org.Sikoling.ejb.abstraction.entity.onlyoffice.utils.Constants.KILOBYTE_SIZE;

public class TrackManager {

	private final String DOCUMENT_JWT_HEADER;
	private final String documentCommandUrl;
	private final DocumentManager documentManager;
	private final ServiceConverter serviceConverter;
	private final OnlyOfficeTokenManager tokenManager;
	
	public TrackManager(Properties properties, DocumentManager documentManager, ServiceConverter serviceConverter, OnlyOfficeTokenManager tokenManager) {
		this.DOCUMENT_JWT_HEADER = properties.getProperty("DOC_SERVICES_HEADER");
		this.documentCommandUrl = properties.getProperty("URL_DOC_SERVICE_COMMAND_INTERNAL");
		this.documentManager = documentManager;
		this.serviceConverter = serviceConverter;
		this.tokenManager = tokenManager;
	}
	
	public String getDOCUMENT_JWT_HEADER() {
		return DOCUMENT_JWT_HEADER;
	}
	
	public String getDocumentCommandUrl() {
		return documentCommandUrl;
	}

	public DocumentManager getDocumentManager() {
		return documentManager;
	}

	public ServiceConverter getServiceConverter() {
		return serviceConverter;
	}
		
	// file force saving process
    public void processForceSave(RequestBodyPost body,
                                        String fileNameParam,
                                        String userAddress) throws Exception {
        if (body.getUrl() == null) {
            throw new Exception("DownloadUrl is null");
        }
        String fileName = fileNameParam;
        String downloadUri = body.getUrl();

        String curExt = FileUtility.getFileExtension(fileName);  // get current file extension
        String downloadExt = "." + body.getFiletype();  // get the extension of the downloaded file

        Boolean newFileName = false;

        // convert downloaded file to the file with the current extension if these extensions aren't equal
        if (!curExt.equals(downloadExt)) {
            try {
                String newFileUri = serviceConverter
                		.getConvertedData(
	                		downloadUri, downloadExt, curExt,
	                		serviceConverter.generateRevisionId(downloadUri), 
	                		null, false, null)
                		.get("fileUrl");  // convert file and get url to a new file
                
                if (newFileUri.isEmpty()) {
                    newFileName = true;
                } 
                else {
                    downloadUri = newFileUri;
                }
            } catch (Exception e) {
                newFileName = true;
            }
        }

        byte[] byteArrayFile = getDownloadFile(downloadUri);  // download document file
        String forcesavePath = "";
        boolean isSubmitForm = body.getForcesavetype() == 3 ? true:false;  // SubmitForm

        if (isSubmitForm) {  // if the form is submitted
            // new file
            if (newFileName) {
                fileName = documentManager.getCorrectName(FileUtility.getFileNameWithoutExtension(fileName)
                        + "-form" + downloadExt, userAddress);  // get the correct file name if it already exists
            } else {
                fileName = documentManager.getCorrectName(FileUtility.getFileNameWithoutExtension(fileName)
                        + "-form" + curExt, userAddress);
            }
            forcesavePath = documentManager.storagePath(fileName, userAddress);
        } else {
            if (newFileName) {
                fileName = documentManager.getCorrectName(FileUtility
                        .getFileNameWithoutExtension(fileName) + downloadExt, userAddress);
            }

            // create forcesave path if it doesn't exist
            forcesavePath = documentManager.forcesavePath(fileName, userAddress, false);
            if (forcesavePath == "") {
                forcesavePath = documentManager.forcesavePath(fileName, userAddress, true);
            }
        }

        saveFile(byteArrayFile, Paths.get(forcesavePath));

        if (isSubmitForm) {
            String user = body.getActions().get(0).getUserid();   // get the user id

            // create meta data for forcesaved file
            documentManager.createMeta(fileName, user, "Filling Form", userAddress);
        }
    }
    
    // create a new file if it does not exist
    private boolean createFile(byte[] byteArray, Path path) {
        if (Files.exists(path)) {
            return true;
        }
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray)) {
            File file = Files.createFile(path).toFile();  // create a new file in the specified path
            try (FileOutputStream out = new FileOutputStream(file)) {
                int read;
                final byte[] bytes = new byte[KILOBYTE_SIZE];
                while ((read = byteArrayInputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);  // write bytes to the output stream
                }
                out.flush();  // force write data to the output stream that can be cached in the current thread
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // get byte array from stream
    private byte[] getAllBytes(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        for (int len = is.read(buffer); len != -1; len = is.read(buffer)) {
            os.write(buffer, 0, len);
        }
        return os.toByteArray();
    }
    
    // save file
    private boolean saveFile(byte[] byteArray, Path path) {
        if (path == null) {
            throw new RuntimeException("Path argument is not specified");  // file isn't specified
        }
        if (!Files.exists(path)) { // if the specified file does not exist
            return createFile(byteArray, path);  // create it in the specified directory
        } else {
            try {
                Files.write(path, byteArray);  // otherwise, write new information in the bytes format to the file
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    // download file from url
    private byte[] getDownloadFile(String url) throws Exception {
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("Url argument is not specified");  // URL isn't specified
        }

        URL uri = new URL(url);
        java.net.HttpURLConnection connection = (java.net.HttpURLConnection) uri.openConnection();
        connection.setConnectTimeout(FILE_SAVE_TIMEOUT);
        InputStream stream = connection.getInputStream();  // get input stream of the file information from the URL

        int statusCode = connection.getResponseCode();

        if (statusCode != HttpServletResponse.SC_OK) {  // checking status code
            connection.disconnect();
            throw new RuntimeException("Document editing service returned status: " + statusCode);
        }

        if (stream == null) {
            connection.disconnect();
            throw new RuntimeException("Input stream is null");
        }

        return getAllBytes(stream);
    }
    
    // create a command request
	public void commandRequest(String method, String key, HashMap<String, Object> meta) throws Exception {
        URL url = new URL(documentCommandUrl);
        java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("c", method);
        params.put("key", key);

        if (meta != null) {
            params.put("meta", meta);
        }

        String headerToken = "";
        // check if a secret key to generate token exists or not
        if (tokenManager.tokenEnabled() && tokenManager.tokenUseForRequest()) {
            Map<String, Object> payloadMap = new HashMap<String, Object>();
            payloadMap.put("payload", params);
            // encode a payload object into a header token
            headerToken = tokenManager.createToken(payloadMap);  
            // add a header Authorization with a header token and Authorization prefix in it
            connection.setRequestProperty(DOCUMENT_JWT_HEADER.equals("") ? "Authorization":DOCUMENT_JWT_HEADER, "Bearer " + headerToken);
            // encode a payload object into a body token
            String token = tokenManager.createToken(params);  
            params.put("token", token);
        }

        Jsonb jsonb = JsonbBuilder.create();
        String bodyString = jsonb.toJson(params);

        byte[] bodyByte = bodyString.getBytes(StandardCharsets.UTF_8);

        connection.setRequestMethod("POST");  // set the request method

        // set the Content-Type header
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true); // set the doOutput field to true

        connection.connect();
        try (OutputStream os = connection.getOutputStream()) {
            os.write(bodyByte);  // write bytes to the output stream
        }
        InputStream stream = connection.getInputStream();  // get input stream

        if (stream == null) {
            throw new Exception("Could not get an answer");
        }

        String jsonString = serviceConverter.convertStreamToString(stream);  // convert stream to json string
        connection.disconnect();

        JsonObject response = serviceConverter.convertStringToJSON(jsonString);  // convert json string to json object
        if (response.getInt("error") != 0) {
            throw new Exception(response.toString());
        }
    }

}
