package org.Sikoling.ejb.main.integrator.onlyoffice;

import java.util.Map;
import java.util.Properties;

import org.Sikoling.ejb.abstraction.security.IOnlyofficeTokenManager;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.JWTClaimsSet.Builder;

public class OnlyOfficeTokenManager implements IOnlyofficeTokenManager {
	private final Properties properties;
	
	public OnlyOfficeTokenManager(Properties properties) {
		this.properties = properties;
	}

	@Override
	public String createToken(Map<String, Object> payloadClaims) {
		try {
            JWSSigner signer = new MACSigner(properties.getProperty("SECRET_KEY_DOC"));
            Builder claimsSetBuilder = new JWTClaimsSet.Builder();            
            for(String key : payloadClaims.keySet()) {
            	claimsSetBuilder.claim(key, payloadClaims.get(key));
            }            
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSetBuilder.build());
            signedJWT.sign(signer);
            return signedJWT.serialize();
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public Map<String, Object> readToken(String token) {
		try {
        	SignedJWT signedJWT = SignedJWT.parse(token);
        	JWSVerifier verifier = new MACVerifier(properties.getProperty("SECRET_KEY_DOC"));
        	
            if(!signedJWT.verify(verifier)) {
            	return null;
            }
            
            return signedJWT.getJWTClaimsSet().toJSONObject();
        } catch (Exception exception) {
            return null;
        }
	}

	// check if the token is enabled
    public Boolean tokenEnabled() {
        String secret = properties.getProperty("SECRET_KEY_DOC");
        return secret != null && !secret.isEmpty();
    }
    
    // check if the token is enabled for request
    public Boolean tokenUseForRequest() {
        String tokenUseForRequest = getTokenUseForRequest();
        return Boolean.parseBoolean(tokenUseForRequest) && !tokenUseForRequest.isEmpty();
    }
    
    // get config request jwt
    public String getTokenUseForRequest() {
        return properties.getProperty("TOKEN_USE_FOR_REQUEST");
    }
}
