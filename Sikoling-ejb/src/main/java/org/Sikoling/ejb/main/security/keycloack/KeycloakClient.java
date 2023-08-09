package org.Sikoling.ejb.main.security.keycloack;

import org.Sikoling.ejb.abstraction.repository.ITokenKeycloak;
import org.keycloak.OAuth2Constants;
import org.keycloak.TokenVerifier;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessTokenResponse;

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
		return getAccessTokenString(newKeycloakBuilderWithClientCredentials().build());
	}

	@Override
	public String getAccessTokenString(String username, String password) {
		return getAccessTokenString(newKeycloakBuilderWithPasswordCredentials(username, password).build());
	}
	
	@Override
	public AccessToken getAccessToken() {
		return getAccessToken(newKeycloakBuilderWithClientCredentials().build());
	}
	
	@Override
	public AccessToken getAccessToken(String username, String password) {
      return getAccessToken(newKeycloakBuilderWithPasswordCredentials(username, password).build());
    }
	
	private String getAccessTokenString(Keycloak keycloak) {
	  AccessTokenResponse tokenResponse = getAccessTokenResponse(keycloak);
	  return tokenResponse == null ? null : tokenResponse.getToken();
    }
	
	private AccessTokenResponse getAccessTokenResponse(Keycloak keycloak) {
	  try {
	    return keycloak.tokenManager().getAccessToken();
	  } catch (Exception ex) {
	    return null;
	  }
    }
	
	private AccessToken getAccessToken(Keycloak keycloak) {
      return extractAccessTokenFrom(keycloak, getAccessTokenString(keycloak));
    }
	
	private AccessToken extractAccessTokenFrom(Keycloak keycloak, String accessToken) {
		
	  if (accessToken == null) {
	    return null;
	  }

      try {
        return TokenVerifier.create(accessToken, AccessToken.class).getToken();
      } catch (VerificationException e) {
        return null;
      }
    }

	private KeycloakBuilder newKeycloakBuilderWithClientCredentials() {
	      return KeycloakBuilder.builder() 
	        .realm(realmId) 
	        .serverUrl(serverUrl)
	        .clientId(clientId) 
	        .clientSecret(clientSecret)
	        .grantType(OAuth2Constants.CLIENT_CREDENTIALS);
    }
	
	private KeycloakBuilder newKeycloakBuilderWithPasswordCredentials(String username, String password) {
      return newKeycloakBuilderWithClientCredentials() 
        .username(username) 
        .password(password) 
        .grantType(OAuth2Constants.PASSWORD);
    }

		
}
