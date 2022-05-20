package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {

	private static final long serialVersionUID = -9207509664824799299L;
	
	private final String email;
	private final String password;
	private final String loginStatus;
	private final Date registerDate;
	
	public User(String email, String password, String loginStatus, Date registerDate) {
		super();
		this.email = email;
		this.password = password;
		this.loginStatus = loginStatus;
		this.registerDate = registerDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + Objects.hashCode(this.email);
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
        
        final User other = (User) obj;
        
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "User{" + "email=" + email + ", password=" + password + "}";
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public Date getRegisterDate() {
		return registerDate;
	}
		
}
