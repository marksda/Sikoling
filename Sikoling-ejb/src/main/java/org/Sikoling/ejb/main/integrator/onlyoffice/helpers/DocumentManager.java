package org.Sikoling.ejb.main.integrator.onlyoffice.helpers;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.FileType;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.helpers.FileUtility;
import org.apache.commons.io.FilenameUtils;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTClaimsSet.Builder;
import com.nimbusds.jwt.SignedJWT;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import static org.Sikoling.ejb.abstraction.entity.onlyoffice.utils.Constants.MAX_FILE_SIZE;
import static org.Sikoling.ejb.abstraction.entity.onlyoffice.utils.Constants.KILOBYTE_SIZE;

public class DocumentManager implements Serializable {

	private static final long serialVersionUID = -7992490385103047337L;
	private final Properties properties;
	private final ServiceConverter serviceConverter;
	
	public DocumentManager(Properties properties, ServiceConverter serviceConverter) {
		this.properties = properties;
		this.serviceConverter = serviceConverter;
	}
	
	public long getMaxFileSize() {
		long size;

        try {
            size = Long.parseLong(properties.getProperty("MAX_FILESIZE"));
        } catch (Exception ex) {
            size = 0;
        }

        return size > 0 ? size : MAX_FILE_SIZE;
	}

	public List<String> getFileExts() {
        List<String> res = new ArrayList<String>();

        res.addAll(getViewedExts());
        res.addAll(getEditedExts());
        res.addAll(getConvertExts());
        res.addAll(getFillExts());

        return res;
    }
	
	public List<String> getFillExts() {
        String exts = properties.getProperty("FILL_DOCS");
        return Arrays.asList(exts.split("\\|"));
    }
	
	// get file extensions that can be viewed
	public List<String> getViewedExts() {
        String exts = properties.getProperty("VIEWED_DOCS");
        return Arrays.asList(exts.split("\\|"));
    }
	
	// get file extensions that can be edited
	public List<String> getEditedExts() {
        String exts = properties.getProperty("EDITED_DOCS");
        return Arrays.asList(exts.split("\\|"));
    }
	
	// get file extensions that can be converted
	public List<String> getConvertExts() {
        String exts = properties.getProperty("CONVERT_DOCS");
        return Arrays.asList(exts.split("\\|"));
    }
	
	// get current user host address
	public String curUserHostAddress(String userAddress) {
        String userAddr = userAddress;
        if (userAddr == null) {
            try {
                userAddr = InetAddress.getLocalHost().getHostAddress();
            } catch (Exception ex) {
                userAddr = "";
            }
        }

        return userAddr.replaceAll("[^0-9a-zA-Z.=]", "_");
    }
	
	// get the root directory of the user host
	public String filesRootPath(String userAddress) {
        String hostAddress = curUserHostAddress(userAddress);
        String serverPath = ""; //request.getSession().getServletContext().getRealPath("");  
        String storagePath = properties.getProperty("STORAGE_PATH");  
        File f = new File(storagePath);

        if (f.isAbsolute()) {
            if (!f.exists()) {
                if (Files.isWritable(Paths.get(storagePath.substring(0, storagePath.lastIndexOf(File.separator))))) {
                    f.mkdirs();
                } else {
                    throw new SecurityException("No write permission to path: " + f.toPath());
                }
            } 
            else if (f.exists() && f.isFile()) {
                throw new SecurityException("The path to the file is specified instead of the folder");
            }
        }
        
        String directory = !f.isAbsolute() ? serverPath.concat(storagePath).concat(File.separator).concat(hostAddress):storagePath;

        File file = new File(directory);

        // if the root directory doesn't exist
        if (!file.exists()) {
            file.mkdirs();
        }

        return directory;
    }

	// get the storage path of the file
    public String storagePath(String fileName, String userAddress) {
        String directory = filesRootPath(userAddress);
        return directory + fileName;
    }
    
