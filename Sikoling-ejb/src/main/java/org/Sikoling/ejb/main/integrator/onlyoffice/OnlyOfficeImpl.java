package org.Sikoling.ejb.main.integrator.onlyoffice;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.FileModel;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.OnlyofficeUser;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.utils.StatusType;
import org.Sikoling.ejb.abstraction.repository.IOnlyOfficeRepository;
import org.Sikoling.ejb.main.integrator.onlyoffice.helpers.DocumentManager;
import org.Sikoling.ejb.main.integrator.onlyoffice.helpers.TrackManager;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

public class OnlyOfficeImpl implements IOnlyOfficeRepository {
	private final TrackManager trackManager;
	private final DocumentManager documentManager;
	private final OnlyOfficeTokenManager tokenManager;

	public OnlyOfficeImpl(TrackManager trackManager, DocumentManager documentManager, OnlyOfficeTokenManager tokenManager) {
		this.trackManager = trackManager;
		this.documentManager = documentManager;
		this.tokenManager = tokenManager;
	}

	@Override
	public void commandRequest(JsonObject requestBodyPost, String fileNameParam, String userAddress) throws Exception {
		int status = requestBodyPost.getInt("status");
		
		if(status == StatusType.EDITING.getCode()) { //Editing	
			JsonArray actions = requestBodyPost.getJsonArray("actions");
			JsonArray users = requestBodyPost.getJsonArray("users");
			JsonObject action = actions.getJsonObject(0);
			if(actions != null && action.getInt("type") == 0) { // finished edit
				String user = action.getString("userid");  // the user who finished editing
                if (users.indexOf((Object) user) == -1) {
                    String key = requestBodyPost.getString("key");
                    try {
                        trackManager.commandRequest("forcesave", key, null);
                    } catch (Exception e) {
                    	throw new Exception("Command request error");
                    }
                }
			}
		}
		
		// MustSave, Corrupted
        if (status == StatusType.MUST_SAVE.getCode() || status == StatusType.CORRUPTED.getCode()) {
            try {
                trackManager.processSave(requestBodyPost, fileNameParam, userAddress);
            } catch (Exception ex) {
            	throw new Exception("Process save error");
            }

        }
        
        // MustForceSave, CorruptedForceSave
        if (status == StatusType.MUST_FORCE_SAVE.getCode() || status == StatusType.CORRUPTED_FORCE_SAVE.getCode()) {
            try {
                trackManager.processForceSave(requestBodyPost, fileNameParam, userAddress);
            } catch (Exception ex) {
            	throw new Exception("error");
            }
        }
		
	}

	@Override
	public FileModel getConfig(String namaFile, String mode, String height, String width, OnlyofficeUser onlyofficeUser) throws Exception {	
		FileModel fileModel = new FileModel(
				namaFile, null, null, onlyofficeUser, null, null, 
				mode, height, width, documentManager, tokenManager
				);
		return fileModel;
	}

}
