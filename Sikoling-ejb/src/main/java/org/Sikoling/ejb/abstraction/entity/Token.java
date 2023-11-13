package org.Sikoling.ejb.abstraction.entity;

import java.util.Objects;

public class Token {
	private final String userId;
    private final String userName;
    private final String userEmail;
    private final String hakAkses;
    private final String accessToken;
    private final String refreshToken;
    private final Long expiresIn;
    private final String sessionId;

    public Token(String userId, String userName, String userEmail, String accessToken, String refreshToken,
			Long expiresIn, String hakAkses, String sessionId) {
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.expiresIn = expiresIn;
		this.hakAkses = hakAkses;
		this.sessionId = sessionId;
	}

	public String getHakAkses() {
		return hakAkses;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}
	
	public String getUserEmail() {
		return userEmail;
	}	

	public String getAccessToken() {
		return accessToken;
	}	

	public String getRefreshToken() {
		return refreshToken;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public String getSessionId() {
		return sessionId;
	}
	

	@Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.userId);
        hash = 67 * hash + Objects.hashCode(this.userName);
        hash = 67 * hash + Objects.hashCode(this.userEmail);
        hash = 67 * hash + Objects.hashCode(this.accessToken);
        hash = 67 * hash + Objects.hashCode(this.refreshToken);
        hash = 67 * hash + Objects.hashCode(this.expiresIn);
        return hash;
    }
    
	@Override    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Token other = (Token) obj;
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.userEmail, other.userEmail)) {
            return false;
        }
        if (!Objects.equals(this.accessToken, other.accessToken)) {
            return false;
        }
        if (!Objects.equals(this.refreshToken, other.refreshToken)) {
            return false;
        }
        if (!Objects.equals(this.expiresIn, other.expiresIn)) {
            return false;
        }
        return true;
    }
    
}
