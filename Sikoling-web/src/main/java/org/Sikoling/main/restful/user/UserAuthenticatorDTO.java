package org.Sikoling.main.restful.user;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.UserAuthenticator;

public class UserAuthenticatorDTO implements Serializable {

	private static final long serialVersionUID = 8997263655797643037L;
	private String userName;
	private String password;
		
	
	public UserAuthenticatorDTO() {		
	}

	public UserAuthenticatorDTO(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public UserAuthenticatorDTO(UserAuthenticator userAuthenticator) {
		this.userName = userAuthenticator.getUserName();
		this.password = userAuthenticator.getPassword();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
	public int hashCode() {
		int hash = 103;
		hash = 13 * hash + Objects.hashCode(this.userName);
		hash = 13 * hash + Objects.hashCode(this.password);
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
        
        final UserAuthenticatorDTO other = (UserAuthenticatorDTO) obj;
        
        if (!this.userName.equals(other.userName)) {
            return false;
        }
        
        if (!this.password.equals(other.password)) {
            return false;
        }
        
        return true;
	}
	
	@Override
    public String toString() {
        return "UserAuthenticatorDTO{" + "userName=" + userName + ", password=" + password + '}';
    }

	public UserAuthenticator toUserAuthenticator() {
		return new UserAuthenticator(userName, password);
	}
}
