package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.Serializable;
import java.util.Objects;

public class PersonPerusahaanDataId implements Serializable {

	private static final long serialVersionUID = 5171532190891483206L;
	private String person;
	private String perusahaan;
	
	public PersonPerusahaanDataId() {
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getPerusahaan() {
		return perusahaan;
	}

	public void setPerusahaan(String perusahaan) {
		this.perusahaan = perusahaan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 131;
        hash = 111 * hash + Objects.hashCode(this.person);
        hash = 111 * hash + Objects.hashCode(this.perusahaan);
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
        
        final PersonPerusahaanDataId other = (PersonPerusahaanDataId) obj;
        
        if (!this.person.equals(other.getPerson())) {
            return false;
        }  
        
        if (!this.perusahaan.equals(other.getPerusahaan())) {
            return false;
        }  

        return true;
	}
	
	

}
