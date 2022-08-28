package org.Sikoling.main.restful.user;

import org.Sikoling.ejb.abstraction.entity.ResponToken;
import org.Sikoling.main.restful.security.TokenDTO;

public class ResponTokenDTO {
	
	private final String status;
	private final TokenDTO token;
	
	public ResponTokenDTO(String status, TokenDTO token) {
		this.status = status;
		this.token = token;
	}
	
	public ResponTokenDTO(ResponToken rt) {
		this.status = rt.getStatus();
		this.token = new TokenDTO(rt.getToken());
	}

	public String getStatus() {
		return status;
	}

	public TokenDTO getToken() {
		return token;
	}

}
