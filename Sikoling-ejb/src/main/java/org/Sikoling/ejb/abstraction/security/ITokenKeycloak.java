package org.Sikoling.ejb.abstraction.security;

import org.keycloak.representations.AccessTokenResponse;

public interface ITokenKeycloak extends IToken {
	AccessTokenResponse getAccessTokenResponse();
	AccessTokenResponse getAccessTokenResponse(String username, String password);
	AccessTokenResponse getAccessTokenResponseRefreshToken(String refreshToken);
}
