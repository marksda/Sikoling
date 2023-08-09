package org.Sikoling.ejb.abstraction.security;

public interface IToken {
	String getAccessTokenString();
	String getAccessTokenString(String username, String password);
}
