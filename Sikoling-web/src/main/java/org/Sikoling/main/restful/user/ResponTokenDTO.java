package org.Sikoling.main.restful.user;

import org.Sikoling.ejb.abstraction.entity.ResponToken;
import org.Sikoling.main.restful.security.TokenDTO;

public class ResponTokenDTO {
	
	private String status;
	private TokenDTO token;	
	
	public ResponTokenDTO() {
		
	}
	
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
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setToken(TokenDTO token) {
		this.token = token;
	}

	
}
