package org.Sikoling.ejb.main.integrator.onlyoffice;

import java.util.Map;

import org.Sikoling.ejb.abstraction.security.IOnlyofficeTokenManager;

import jakarta.inject.Inject;

public class OnlyOfficeTokenManagerEJB implements IOnlyofficeTokenManager {
	
	@Inject
	private OnlyOfficeTokenManager onlyOfficeTokenManager;

	@Override
	public String createToken(Map<String, Object> payloadClaims) {
		return onlyOfficeTokenManager.createToken(payloadClaims);
	}

	@Override
	public Map<String, Object> readToken(String token) {
		return onlyOfficeTokenManager.readToken(token);
	}

}
