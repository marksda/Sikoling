package org.Sikoling.main.restful.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.main.restful.person.PersonDTO;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 8350209564637166779L;
	private String id;
	private String email;
	private String password;
	private Boolean loginStatus;
	private Date registerDate;
	private Boolean statusInternal;
	private PersonDTO personDTO;
	private Boolean statusEnable;
	
	public UserDTO() {
		
	}
	
	public UserDTO(User u) {
		this.id = u.getId();
		this.email = u.getEmail();
		this.password = u.getPassword();
		this.loginStatus = u.getLoginStatus();
		this.registerDate = u.getRegisterDate();
		this.statusInternal = u.getStatusInternal();
	}
	
	public UserDTO(String id, String email, String password, Boolean loginStatus, Date registerDate,
			Boolean statusInternal) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.loginStatus = loginStatus;
		this.registerDate = registerDate;
		this.statusInternal = statusInternal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Boolean getStatusInternal() {
		return statusInternal;
	}

	public void setStatusInternal(Boolean statusInternal) {
		this.statusInternal = statusInternal;
	}
	
	public PersonDTO getPersonDTO() {
		return personDTO;
	}

	public void setPersonDTO(PersonDTO personDTO) {
		this.personDTO = personDTO;
	}

	public Boolean getStatusEnable() {
		return statusEnable;
	}

	public void setStatusEnable(Boolean statusEnable) {
		this.statusEnable = statusEnable;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 17;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.email);
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
        
        final UserDTO other = (UserDTO) obj;
        
        if (this.id != other.id) {
            return false;
        }
        
        if (this.email != other.email) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "UserDTO{" + "id=" + id + ", email=" + email + '}';	  
	}

	public User toUser() {
		return new User(id, email, password, loginStatus, registerDate, statusInternal, personDTO.toPerson(), statusEnable);
	}

}
