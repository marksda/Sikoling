package org.Sikoling.ejb.abstraction.service.security;

import java.util.Map;

import org.Sikoling.ejb.abstraction.repository.ITokenValidation;

public class TokenValidationService implements ITokenValidationService {
	
	private final ITokenValidation tokenValidationRepository;

	public TokenValidationService(ITokenValidation tokenValidationRepository) {
		this.tokenValidationRepository = tokenValidationRepository;
	}

	@Override
	public Map<String, Object> validate(String jwt) {
		return tokenValidationRepository.validate(jwt);
	}

}
