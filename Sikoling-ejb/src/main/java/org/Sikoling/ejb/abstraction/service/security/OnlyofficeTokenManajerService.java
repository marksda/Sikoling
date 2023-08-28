package org.Sikoling.ejb.abstraction.service.security;

import java.util.Map;

import org.Sikoling.ejb.abstraction.security.IOnlyofficeTokenManager;

public class OnlyofficeTokenManajerService implements IOnlyofficeTokenManagerService {
	
	private IOnlyofficeTokenManager onlyofficeTokenManager;

	public OnlyofficeTokenManajerService(IOnlyofficeTokenManager onlyofficeTokenManager) {
		this.onlyofficeTokenManager = onlyofficeTokenManager;
	}

	@Override
	public String createToken(Map<String, Object> payloadClaims) {
		return onlyofficeTokenManager.createToken(payloadClaims);
	}

	@Override
	public Map<String, Object> readToken(String token) {
		return onlyofficeTokenManager.readToken(token);
	}

}
