package org.Sikoling.main.restful.user;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.main.restful.person.PersonDTO;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 8350209564637166779L;
	private CredentialDTO credential;
	private PersonDTO person;
	
	public UserDTO() {		
	}
	
	public UserDTO(User t) {
		if(t != null) {
			this.credential = t.getCredential() != null ? new CredentialDTO(t.getCredential()):null;
			this.person = t.getPerson() != null ? new PersonDTO(t.getPerson()):null;
		}
	}
	
	public CredentialDTO getCredential() {
		return credential;
	}

	public void setCredential(CredentialDTO credential) {
		this.credential = credential;
	}

	public PersonDTO getPerson() {
		return person;
	}

	public void setPerson(PersonDTO person) {
		this.person = person;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int hashCode() {
		int hash = 171;
        hash = 31 * hash + Objects.hashCode(this.credential.getUserName());
        hash = 31 * hash + Objects.hashCode(this.person.getNik());
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
        
        if (this.credential.getUserName() != other.credential.getUserName()) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "UserDTO{" + "userName=" + this.credential.getUserName() + '}';	  
	}

	public User toUser() {
		return new User(
				this.credential != null ? this.credential.toCredential():null, 
				this.person != null ? this.person.toPerson():null
				);
	}

}
