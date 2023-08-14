package org.Sikoling.ejb.main.security;

import java.util.Map;

import org.Sikoling.ejb.abstraction.repository.ITokenValidationRepository;
import org.Sikoling.ejb.abstraction.service.security.AuthorizationException;
import org.Sikoling.ejb.main.Infrastructure;

import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.BadJWTException;
import com.nimbusds.jwt.proc.JWTProcessor;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

@Stateless
@LocalBean
@Infrastructure
public class TokenValidationJWK implements ITokenValidationRepository {
	
	private final JWTProcessor<SecurityContext> jwtProcessor;

    public TokenValidationJWK(JWTProcessor<SecurityContext> jwtProcessor) {
        this.jwtProcessor = jwtProcessor;
    }
    
	@Override
	public Map<String, Object> validate(String jwt) {
		try {
            return jwtProcessor.process(jwt, null).getClaims();
        } 
		catch (BadJWTException e) {
			throw new AuthorizationException(e.getMessage());
		}
		catch (Exception ex) {
            throw new AuthorizationException("Token validation fails", ex);
        }
	}
	
}
