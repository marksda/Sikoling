package org.Sikoling.main.restful.user;

import java.io.Serializable;

import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.Credential;
import org.Sikoling.main.restful.person.PersonDTO;

public class RegistrasiDTO implements Serializable {

	private static final long serialVersionUID = -4220243950689051507L;
	private CredentialDTO credential;
	private PersonDTO person;	
	
	public RegistrasiDTO() {
	}

	public RegistrasiDTO(Credential credential, Person person) {
		this.credential = new CredentialDTO(credential);
		this.person = new PersonDTO(person);
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
	

}
