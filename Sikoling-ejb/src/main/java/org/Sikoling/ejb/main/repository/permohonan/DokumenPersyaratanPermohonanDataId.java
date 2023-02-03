package org.Sikoling.ejb.main.repository.permohonan;

import java.io.Serializable;
import java.util.Objects;

public class DokumenPersyaratanPermohonanDataId implements Serializable {

	private static final long serialVersionUID = -6147816271452015407L;
	private String registerPermohonan;
	private String registerDokumen;
	
	public DokumenPersyaratanPermohonanDataId() {
	}

	public String getRegisterPermohonan() {
		return registerPermohonan;
	}

	public void setRegisterPermohonan(String registerPermohonan) {
		this.registerPermohonan = registerPermohonan;
	}

	public String getRegisterDokumen() {
		return registerDokumen;
	}

	public void setRegisterDokumen(String registerDokumen) {
		this.registerDokumen = registerDokumen;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 1731;
        hash = 111 * hash + Objects.hashCode(this.registerPermohonan);
        hash = 111 * hash + Objects.hashCode(this.registerDokumen);
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
        
        final DokumenPersyaratanPermohonanDataId other = (DokumenPersyaratanPermohonanDataId) obj;
        
        if (!this.registerPermohonan.equals(other.getRegisterPermohonan())) {
            return false;
        }  
        
        if (!this.registerDokumen.equals(other.getRegisterDokumen())) {
            return false;
        }  

        return true;
	}
	

}
