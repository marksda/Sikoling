package org.Sikoling.main.restful.person;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Kontak;

public class KontakPersonDTO implements Serializable {

	private static final long serialVersionUID = -3805707147101223655L;
	private String telepone;
	private String email;
	
	public KontakPersonDTO() {		
	}
	
	public KontakPersonDTO(Kontak kontak) {	
		this.telepone = kontak.getTelepone();
		this.email = kontak.getEmail();
	}
	
	public KontakPersonDTO(String telepone, String email) {
		super();
		this.telepone = telepone;
		this.email = email;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 19;
        hash = 217 * hash + Objects.hashCode(this.telepone);
        hash = 83 * hash + Objects.hashCode(this.email);
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
        
        final KontakPersonDTO other = (KontakPersonDTO) obj;
        
        if (this.telepone != other.telepone) {
            return false;
        }
        
        if (this.email != other.email) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "KontakPersonDTO{" + "telepone=" + telepone + ", email=" + email + '}';
	}
	
	public Kontak toKontak() {
		return new Kontak(telepone, null, email);
	}

}
