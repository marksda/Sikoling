package org.Sikoling.ejb.abstraction.entity.onlyoffice.helpers;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.FileType;

public final class FileUtility {
	
	private static List<String> extsDocument = Arrays.asList(
            ".doc", ".docx", ".docm",
            ".dot", ".dotx", ".dotm",
            ".odt", ".fodt", ".ott", ".rtf", ".txt",
            ".html", ".htm", ".mht", ".xml",
            ".pdf", ".djvu", ".fb2", ".epub", ".xps", ".oxps", ".oform"
    );
	
	private static List<String> extsSpreadsheet = Arrays.asList(
            ".xls", ".xlsx", ".xlsm", ".xlsb",
            ".xlt", ".xltx", ".xltm",
            ".ods", ".fods", ".ots", ".csv"
    );
	
	private static List<String> extsPresentation = Arrays.asList(
            ".pps", ".ppsx", ".ppsm",
            ".ppt", ".pptx", ".pptm",
            ".pot", ".potx", ".potm",
            ".odp", ".fodp", ".otp"
    );

	private FileUtility() { }
	
	public static FileType getFileType(String fileName) {
        String ext = getFileExtension(fileName);

        if (extsDocument.contains(ext)) {
            return FileType.Word;
        }

        if (extsSpreadsheet.contains(ext)) {
            return FileType.Cell;
        }

        if (extsPresentation.contains(ext)) {
            return FileType.Slide;
        }

        return FileType.Word;
    }
		
	public static String getFileName(String url) {
        if (url == null) {
            return "";
        }

        String fileName = url.substring(url.lastIndexOf('/') + 1, url.length());
        fileName = fileName.split("\\?")[0];
        return fileName;
    }
	
	public static String getFileNameWithoutExtension(String url) {
        String fileName = getFileName(url);
        if (fileName == null) {
            return null;
        }
        String fileNameWithoutExt = fileName.substring(0, fileName.lastIndexOf('.'));
        return fileNameWithoutExt;
    }
	
	public static String getFileExtension(String url) {
		String fileName = getFileName(url);
        if (fileName == null) {
            return null;
        }        
        return fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
    }
	
	public static Map<String, String> getUrlParams(String url) {
        try {
            String query = new URL(url).getQuery();
            String[] params = query.split("&");  
            Map<String, String> map = new HashMap<>();
            for (String param : params) {  
                String name = param.split("=")[0];
                String value = param.split("=")[1];
                map.put(name, value);
            }
            return map;
        } catch (Exception ex) {
            return null;
        }
    }
	
}
