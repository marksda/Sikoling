package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.util.Objects;


public class RegisterKbliDataId implements Serializable {

	private static final long serialVersionUID = -8172764954864064795L;
	private String registerDokumenOssData;
	private String kbliData;
	
	public RegisterKbliDataId() {
	}
	
	public String getRegisterDokumenOssData() {
		return registerDokumenOssData;
	}

	public void setRegisterDokumenOssData(String registerDokumenOssData) {
		this.registerDokumenOssData = registerDokumenOssData;
	}

	public String getKbliData() {
		return kbliData;
	}

	public void setKbliData(String kbliData) {
		this.kbliData = kbliData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int hashCode() {
		int hash = 131;
        hash = 191 * hash + Objects.hashCode(this.registerDokumenOssData);
        hash = 191 * hash + Objects.hashCode(this.kbliData);
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
        
        final RegisterKbliDataId other = (RegisterKbliDataId) obj;
        
        if (!this.registerDokumenOssData.equalsIgnoreCase(other.getRegisterDokumenOssData())) {
            return false;
        }  
        
        if (!this.kbliData.equalsIgnoreCase(other.getKbliData())) {
            return false;
        }  

        return true;
	}
	
	
}
