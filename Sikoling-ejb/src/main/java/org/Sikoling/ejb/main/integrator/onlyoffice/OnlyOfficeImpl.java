package org.Sikoling.ejb.main.integrator.onlyoffice;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.FileModel;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.OnlyofficeUser;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.utils.StatusType;
import org.Sikoling.ejb.abstraction.repository.IOnlyOfficeRepository;
import org.Sikoling.ejb.main.integrator.onlyoffice.helpers.TrackManager;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

public class OnlyOfficeImpl implements IOnlyOfficeRepository {
	private final TrackManager trackManager;

	public OnlyOfficeImpl(TrackManager trackManager) {
		this.trackManager = trackManager;
	}

	@Override
	public void commandRequest(JsonObject requestBodyPost, String fileNameParam, String userAddress) throws Exception {
		int status = requestBodyPost.getInt("status");
		
		if(status == StatusType.EDITING.getCode()) { //Editing	
			JsonArray actions = requestBodyPost.getJsonArray("actios");
			String[] users = (String[]) requestBodyPost.getJsonArray("users").toArray();
			JsonObject action = actions.getJsonObject(0);
			if(actions != null && action.getString("type").equals("0")) { // finished edit
				String user = action.getString("userid");				
				int index = -1;
				for (int i=0;i<users.length;i++) {
				    if (users[i].equals(user)) {
				        index = i;
				        break;
				    }
				}
				
				if(index == -1) {
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
	public FileModel getConfig(String namaFile, OnlyofficeUser onlyofficeUser) throws Exception {		
//		ServiceConverter serviceConverter = new ServiceConverter(properties);
//		DocumentManager documentManager = new DocumentManager(properties, serviceConverter);
		FileModel fileModel = new FileModel(namaFile, null, null, onlyofficeUser, null, null, null, null);
		return fileModel;
	}

}
