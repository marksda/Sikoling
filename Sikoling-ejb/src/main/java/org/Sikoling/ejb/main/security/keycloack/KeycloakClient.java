package org.Sikoling.ejb.main.security.keycloack;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.Sikoling.ejb.abstraction.security.ITokenKeycloak;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
		return getAccessTokenString(newKeycloakBuilderWithPasswordCredentials(username, password, OAuth2Constants.PASSWORD).build());
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
		Response response = null;
		String url = serverUrl + "/realms/" + realmId + "/protocol/openid-connect/token";
		final WebTarget refreshTokenUri = ClientBuilder.newClient().target(url);
		Form formRefreshToken = new Form();
		formRefreshToken.param("client_id", clientId);
		formRefreshToken.param("grant_type", OAuth2Constants.REFRESH_TOKEN);
		formRefreshToken.param("client_secret", clientSecret);
		formRefreshToken.param("refresh_token", refreshToken);	
		
		try {
			response = refreshTokenUri.request(MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(formRefreshToken));
			ObjectMapper mapper = new ObjectMapper();
		    JsonFactory factory = mapper.getFactory();
		    JsonParser parser = factory.createParser(new InputStreamReader((InputStream) response.getEntity()));
		    JsonNode node = mapper.readTree(parser);
			accessTokenResponse = new AccessTokenResponse();
			accessTokenResponse.setToken(node.get("access_token").asText());
			accessTokenResponse.setRefreshToken(node.get("refresh_token").asText());
			accessTokenResponse.setExpiresIn(node.get("expires_in").asLong());
			accessTokenResponse.setExpiresIn(node.get("refresh_expires_in").asLong());
			response.close();
			return accessTokenResponse;
		} catch (Exception e) {
			response.close();
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
