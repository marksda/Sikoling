package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.util.Objects;


public class RegisterKbliDataId implements Serializable {

	private static final long serialVersionUID = -8172764954864064795L;
	private String nib;
	private String kbli;
	
	public RegisterKbliDataId() {
	}
	
	public String getNib() {
		return nib;
	}

	public void setNib(String nib) {
		this.nib = nib;
	}

	public String getKbli() {
		return kbli;
	}
	
	public void setKbli(String kbli) {
		this.kbli = kbli;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 131;
        hash = 171 * hash + Objects.hashCode(this.nib);
        hash = 171 * hash + Objects.hashCode(this.kbli);
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
        
        if (!this.nib.equalsIgnoreCase(other.getNib())) {
            return false;
        }  
        
        if (!this.kbli.equalsIgnoreCase(other.getKbli())) {
            return false;
        }  

        return true;
	}
		
}
