package org.Sikoling.main.restful.security;

import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Token;

public class TokenDTO {
	
	private String userId;
    private String userName;
    private String userEmail;
    private String hakAkses;
    private String accessToken;
    private String refreshToken;
    private String expiresIn;
    
    public TokenDTO() {    	
    }
    
    public TokenDTO(Token token) {
        this.userId = token.getUserId();
        this.userName = token.getUserName();
        this.userEmail = token.getUserEmail();
        this.accessToken = token.getAccessToken();
        this.refreshToken = token.getRefreshToken();
        this.expiresIn = token.getExpiresIn();
        this.hakAkses = token.getHakAkses();
    }
    
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getHakAkses() {
		return hakAkses;
	}

	public void setHakAkses(String hakAkses) {
		this.hakAkses = hakAkses;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	@Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.userId);
        hash = 83 * hash + Objects.hashCode(this.userName);
        hash = 83 * hash + Objects.hashCode(this.userEmail);
        hash = 83 * hash + Objects.hashCode(this.accessToken);
        hash = 83 * hash + Objects.hashCode(this.refreshToken);
        hash = 83 * hash + Objects.hashCode(this.expiresIn);
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
        final TokenDTO other = (TokenDTO) obj;
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
