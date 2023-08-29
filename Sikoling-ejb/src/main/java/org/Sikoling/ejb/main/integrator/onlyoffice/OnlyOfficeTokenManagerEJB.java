package org.Sikoling.ejb.main.integrator.onlyoffice;

import java.util.Map;

import org.Sikoling.ejb.abstraction.security.IOnlyofficeTokenManager;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
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