 // get the path to history file
    public String historyPath(String fileName, String userAddress, String version, String file) {
        String hostAddress = curUserHostAddress(userAddress);
        String serverPath = "";  //request.getSession().getServletContext().getRealPath("");
        String storagePath = properties.getProperty("STORAGE_PATH"); 
        String directory = serverPath + storagePath + File.separator + hostAddress + File.separator;
        if (new File(storagePath).isAbsolute()) {
            directory = filesRootPath(userAddress);
        }

        directory = directory + fileName + "-hist" + File.separator + version + File.separator + file;

        return directory;
    }
	
 // get the path to the forcesaved file version
    public String forcesavePath(String fileName, String userAddress, Boolean create) {
        String hostAddress = curUserHostAddress(userAddress);
        String serverPath = "";  //request.getSession().getServletContext().getRealPath("");
        String storagePath = properties.getProperty("STORAGE_PATH");

        // create the directory to this file version
        String directory = serverPath + storagePath + File.separator + hostAddress + File.separator;

        File file = new File(directory);
        if (!file.exists()) {
            return "";
        }

        // create the directory to the history of this file version
        directory = directory + fileName + "-hist" + File.separator;
        file = new File(directory);
        if (!create && !file.exists()) {
            return "";
        }

        file.mkdirs();

        directory = directory + fileName;
        file = new File(directory);
        if (!create && !file.exists()) {
            return "";
        }

        return directory;
    }
    
 // get the history directory
    public String historyDir(String storagePath) {
        return storagePath + "-hist";
    }
    
 // get the path to the file version by the history path and file version
    public String versionDir(String histPath, Integer version) {
        return histPath + File.separator + Integer.toString(version);
    }

    // get the path to the file version by the file name, user address and file version
    public String versionDir(String fileName, String userAddress, Integer version) {
        return versionDir(historyDir(storagePath(fileName, userAddress)), version);
    }
    	
