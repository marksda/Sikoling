package org.Sikoling.ejb.main.security.tokenvalidation;

import java.util.Map;

import org.Sikoling.ejb.abstraction.repository.ITokenValidation;
import org.Sikoling.ejb.abstraction.service.security.AuthorizationException;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.BadJWTException;
import com.nimbusds.jwt.proc.JWTProcessor;

public class TokenValidation implements ITokenValidation {
	
	private final JWTProcessor<SecurityContext> jwtProcessor;

    public TokenValidation(JWTProcessor<SecurityContext> jwtProcessor) {
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
