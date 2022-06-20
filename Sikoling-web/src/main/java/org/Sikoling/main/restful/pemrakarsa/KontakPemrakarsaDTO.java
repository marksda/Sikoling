package org.Sikoling.main.restful.pemrakarsa;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.KontakPemrakarsa;

public class KontakPemrakarsaDTO implements Serializable {

	private static final long serialVersionUID = -7197745612958948259L;
	private String telepone;
	private String fax;
	private String email;
	
	public KontakPemrakarsaDTO() {		
	}
	
	public KontakPemrakarsaDTO(KontakPemrakarsa kontakPemrakarsa) {		
		this.telepone = kontakPemrakarsa.getTelepone();
		this.fax = kontakPemrakarsa.getFax();
		this.email = kontakPemrakarsa.getEmail();
	}
	
	public KontakPemrakarsaDTO(String telepone, String fax, String email) {
		super();
		this.telepone = telepone;
		this.fax = fax;
		this.email = email;
	}

	public String getTelepone() {
		return telepone;
	}

	public void setTelepone(String telepone) {
		this.telepone = telepone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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
	
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + Objects.hashCode(this.telepone);
		hash = 31 * hash + Objects.hashCode(this.fax);
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
        
        final KontakPemrakarsaDTO other = (KontakPemrakarsaDTO) obj;
        
        if (!this.telepone.equals(other.telepone)) {
            return false;
        }
        
        if (!this.fax.equals(other.fax)) {
            return false;
        }
        
        if (!this.email.equals(other.email)) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "KontakPemrakarsaDTO{" + "telepone=" + telepone + ", fax=" + fax  + ", email=" + email + "}";
	}

	public KontakPemrakarsa toKontakPemrakarsa() {
		return new KontakPemrakarsa(telepone, fax, email);
	}
}
