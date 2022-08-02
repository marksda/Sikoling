package org.Sikoling.ejb.abstraction.service.security;

import org.Sikoling.ejb.abstraction.entity.Token;

public interface IOpenIdConnectService {
	Token requestToken(String grantType, String code, String redirectUrl);
}
