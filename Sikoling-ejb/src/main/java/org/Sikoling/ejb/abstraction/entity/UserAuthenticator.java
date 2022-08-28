package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserAuthenticator implements Serializable {

	private static final long serialVersionUID = -6621586842419432649L;
	private final String userName;
	private final String password;
	
	public UserAuthenticator(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
	public int hashCode() {
		int hash = 11;
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
        
        final UserAuthenticator other = (UserAuthenticator) obj;
        
        if (!this.userName.equals(other.userName)) {
            return false;
        }
        
        if (!this.password.equals(other.password)) {
            return false;
        }
        
        return true;
	}

	
}
