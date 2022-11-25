package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.util.Objects;

public class RegisterDokumenDataId implements Serializable {

	private static final long serialVersionUID = 9054712343471948587L;
	private String perusahaanData;
	private String dokumenData;
	
	public RegisterDokumenDataId() {
	}
	
	public String getPerusahaanData() {
		return perusahaanData;
	}

	public void setPerusahaanData(String perusahaanData) {
		this.perusahaanData = perusahaanData;
	}

	public String getDokumenData() {
		return dokumenData;
	}

	public void setDokumenData(String dokumenData) {
		this.dokumenData = dokumenData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int hashCode() {
		int hash = 71;
        hash = 191 * hash + Objects.hashCode(this.perusahaanData);
        hash = 191 * hash + Objects.hashCode(this.dokumenData);
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
        
        final RegisterDokumenDataId other = (RegisterDokumenDataId) obj;
        
        if (!this.perusahaanData.equalsIgnoreCase(other.getPerusahaanData())) {
            return false;
        }  
        
        if (!this.dokumenData.equalsIgnoreCase(other.getDokumenData())) {
            return false;
        }  

        return true;
	}
	

}
