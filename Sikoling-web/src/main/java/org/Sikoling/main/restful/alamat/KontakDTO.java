package org.Sikoling.main.restful.alamat;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Kontak;

public class KontakDTO implements Serializable {

	private static final long serialVersionUID = -3805707147101223655L;
	private String telepone;
	private String fax;
	private String email;
	
	public KontakDTO() {		
	}
	
	public KontakDTO(Kontak kontak) {	
		if(kontak != null) {
			this.telepone = kontak.getTelepone();
			this.fax = kontak.getFax();
			this.email = kontak.getEmail();
		}
		else {
			this.telepone = null;
			this.fax = null;
			this.email = null;
		}
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

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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
        
        final KontakDTO other = (KontakDTO) obj;
        
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
		return new Kontak(telepone, fax, email);
	}

}
