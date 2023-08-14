package org.Sikoling.ejb.abstraction.service.security;

import java.util.Map;

import org.Sikoling.ejb.abstraction.repository.ITokenValidationRepository;

public class TokenValidationService implements ITokenValidationService {
	
	private final ITokenValidationRepository tokenValidationRepository;

	public TokenValidationService(ITokenValidationRepository tokenValidationRepository) {
		this.tokenValidationRepository = tokenValidationRepository;
	}

	@Override
	public Map<String, Object> validate(String jwt) {
		return tokenValidationRepository.validate(jwt);
	}

}
