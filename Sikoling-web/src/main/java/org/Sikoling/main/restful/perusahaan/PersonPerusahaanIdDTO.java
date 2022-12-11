package org.Sikoling.main.restful.perusahaan;

import java.io.Serializable;
import java.util.Objects;

public class PersonPerusahaanIdDTO implements Serializable {

	private static final long serialVersionUID = -908637131174953705L;
	private String idPerson;
	private String idPerusahaan;
	
	public PersonPerusahaanIdDTO() {
	}

	public String getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(String person) {
		this.idPerson = person;
	}

	public String getIdPerusahaan() {
		return idPerusahaan;
	}

	public void setIdPerusahaan(String perusahaan) {
		this.idPerusahaan = perusahaan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 37;
		hash = 121 * hash + Objects.hashCode(idPerson);
		hash = 121 * hash + Objects.hashCode(idPerusahaan);
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
        
        final PersonPerusahaanIdDTO other = (PersonPerusahaanIdDTO) obj;
        
        if (!this.idPerson.equals(other.getIdPerson())) {
            return false;
        }
        
        if (!this.idPerusahaan.equals(other.getIdPerusahaan())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "PersonPerusahaanIdDTO {"
				.concat("personId=")
				.concat(idPerson)
				.concat(", perusahaanId=")
				.concat(idPerusahaan)
				.concat("}");
	}
	
}
