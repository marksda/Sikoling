package org.Sikoling.ejb.abstraction.entity.onlyoffice;

import java.io.Serializable;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.helpers.FileUtility;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.utils.Constants;

import com.nimbusds.jose.util.StandardCharset;

public class FileModel implements Serializable {

	private static final long serialVersionUID = -8999679392881024180L;
	private final String type;
	private final String mode;
	private final String documentType;
    private final Document document;
    private final EditorConfig editorConfig;
    private final String token;
    
	public FileModel(Properties properties, String fileNameParam, String lang, String actionData, User user, Boolean isEnableDirectUrl, String type, String mode) {
		String fileName = FileUtility.getFileName(fileNameParam);
		this.type = type != null ? type:"desktop";
		this.mode = mode != null ? mode:"edit";
		this.documentType = FileUtility.getFileType(fileNameParam).toString().toLowerCase();
		this.document = new Document();
		this.document.setTitle(fileName);
		this.document.setUrl(properties.getProperty("URL_DOC_STORAGE").concat("/download").concat(URLEncoder.encode(fileNameParam, StandardCharsets.UTF_8.toString())));
//		this.document.setDirectUrl(isEnableDirectUrl ? DocumentManager.getDownloadUrl(fileName, false) : "");
		this.document.setFileType(FileUtility.getFileExtension(fileNameParam).replace(".", ""));
		this.document.setKey(generateRevisionId(fileName));
		this.document.setInfo(new Info());
		this.document.getInfo().setFavorite(user.getFavorite());
		this.document.setReferenceData(new ReferenceData(fileName, DocumentManager.curUserHostAddress(null), user));
		
		this.editorConfig = null;
		this.token = null;
	}
	
	private String generateRevisionId(String expectedKey) {
       String formatKey = expectedKey.length() > Constants.MAX_KEY_LENGTH ? Integer.toString(expectedKey.hashCode()) : expectedKey;
       String key = formatKey.replace("[^0-9-.a-zA-Z_=]", "_");

       return key.substring(0, Math.min(key.length(), Constants.MAX_KEY_LENGTH));  // the resulting key length is 20 or less
   }
    
	public class Document {
        private String title;
        private String url;
//        private String directUrl;
        private String fileType;
        private String key;
        private Info info;
        private Permissions permissions;
        private ReferenceData referenceData;
        
        public Document() {}

        public String getTitle() {
            return title;
        }

        public void setTitle(final String titleParam) {
            this.title = titleParam;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(final String urlParam) {
            this.url = urlParam;
        }

//        public String getDirectUrl() {
//            return directUrl;
//        }
//
//        public void setDirectUrl(final String directUrlParam) {
//            this.directUrl = directUrlParam;
//        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(final String fileTypeParam) {
            this.fileType = fileTypeParam;
        }

        public String getKey() {
            return key;
        }

        public void setKey(final String keyParam) {
            this.key = keyParam;
        }

        public Info getInfo() {
            return info;
        }

        public void setInfo(final Info infoParam) {
            this.info = infoParam;
        }

        public Permissions getPermissions() {
            return permissions;
        }

        public void setPermissions(final Permissions permissionsParam) {
            this.permissions = permissionsParam;
        }

        public ReferenceData getReferenceData() {
            return referenceData;
        }
        
        public void setReferenceData(final ReferenceData referenceDataParam) {
            this.referenceData = referenceDataParam;
        }
    }
	
	public class Permissions {
        private final Boolean comment;
        private final Boolean copy;
        private final Boolean download;
        private final Boolean edit;
        private final Boolean print;
        private final Boolean fillForms;
        private final Boolean modifyFilter;
        private final Boolean modifyContentControl;
        private final Boolean review;
        private final Boolean chat;
        private final List<String> reviewGroups;
        private final CommentGroups commentGroups;
        private final List<String> userInfoGroups;
        private final Boolean protect;
        //public Gson gson = new Gson();

        // defines what can be done with a document
        public Permissions(final String modeParam, final String typeParam, final Boolean canEdit, final User user) {
            comment = !modeParam.equals("view") && !modeParam.equals("fillForms") && !modeParam.equals("embedded")
                    && !modeParam.equals("blockcontent");
            copy = !user.getDeniedPermissions().contains("сopy");
            download = !user.getDeniedPermissions().contains("download");
            edit = canEdit && (modeParam.equals("edit") || modeParam.equals("view") || modeParam.equals("filter")
                    || modeParam.equals("blockcontent"));
            print = !user.getDeniedPermissions().contains("print");
            fillForms = !modeParam.equals("view") && !modeParam.equals("comment") && !modeParam.equals("embedded")
                    && !modeParam.equals("blockcontent");
            modifyFilter = !modeParam.equals("filter");
            modifyContentControl = !modeParam.equals("blockcontent");
            review = canEdit && (modeParam.equals("edit") || modeParam.equals("review"));
            chat = !user.getId().equals("uid-0");
            reviewGroups = user.getReviewGroups();
            commentGroups = user.getCommentGroups();
            userInfoGroups = user.getUserInfoGroups();
            protect = !user.getDeniedPermissions().contains("protect");
        }

        public Boolean getComment() {
            return comment;
        }

        public Boolean getCopy() {
            return copy;
        }

        public Boolean getDownload() {
            return download;
        }

        public Boolean getEdit() {
            return edit;
        }

        public Boolean getPrint() {
            return print;
        }

        public Boolean getFillForms() {
            return fillForms;
        }

        public Boolean getModifyFilter() {
            return modifyFilter;
        }

        public Boolean getModifyContentControl() {
            return modifyContentControl;
        }

        public Boolean getReview() {
            return review;
        }

        public Boolean getChat() {
            return chat;
        }

        public List<String> getReviewGroups() {
            return reviewGroups;
        }

        public CommentGroups getCommentGroups() {
            return commentGroups;
        }

        public List<String> getUserInfoGroups() {
            return userInfoGroups;
        }

        public Boolean getProtect() {
            return protect;
        }
    }

	public class ReferenceData {
        private final String instanceId;
        private final Map<String, String> fileKey;
        public ReferenceData(final String fileName, final String curUserHostAddress, final User user) {
            instanceId = DocumentManager.getServerUrl(true);
            Map<String, String> fileKeyList = new HashMap<>();
            if (!user.getId().equals("uid-0")) {
                fileKeyList.put("fileName", fileName);
                fileKeyList.put("userAddress", curUserHostAddress);
            } else {
                fileKeyList = null;
            }
            fileKey = fileKeyList;
        }

        public String getInstanceId() {
            return instanceId;
        }

        public Map<String, String> getFileKey() {
            return fileKey;
        }
    }
	
	public class Info {
        private String owner = "Me";
        private Boolean favorite;
        private String uploaded = getDate();

        public String getOwner() {
            return owner;
        }

        public Boolean getFavorite() {
            return favorite;
        }

        public void setFavorite(final Boolean favoriteParam) {
            this.favorite = favoriteParam;
        }

        public String getUploaded() {
            return uploaded;
        }

        private String getDate() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd yyyy", Locale.US);
            return simpleDateFormat.format(new Date());
        }
    }
	
}
