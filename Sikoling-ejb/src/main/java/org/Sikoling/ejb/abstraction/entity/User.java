package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {

	private static final long serialVersionUID = -9207509664824799299L;
	
	private final String id;
	private final String email;
	private final String password;
	private final Boolean loginStatus;
	private final Date registerDate;
	private final Boolean statusInternal;

	public User(String id, String email, String password, Boolean loginStatus, Date registerDate,
			Boolean statusInternal) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.loginStatus = loginStatus;
		this.registerDate = registerDate;
		this.statusInternal = statusInternal;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public Boolean getLoginStatus() {
		return loginStatus;
	}

	public Boolean getStatusInternal() {
		return statusInternal;
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
        
        if (!this.email.equals(other.email)) {
            return false;
        }
        
        if (!this.password.equals(other.password)) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "User{" + "email=" + email + ", password=" + password + "}";
	}

	public Date getRegisterDate() {
		return registerDate;
	}
		
}
