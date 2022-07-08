package org.Sikoling.ejb.main.repository.person;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class KontakPersonData implements Serializable {

	private static final long serialVersionUID = -5315283687500062624L;
	private String telepone;
	private String email;
	
	public KontakPersonData() {
	}

	public String getTelepone() {
		return telepone;
	}

	public void setTelepone(String telepone) {
		this.telepone = telepone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