	// get the file version by the history path
    public Integer getFileVersion(String historyPath) {
        File dir = new File(historyPath);

        if (!dir.exists()) {
            return 1;  // if the history path doesn't exist, then the file version is 1
        }

        File[] dirs = dir.listFiles(new FileFilter() {  // take only directories from the history folder
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        return dirs.length + 1;  // count the directories
    }
    
    // get the file version by the file name and user address
    public int getFileVersion(String fileName, String userAddress) {
        return getFileVersion(historyDir(storagePath(fileName, userAddress)));
    }
    
    // get a file name with an index if the file with such a name already exists
    public String getCorrectName(String fileName, String userAddress) {
        String baseName = FileUtility.getFileNameWithoutExtension(fileName);
        String ext = FileUtility.getFileExtension(fileName);
        String name = baseName + ext;

        File file = new File(storagePath(name, userAddress));

        for (int i = 1; file.exists(); i++) {  // run through all the files with such a name in the storage directory
            name = baseName + " (" + i + ")" + ext;  // and add an index to the base name
            file = new File(storagePath(name, userAddress));
        }

        return name;
    }
    
    // create meta information
    public void createMeta(String fileName, String uid, String uname, String userAddress) throws Exception {
        String histDir = historyDir(storagePath(fileName, userAddress));

        File dir = new File(histDir);  // create history directory
        dir.mkdir();

        // create json object and put there file information (creation time, user id and name)
        JsonObject json = Json.createObjectBuilder()
        		.add("created", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
        		.add("id", uid)
        		.add("name", uname)
        		.build();

        // create createdInfo.json file with meta information in the history directory
        File meta = new File(histDir + File.separator + "createdInfo.json");
        try (FileWriter writer = new FileWriter(meta)) {
        	writer.write(json.toString());
        }
    }

    // get all the stored files from the user host address
    public File[] getStoredFiles(String userAddress) {
        String directory = filesRootPath(userAddress);

        File file = new File(directory);
        return file.listFiles(new FileFilter() {  // take only files from the root directory
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
    }
    
    public boolean createFile(Path path, InputStream stream) {
        if (Files.exists(path)) {
            return true;
        }
        try {
            File file = Files.createFile(path).toFile();
            try (FileOutputStream out = new FileOutputStream(file)) {
                int read;
                final byte[] bytes = new byte[KILOBYTE_SIZE];
                while ((read = stream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // get file url
    public String getFileUri(String fileName, Boolean forDocumentServer) {
        try {
            String serverPath = getServerUrl(forDocumentServer);
            String storagePath = properties.getProperty("STORAGE_PATH");
            File f = new File(storagePath);
            String hostAddress = curUserHostAddress(null);

            String filePath = serverPath + "/" + storagePath + "/" + hostAddress + "/"
                    + URLEncoder.encode(fileName, java.nio.charset.StandardCharsets.UTF_8.toString())
                    .replace("+", "%20");
            if (f.isAbsolute() && f.isFile()) {
                filePath = getDownloadUrl(fileName, true);
                if (!Files.isWritable(f.toPath())) {
                    throw new SecurityException("No write permission to path: " + f.toPath());
                }
            }

            return filePath;
        } catch (Exception e) {
            return "";
        }
    }
    
    // get file information
    public ArrayList<Map<String, Object>> getFilesInfo() {
        ArrayList<Map<String, Object>> files = new ArrayList<Map<String, Object>>();

        // run through all the stored files
        for (File file : getStoredFiles(null)) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();  // write all the parameters to the map
            map.put("version", getFileVersion(file.getName(), null));
            map.put("id", serviceConverter.generateRevisionId(curUserHostAddress(null) + "/" + file.getName() + "/"
                            + Long.toString(new File(storagePath(file.getName(), null)).lastModified())));
            map.put("contentLength", new BigDecimal(String.valueOf((file.length() / Double.valueOf(KILOBYTE_SIZE))))
                    .setScale(2, RoundingMode.HALF_UP) + " KB");
            map.put("pureContentLength", file.length());
            map.put("title", file.getName());
            map.put("updated", String.valueOf(new Date(file.lastModified())));
            files.add(map);
        }

        return files;
    }

    // get file information by its id
    public ArrayList<Map<String, Object>> getFilesInfo(String fileId) {
        ArrayList<Map<String, Object>> file = new ArrayList<>();

        for (Map<String, Object> map : getFilesInfo()) {
            if (map.get("id").equals(fileId)) {
                file.add(map);
                break;
            }
        }

        return file;
    }
    
    // get the path url
    public String getPathUri( String path) {
        String serverPath = getServerUrl(true);
        String storagePath = properties.getProperty("STORAGE_PATH");
        String hostAddress = curUserHostAddress(null);

        String filePath = serverPath + "/" + storagePath + "/" + hostAddress + "/"
                + path.replace(File.separator, "/").substring(filesRootPath(null).length())
                .replace(" ", "%20");

        return filePath;
    }
    
    // get the server url
    public String getServerUrl(Boolean forDocumentServer) {
        if (forDocumentServer && !properties.getProperty("STORAGE_SERVER_FOR_ONLYOFFICE_INTERNAL").equals("")) {
            return properties.getProperty("STORAGE_SERVER_FOR_ONLYOFFICE_INTERNAL");
        } else {
            return properties.getProperty("STORAGE_SERVER_FOR_ANYONE");
        }
    }
    
    // get the callback url
    public String getCallback(String fileNameParam) {
        String serverPath = getServerUrl(true);
        String hostAddress = curUserHostAddress(null);
        try {
            String query = "?fileNameParam="
                    + URLEncoder.encode(fileNameParam, java.nio.charset.StandardCharsets.UTF_8.toString())
                    + "&userAddress=" + URLEncoder.encode(hostAddress, java.nio.charset.StandardCharsets.UTF_8.toString());

            return serverPath + "/track" + query;
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
    
    // get url to the created file
    public String getCreateUrl(String fileNameParam) {
        String serverPath = getServerUrl(false);
        String randomFileName =  UUID.randomUUID().toString() + "-cso." + FilenameUtils.getExtension(fileNameParam);
        fileNameParam = FilenameUtils.getFullPath(randomFileName) + randomFileName;
    	return serverPath + "/create?fileNameParam=" + fileNameParam;
    }
    
    // get url to download a file
    public String getDownloadUrl(String fileNameParam, Boolean forDocumentServer) {
        String serverPath = getServerUrl(forDocumentServer);
        try {
        	return serverPath + "/download?fileNameParam=" + URLEncoder.encode(fileNameParam, StandardCharsets.UTF_8.toString());
        }
        catch (UnsupportedEncodingException e) {
        	return "";
		}
    }
    
    // get url to download a file to History prev.*
    public String getDownloadHistoryUrl(String fileName, Integer version, String file, Boolean forDocumentServer) {
        String serverPath = getServerUrl(forDocumentServer);
        String hostAddress = curUserHostAddress(null);
        try {
            String userAddress = forDocumentServer ? "&userAddress=" + URLEncoder
                    .encode(hostAddress, java.nio.charset.StandardCharsets.UTF_8.toString()) : "";
            String query = "?type=downloadhistory&fileName=" + URLEncoder
                    .encode(fileName, java.nio.charset.StandardCharsets.UTF_8.toString()) + userAddress;
            query = query + "&ver=" + version + "&file=" + URLEncoder.
                    encode(file, java.nio.charset.StandardCharsets.UTF_8.toString());

            return serverPath + "/IndexServlet" + query;
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
    
    // get an editor internal extension
    public String getInternalExtension(FileType fileType) {
        // .docx for word file type
        if (fileType.equals(FileType.Word)) {
            return ".docx";
        }

        // .xlsx for cell file type
        if (fileType.equals(FileType.Cell)) {
            return ".xlsx";
        }

        // .pptx for slide file type
        if (fileType.equals(FileType.Slide)) {
            return ".pptx";
        }

        // the default file type is .docx
        return ".docx";
    }

    // get image url for templates
    public String getTemplateImageUrl(FileType fileType) {
        String path = getServerUrl(true) + "/resources?fileNameParam=/css/img/";
        // for word file type
        if (fileType.equals(FileType.Word)) {
            return path + "file_docx.svg";
        }

        // .xlsx for cell file type
        if (fileType.equals(FileType.Cell)) {
            return path + "file_xlsx.svg";
        }

        // .pptx for slide file type
        if (fileType.equals(FileType.Slide)) {
            return path + "file_pptx.svg";
        }

        // the default file type
        return path + "file_docx.svg";
    }
    
    // create document token
    public String createToken(final Map<String, Object> payloadClaims) {
        try {
            JWSSigner signer = new MACSigner(properties.getProperty("SECRET_KEY_DOC"));
            Builder claimsSetBuilder = new JWTClaimsSet.Builder();
            
            for(String key : payloadClaims.keySet()) {
            	claimsSetBuilder.claim(key, payloadClaims.get(key));
            }
            
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSetBuilder.build());
            
            // Apply the HMAC protection
            signedJWT.sign(signer);
            return signedJWT.serialize();
        } catch (Exception e) {
            return "";
        }
    }
    
    // read document token
//    public Map<String, Object> readToken(String token) {
//        try {
//        	SignedJWT signedJWT = SignedJWT.parse(token);
//        	JWSVerifier verifier = new MACVerifier(properties.getProperty("SECRET_KEY_DOC"));
//        	
//            if(!signedJWT.verify(verifier)) {
//            	return null;
//            }
//            
//            return signedJWT.getJWTClaimsSet().toJSONObject();
//        } catch (Exception exception) {
//            return null;
//        }
//    }
    
    // check if the token is enabled
//    public Boolean tokenEnabled() {
//        String secret = properties.getProperty("SECRET_KEY_DOC");
//        return secret != null && !secret.isEmpty();
//    }

    // check if the token is enabled for request
//    public Boolean tokenUseForRequest() {
//        String tokenUseForRequest = getTokenUseForRequest();
//        return Boolean.parseBoolean(tokenUseForRequest) && !tokenUseForRequest.isEmpty();
//    }
    
    // get config request jwt
//    public String getTokenUseForRequest() {
//        return properties.getProperty("TOKEN_USE_FOR_REQUEST");
//    }
    
    // get languages
    public Map<String, String> getLanguages() {
        String langs = properties.getProperty("files.docservice.languages");
        List<String> langsAndKeys = Arrays.asList(langs.split("\\|"));

        Map<String, String> languages = new LinkedHashMap<>();

        langsAndKeys.forEach((str) -> {
            String[] couple = str.split(":");
            languages.put(couple[0], couple[1]);
        });
        return languages;
    }
}
