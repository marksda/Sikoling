package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {

	private static final long serialVersionUID = -9207509664824799299L;
	
	private final String id;
	private final String userName;
	private final String password;
	private final Boolean loginStatus;
	private final Date registerDate;
	private final Boolean statusInternal;
	private final Person person;
	private final Boolean statusEnable;

	public User(String id) {
		super();
		this.id = id;
		this.userName = null;
		this.password = null;
		this.loginStatus = null;
		this.registerDate = null;
		this.statusInternal = null;
		this.person = null;
		this.statusEnable = true;
	}
	
	public User(String id, String email, Person person, Date registerDate, Boolean statusInternal, Boolean statusEnable) {
		super();
		this.id = id;
		this.userName = email;
		this.password = "";
		this.loginStatus = false;
		this.registerDate = registerDate;
		this.statusInternal = statusInternal;
		this.person = person;
		this.statusEnable = statusEnable;
	}

	public User(String id, String email, String password, Boolean loginStatus, Date registerDate,
			Boolean statusInternal, Person person, Boolean statusEnable) {
		super();
		this.id = id;
		this.userName = email;
		this.password = password;
		this.loginStatus = loginStatus;
		this.registerDate = registerDate;
		this.statusInternal = statusInternal;
		this.person = person;
		this.statusEnable = statusEnable;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public String getUserName() {
		return userName;
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
        
        final User other = (User) obj;
        
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
		return "User{" + "email=" + userName + ", password=" + password + "}";
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	
	public Person getPerson() {
		return person;
	}

	public Boolean getStatusEnable() {
		return statusEnable;
	}		
	
}
