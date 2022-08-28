package org.Sikoling.ejb.main.security;

import java.util.Map;

import org.Sikoling.ejb.abstraction.service.security.AuthorizationException;
import org.Sikoling.ejb.abstraction.service.security.ITokenValidationService;

import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.JWTProcessor;


public class TokenValidationJWK implements ITokenValidationService {
	
	private final JWTProcessor<SecurityContext> jwtProcessor;

    public TokenValidationJWK(JWTProcessor<SecurityContext> jwtProcessor) {
        this.jwtProcessor = jwtProcessor;
    }
    
	@Override
	public Map<String, Object> validate(String jwt) {
		try {
            return jwtProcessor.process(jwt, null).getClaims();
        } catch (Exception ex) {
            throw new AuthorizationException("Token validation fails", ex);
        }
	}
	
}
