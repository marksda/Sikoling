package org.Sikoling.ejb.main.security.keycloack;

import java.io.InputStreamReader;
import java.util.ArrayList;

import org.Sikoling.ejb.abstraction.security.ITokenKeycloak;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;

import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

public class KeycloakClient implements ITokenKeycloak {
	private final String serverUrl;
    private final String realmId;
    private final String clientId;
    private final String clientSecret;    

	public KeycloakClient(String serverUrl, String realmId, String clientId, String clientSecret) {
		this.serverUrl = serverUrl;
		this.realmId = realmId;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	@Override
	public String getAccessTokenString() {
		return getAccessTokenString(newKeycloakBuilderWithClientCredentials(OAuth2Constants.CLIENT_CREDENTIALS).build());
	}

	@Override
	public String getAccessTokenString(String username, String password) {
		return getAccessTokenString(newKeycloakBuilderWithPasswordCredentials(OAuth2Constants.PASSWORD, username, password).build());
	}
	
	@Override
	public AccessTokenResponse getAccessTokenResponse() {
		return getAccessTokenResponse(newKeycloakBuilderWithClientCredentials(OAuth2Constants.CLIENT_CREDENTIALS).build());
	}

	@Override
	public AccessTokenResponse getAccessTokenResponse(String username, String password) {
		return getAccessTokenResponse(newKeycloakBuilderWithPasswordCredentials(username, password, OAuth2Constants.PASSWORD).build());
	}

	@Override
	public AccessTokenResponse getAccessTokenResponseRefreshToken(String refreshToken) {
		AccessTokenResponse accessTokenResponse = null;
		String url = serverUrl + "/realms/" + realmId + "/protocol/openid-connect/token";
		HttpPost post = new HttpPost(url);
		
		ArrayList<NameValuePair> parameters;
		parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("client_id", clientId));
		parameters.add(new BasicNameValuePair("grant_type", OAuth2Constants.REFRESH_TOKEN));
		parameters.add(new BasicNameValuePair("client_secret", clientSecret));
		parameters.add(new BasicNameValuePair("refresh_token", refreshToken));
		
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
			httpClient = HttpClients.createDefault();
			response = httpClient.execute(post);
			JsonParser jsonParser = Json.createParser(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
			accessTokenResponse = new AccessTokenResponse();
			Event event = null;
			while (jsonParser.hasNext()) {
				event = jsonParser.next();
				if (event == JsonParser.Event.KEY_NAME ) {	
					String key = jsonParser.getString();
					switch (key) {
					case "access_token":
						event = jsonParser.next();
						accessTokenResponse.setToken(jsonParser.getString());
						break;
					case "refresh_token":
						event = jsonParser.next();
						accessTokenResponse.setRefreshToken(jsonParser.getString());
						break;
					case "expires_in":
						event = jsonParser.next();
						accessTokenResponse.setExpiresIn(jsonParser.getLong());
						break;
					case "refresh_expires_in":
						event = jsonParser.next();
						accessTokenResponse.setRefreshExpiresIn(jsonParser.getLong());
						break;
					default:
						break;
					}
				}
			}
			
			return accessTokenResponse;
		} catch (Exception e) {
			return null;
		}
			

	}
	
	private String getAccessTokenString(Keycloak keycloak) {
	  AccessTokenResponse tokenResponse = getAccessTokenResponse(keycloak);
	  return tokenResponse == null ? null : tokenResponse.getToken();
    }
	
	private AccessTokenResponse getAccessTokenResponse(Keycloak keycloak) {
	  try {
		  return keycloak.tokenManager().grantToken();
	  } catch (Exception ex) {
	    return null;
	  }
    }

	private KeycloakBuilder newKeycloakBuilderWithClientCredentials(String grandType) {
      return KeycloakBuilder.builder() 
        .realm(realmId) 
        .serverUrl(serverUrl)
        .clientId(clientId) 
        .clientSecret(clientSecret)
        .grantType(grandType);
    }
	
	private KeycloakBuilder newKeycloakBuilderWithPasswordCredentials(String username, String password, String grandType) {
      return newKeycloakBuilderWithClientCredentials(grandType) 
        .username(username) 
        .password(password);
    }
	
}
