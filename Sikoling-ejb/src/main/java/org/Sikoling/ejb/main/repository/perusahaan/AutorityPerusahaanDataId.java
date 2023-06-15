package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.Serializable;
import java.util.Objects;

public class AutorityPerusahaanDataId implements Serializable {

	private static final long serialVersionUID = 5171532190891483206L;
	private String autority;
	private String perusahaan;
	
	public AutorityPerusahaanDataId() {
	}
	
	public String getAutority() {
		return autority;
	}

	public void setAutority(String autority) {
		this.autority = autority;
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
        hash = 111 * hash + Objects.hashCode(this.autority);
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
        
        final AutorityPerusahaanDataId other = (AutorityPerusahaanDataId) obj;
        
        if (!this.autority.equals(other.getAutority())) {
            return false;
        }  
        
        if (!this.perusahaan.equals(other.getPerusahaan())) {
            return false;
        }  

        return true;
	}
	
	@Override
	public String toString() {
		return autority.concat("_").concat(perusahaan);	  
	}
}
