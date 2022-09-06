package org.Sikoling.main.restful.user;

import java.io.Serializable;

import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.UserAuthenticator;
import org.Sikoling.main.restful.person.PersonDTO;

public class RegistrasiDTO implements Serializable {

	private static final long serialVersionUID = -4220243950689051507L;
	private UserAuthenticatorDTO auth;
	private PersonDTO person;	
	
	public RegistrasiDTO() {
	}

	public RegistrasiDTO(UserAuthenticator userAuthenticator, Person person) {
		super();
		this.auth = new UserAuthenticatorDTO(userAuthenticator);
		this.person = new PersonDTO(person);
	}

	public UserAuthenticatorDTO getAuth() {
		return auth;
	}

	public void setAuth(UserAuthenticatorDTO auth) {
		this.auth = auth;
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
	

}
