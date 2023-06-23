package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

	private static final long serialVersionUID = -9207509664824799299L;
	
	private final Credential credential;	
	private final Person person;
	
	public User(Credential credential, Person person) {
		this.credential = credential;
		this.person = person;
	}	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Credential getCredential() {
		return credential;
	}
	
	public Person getPerson() {
		return person;
	}
	
	@Override
	public int hashCode() {
		int hash = 713;
		hash = 113 * hash + Objects.hashCode(this.credential.getUserName());
		hash = 113 * hash + Objects.hashCode(this.person.getNik());
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
        
        if (!this.credential.getUserName().equals(other.credential.getUserName())) {
            return false;
        }
        
        if (!this.person.getNik().equals(other.person.getNik())) {
            return false;
        }
        
        return true;
	}

}
