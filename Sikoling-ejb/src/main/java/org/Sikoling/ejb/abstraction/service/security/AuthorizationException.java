package org.Sikoling.ejb.abstraction.service.security;

import jakarta.ws.rs.NotAuthorizedException;

public class AuthorizationException extends NotAuthorizedException {
	
	private static final long serialVersionUID = 857099103347067030L;

	public AuthorizationException(String string) {
        super(string);
    }
    
    public AuthorizationException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
}
