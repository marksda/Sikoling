package org.Sikoling.ejb.abstraction.entity.onlyoffice;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.helpers.FileUtility;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.utils.Constants;
import org.Sikoling.ejb.main.integrator.onlyoffice.OnlyOfficeTokenManager;
import org.Sikoling.ejb.main.integrator.onlyoffice.helpers.DocumentManager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class FileModel implements Serializable {

	private static final long serialVersionUID = -8999679392881024180L;
	private String type;
	private String documentType;
    private Document document;
    private EditorConfig editorConfig;
    private String height;
    private String width;
    private String token;
    
    
	public FileModel(String fileNameParam, String lang, String actionData, OnlyofficeUser user, Boolean isEnableDirectUrl, 
			String type, String mode, String height, String width, DocumentManager documentManager, OnlyOfficeTokenManager tokenManager) throws Exception {
		fileNameParam = fileNameParam == null ? "" : fileNameParam.trim();
		String fileName = FileUtility.getFileName(fileNameParam);
		this.type = type != null ? type:"desktop";
		this.height = height != null ? height:"100%";
		this.width = width != null ? width:"100%";
		this.documentType = FileUtility.getFileType(fileName).toString().toLowerCase();
		this.document = new Document();
		this.document.setTitle(fileName);
		this.document.setUrl(documentManager.getDownloadUrl(fileNameParam, true));
		this.document.setFileType(FileUtility.getFileExtension(fileName).replace(".", ""));
		this.document.setKey(generateRevisionId(fileName));
		this.document.setInfo(new Info());
		this.document.getInfo().setFavorite(user.getFavorite());
		this.document.setReferenceData(new ReferenceData(this.document.getKey(), documentManager.getServerUrl(true)));
		
		Boolean canEdit = documentManager.getEditedExts().contains(FileUtility.getFileExtension(document.getTitle()));
		Permissions permissions = new Permissions(mode, type, canEdit, user);
		this.document.setPermissions(permissions);
		
		
//		List<Map<String, String>> templates = new ArrayList<>();
        String createUrl = documentManager.getCreateUrl(fileNameParam);
        // add templates for the "Create New" from menu option
//        Map<String, String> templateForBlankDocument = new HashMap<>();
//        templateForBlankDocument.put("image", "");
//        templateForBlankDocument.put("title", "Blank");
//        templateForBlankDocument.put("url", createUrl);
//        templates.add(templateForBlankDocument);

        // set the editor config parameters
        this.editorConfig = new EditorConfig(actionData);
        this.editorConfig.setCallbackUrl(documentManager.getCallback(fileNameParam));  // get callback url
        this.editorConfig.setMode(mode);

        HashMap<String, Object> coEditing;
        if(mode.equals("view")) {
        	coEditing = new HashMap<String, Object>();
        	coEditing.put("mode", "strict");
        	coEditing.put("change", false);
        }
        else {
        	coEditing = null;
        }
        this.editorConfig.setCoEditing(coEditing);
        
        if (lang != null) {
        	this.editorConfig.setLang(lang);  // write language parameter to the config
        }

        this.editorConfig.setCreateUrl(user.getGroup().equals("Umum") ? null : createUrl);
//        this.editorConfig.setTemplates(user.getTemplates() ? templates : null);

        // write user information to the config (id, name and group)
        this.editorConfig.getUser().setId(user.getId());
        this.editorConfig.getUser().setName(user.getName());
        this.editorConfig.getUser().setGroup(user.getGroup());

//        changeType(mode, type, user, fileName, documentManager);
		
        Map<String, Object> params = new HashMap<>();
        params.put("type", this.type);
//        params.put("mode", this.mode);
        params.put("documentType", this.documentType);
        params.put("document", this.document);
        params.put("editorConfig", this.editorConfig);
        params.put("height", this.height);
        params.put("width", this.width);
        
		this.token = tokenManager.createToken(params);
	}
			
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getType() {
		return type;
	}

	public String getDocumentType() {
		return documentType;
	}

	public Document getDocument() {
		return document;
	}

	public EditorConfig getEditorConfig() {
		return editorConfig;
	}

	public String getToken() {
		return token;
	}
    
	private String generateRevisionId(String expectedKey) {
       String formatKey = expectedKey.length() > Constants.MAX_KEY_LENGTH ? Integer.toString(expectedKey.hashCode()) : expectedKey;
       String key = formatKey.replace("[^0-9-.a-zA-Z_=]", "_");

       return key.substring(0, Math.min(key.length(), Constants.MAX_KEY_LENGTH));  // the resulting key length is 20 or less
   }
    
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public void setEditorConfig(EditorConfig editorConfig) {
		this.editorConfig = editorConfig;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public class Document {
        private String title;
        private String url;
        private String directUrl;
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

        public String getDirectUrl() {
            return directUrl;
        }

        public void setDirectUrl(final String directUrlParam) {
            this.directUrl = directUrlParam;
        }

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

        // defines what can be done with a document
        public Permissions(String modeParam, String typeParam, Boolean canEdit, OnlyofficeUser user) {
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
        private String instanceId;
        private String fileKey;
        
        public ReferenceData(String fileKey, String storageServerUrl) {
            this.instanceId = storageServerUrl;
            this.fileKey = fileKey;
        }

        public String getInstanceId() {
            return instanceId;
        }

        public String getFileKey() {
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
	
	public class EditorConfig {
        private HashMap<String, Object> actionLink = null;
        private String mode = "edit";
        private String callbackUrl;
        private HashMap<String, Object> coEditing = null;
        private String lang = "en";
        private String createUrl;
        private List<Map<String, String>> templates;
        private User user;
        private Customization customization;
        private Embedded embedded;

        public EditorConfig(String actionData) {
            // get the action in the document that will be scrolled to (bookmark or comment)
            if (actionData != null) {
            	try {
	            	ObjectMapper mapper = new ObjectMapper();
	            	this.actionLink = mapper.readValue(actionData.getBytes(), new TypeReference<HashMap<String, Object>>() {
					});
            	}
            	catch (Exception e) {
            		this.actionLink = null;
				}
            }
            user = new User();
            customization = new Customization();
        }

        public HashMap<String, Object> getActionLink() {
            return actionLink;
        }

        public String getCallbackUrl() {
            return callbackUrl;
        }

        public HashMap<String, Object> getCoEditing() {
            return coEditing;
        }

        public String getLang() {
            return lang;
        }

        public String getCreateUrl() {
            return createUrl;
        }

        public List<Map<String, String>> getTemplates() {
            return templates;
        }

        public Embedded getEmbedded() {
            return embedded;
        }

        // set parameters for the embedded document
        public void initDesktop(final String url) {
            embedded = new Embedded();

            // the absolute URL that will allow the document to be saved onto the user personal computer
            embedded.setSaveUrl(url);

            // the absolute URL to the document serving as a source file for the document embedded into the web page
            embedded.setEmbedUrl(url);

            // the absolute URL that will allow other users to share this document
            embedded.setShareUrl(url);

            // the place for the embedded viewer toolbar, can be either top or bottom
            embedded.setToolbarDocked("top");
        }

        public String getMode() {
            return mode;
        }

        public void setMode(final String modeParam) {
            this.mode = modeParam;
        }

        public void setCallbackUrl(final String callbackUrlParam) {
            this.callbackUrl = callbackUrlParam;
        }

        public void setCoEditing(final HashMap<String, Object> coEditingParam) {
            this.coEditing = coEditingParam;
        }

        public void setLang(final String langParam) {
            this.lang = langParam;
        }

        public void setCreateUrl(final String createUrlParam) {
            this.createUrl = createUrlParam;
        }

        public void setTemplates(final List<Map<String, String>> templatesParam) {
            this.templates = templatesParam;
        }

        public User getUser() {
            return user;
        }

        public void setUser(final User userParam) {
            this.user = userParam;
        }

        public Customization getCustomization() {
            return customization;
        }

        // default user parameters (id, name and group)
        public class User {
            private String id;
            private String name;
            private String group;

            public String getId() {
                return id;
            }

            public void setId(final String idParam) {
                this.id = idParam;
            }

            public String getName() {
                return name;
            }

            public void setName(final String nameParam) {
                this.name = nameParam;
            }

            public String getGroup() {
                return group;
            }

            public void setGroup(final String groupParam) {
                this.group = groupParam;
            }
        }

        // customization parameters
        public class Customization {
            private Goback goback;
            private Boolean forcesave;
            private Boolean comments;
            private Boolean feedback;

            public Customization() {
                comments = false;
                feedback = false;
                forcesave = false;
                goback = new Goback();
            }

            public Goback getGoback() {
                return goback;
            }

            public Boolean getForcesave() {
                return forcesave;
            }

            public Boolean getComments() {
                return comments;
            }

            public Boolean getFeedback() {
                return feedback;
            }

            public class Goback {
                private String url;

                public String getUrl() {
                    return url;
                }

                public void setUrl(final String urlParam) {
                    this.url = urlParam;
                }
            }
        }

        // parameters for embedded document
        public class Embedded {
            private String saveUrl;
            private String embedUrl;
            private String shareUrl;
            private String toolbarDocked;

            public String getSaveUrl() {
                return saveUrl;
            }

            public void setSaveUrl(String saveUrlParam) {
                this.saveUrl = saveUrlParam;
            }

            public String getEmbedUrl() {
                return embedUrl;
            }

            public void setEmbedUrl(String embedUrlParam) {
                this.embedUrl = embedUrlParam;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrlParam) {
                this.shareUrl = shareUrlParam;
            }

            public String getToolbarDocked() {
                return toolbarDocked;
            }

            public void setToolbarDocked(String toolbarDockedParam) {
                this.toolbarDocked = toolbarDockedParam;
            }
        }
    }
	
}
