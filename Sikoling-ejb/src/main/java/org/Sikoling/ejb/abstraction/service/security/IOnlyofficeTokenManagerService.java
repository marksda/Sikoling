package org.Sikoling.ejb.abstraction.service.security;

import java.util.Map;

public interface IOnlyofficeTokenManagerService {
	String createToken(Map<String, Object> payloadClaims);
	Map<String, Object> readToken(String token);
}
