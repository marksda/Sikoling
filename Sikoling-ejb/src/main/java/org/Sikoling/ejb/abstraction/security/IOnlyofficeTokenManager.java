package org.Sikoling.ejb.abstraction.security;

import java.util.Map;

public interface IOnlyofficeTokenManager {
	String createToken(Map<String, Object> payloadClaims);
	Map<String, Object> readToken(String token);
}
