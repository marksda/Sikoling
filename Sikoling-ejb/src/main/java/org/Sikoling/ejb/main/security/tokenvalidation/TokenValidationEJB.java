package org.Sikoling.ejb.main.security.tokenvalidation;

import java.util.Map;

import org.Sikoling.ejb.abstraction.repository.ITokenValidation;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class TokenValidationEJB implements ITokenValidation {
	
	@Inject
	private TokenValidation tokenValidation;

	@Override
	public Map<String, Object> validate(String jwt) {
		return tokenValidation.validate(jwt);
	}

}
