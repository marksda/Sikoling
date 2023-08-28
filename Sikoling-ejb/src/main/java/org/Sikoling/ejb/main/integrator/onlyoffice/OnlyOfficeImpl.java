package org.Sikoling.ejb.main.integrator.onlyoffice;

import java.util.List;
import java.util.Properties;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.Action;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.ActionType;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.FileModel;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.OnlyofficeUser;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.RequestBodyPost;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.helpers.DocumentManager;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.utils.StatusType;
import org.Sikoling.ejb.abstraction.repository.IOnlyOfficeRepository;
import org.Sikoling.ejb.main.integrator.onlyoffice.helpers.ServiceConverter;
import org.Sikoling.ejb.main.integrator.onlyoffice.helpers.TrackManager;

public class OnlyOfficeImpl implements IOnlyOfficeRepository {
	private final Properties properties;
	private final TrackManager trackManager;

	public OnlyOfficeImpl(Properties properties, TrackManager trackManager) {
		this.properties = properties;
		this.trackManager = trackManager;
	}

	@Override
	public void commandRequest(RequestBodyPost requestBodyPost, String fileNameParam, String userAddress) throws Exception {
		int status = requestBodyPost.getStatus();
		
		if(status == StatusType.EDITING.getCode()) { //Editing	
			Action action = requestBodyPost.getActions().get(0);
			List<String> users = requestBodyPost.getUsers();
			if(action != null && action.getType() == ActionType.USER_DISCONNECT_CO_EDITING.getCode()) { //edit finished
				String user = action.getUserid();
				if(users.indexOf(user) == -1) {
					String key = requestBodyPost.getKey();
                    try {
                    	trackManager.commandRequest("forcesave", key, null);
                    } catch (Exception e) {
                    	throw new Exception("error");
                    }
				}
			}
		}
		
		// MustSave, Corrupted
        if (status == StatusType.MUST_SAVE.getCode() || status == StatusType.CORRUPTED.getCode()) {
            try {
                trackManager.processSave(requestBodyPost, fileNameParam, userAddress);
            } catch (Exception ex) {
            	throw new Exception("error");
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
//		FileModel fileModel = new FileModel(namaFile, null, null, null, null, null, null, documentManager);
//		return fileModel;
		return null;
	}

}
