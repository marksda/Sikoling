package org.Sikoling.ejb.main.storage;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.Action;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.ActionType;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.RequestBodyPost;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.StatusType;
import org.Sikoling.ejb.abstraction.repository.IOnlyOfficeRepository;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class OnlyOfficeImpl implements IOnlyOfficeRepository {
	private final Properties properties;
	private final LocalStorageImpl localStorageImpl;

	public OnlyOfficeImpl(Properties properties, LocalStorageImpl localStorageImpl) {
		this.properties = properties;
		this.localStorageImpl = localStorageImpl;
	}

	@Override
	public void commandRequest(RequestBodyPost requestBodyPost) throws Exception {
		int status = requestBodyPost.getStatus();
		
		if(status == StatusType.EDITING.getCode()) { //Editing	
			Action action = requestBodyPost.getActions().get(0);
			List<String> users = requestBodyPost.getUsers();
			if(action != null && action.getType() == ActionType.USER_DISCONNECT_CO_EDITING.getCode()) { //edit finished
				String user = action.getUserid();
				if(users.indexOf(user) == -1) {
					String key = requestBodyPost.getKey();
                    try {
                    	forceSave(key);
                    } catch (Exception e) {
                    	throw new Exception("error");
                    }
				}
			}
		}
	}
	
	private void forceSave(String key) throws Exception {
		Response response = null;
		String url = properties.getProperty("URL_DOC_SERVICE_COMMAND");
		final WebTarget onlyOfficeUri = ClientBuilder.newClient().target(url);
		
		try {
			response = onlyOfficeUri.request(MediaType.APPLICATION_FORM_URLENCODED)
					.post(null);
			ObjectMapper mapper = new ObjectMapper();
		    JsonFactory factory = mapper.getFactory();
		    JsonParser parser = factory.createParser(new InputStreamReader((InputStream) response.getEntity()));
		    JsonNode node = mapper.readTree(parser);
		    if (!node.get("error").asText().equals("0")) {
		    	response.close();
	            throw new Exception("error");
	        }
		    localStorageImpl.download(key, url);
		    response.close();
		} catch (Exception e) {
			response.close();
			throw new Exception("error");
		}
	}

}
