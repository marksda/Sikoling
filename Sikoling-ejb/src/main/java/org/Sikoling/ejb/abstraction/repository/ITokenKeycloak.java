package org.Sikoling.ejb.abstraction.repository;

import org.Sikoling.ejb.abstraction.security.IToken;
import org.keycloak.representations.AccessToken;

public interface ITokenKeycloak extends IToken {
	AccessToken getAccessToken();
	AccessToken getAccessToken(String username, String password);
}
