package org.Sikoling.ejb.abstraction.service.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.Sikoling.ejb.abstraction.entity.Token;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class OpenIdConnectionService implements IOpenIdConnectService {
	
	private final ITokenValidationService tokenValidationService;
	private final Client client;
	private final String identityProviderUrl;
	private final String clientId;
    private final String clientSecret;  

	public OpenIdConnectionService(ITokenValidationService tokenValidationService, Client client,
			String identityProviderUrl, String clientId, String clientSecret) {
		super();
		this.tokenValidationService = tokenValidationService;
		this.client = client;
		this.identityProviderUrl = identityProviderUrl;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}
	
	
	@Override
	public Token requestToken(String grantType, String code, String redirectUrl) {
		Form form = new Form()
				.param("grant_type", grantType)
                .param("code", code)
                .param("redirect_uri", redirectUrl)
                .param("client_id", clientId)
                .param("client_secret", clientSecret);
		
		Response response = client.target(identityProviderUrl)
                .request(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.form(form));
		
		if (response.getStatus() != 200) {
            throw new IllegalStateException("The tokens couldn't be gotten " + response.readEntity(String.class));
        }

        Map<String, String> map = response.readEntity(new GenericType<HashMap<String, String>>() { });
        
        Map<String, Object> claims = tokenValidationService.validate(map.get("id_token"));
        
        Token token = new Token(
        		getClaim(claims, "sub"), 
        		getClaim(claims, "given_name") + " " + getClaim(claims, "family_name"), 
        		getClaim(claims, "email"), 
        		map.get("access_token"), 
        		map.get("refresh_token"), 
        		map.get("expires_in"), null
    		);

        return token;
	}
	
	private String getClaim(Map<String, Object> claims, String name){
        return Optional.ofNullable(claims.get(name))
                .map(String.class::cast)
                .orElseThrow(() -> new IllegalStateException("Claim " + name + " was expected"));
    }

}
