package org.Sikoling.ejb.abstraction.entity.onlyoffice.helpers;

import java.io.Serializable;
import java.util.Properties;

public class DocumentManager implements Serializable {

	private static final long serialVersionUID = -7992490385103047337L;
	private final Properties properties;
	
	public DocumentManager(Properties properties) {
		this.properties = properties;
	}


//	public String getServerUrl(Boolean forDocumentServer) {
//        if (forDocumentServer) {
//            return properties.getProperty("URL_DOC_STORAGE");
//        } else {
//            return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
//                    + request.getContextPath();
//        }
//    }
//
//	public static String getDownloadUrl(final String fileName, final Boolean forDocumentServer) {
//        String serverPath = getServerUrl(forDocumentServer);
//        String hostAddress = curUserHostAddress(null);
//        try {
//            String userAddress = forDocumentServer ? "&userAddress=" + URLEncoder
//                    .encode(hostAddress, java.nio.charset.StandardCharsets.UTF_8.toString()) : "";
//            String query = "?type=download&fileName=" + URLEncoder
//                    .encode(fileName, java.nio.charset.StandardCharsets.UTF_8.toString()) + userAddress;
//
//            return serverPath + "/IndexServlet" + query;
//        } catch (UnsupportedEncodingException e) {
//            return "";
//        }
//    }

}
