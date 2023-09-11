package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.RegisterKbli;

public class RegisterKbliDTO implements Serializable {

	private static final long serialVersionUID = -4442022463611044537L;
	private String nib;
	private KbliDTO kbli;
	
	public RegisterKbliDTO() {
	}
	
	public RegisterKbliDTO(RegisterKbli t) {
		if(t!= null) {
			this.nib = t.getNib();
			this.kbli = t.getKbli() != null ? new KbliDTO(t.getKbli()):null;
		}
	}	
	
	public String getNib() {
		return nib;
	}

	public void setNib(String nib) {
		this.nib = nib;
	}

	public KbliDTO getKbli() {
		return kbli;
	}

	public void setKbli(KbliDTO d) {
		this.kbli = d;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 137;
		hash = 171 * hash + Objects.hashCode(this.nib);
        hash = 171 * hash + Objects.hashCode(this.kbli.getKode());
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
        
        final RegisterKbliDTO other = (RegisterKbliDTO) obj;
        
        if ( !this.nib.equalsIgnoreCase(other.nib) ) {
            return false;
        }
        
        if ( !this.kbli.getKode().equalsIgnoreCase(other.kbli.getKode()) ) {
            return false;
        }

        return true;
	}
	
	@Override
	public String toString() {
		return "RegisterKbliDTO{nib="
				.concat(nib)
				.concat(", kode_kbli = ")
				.concat(kbli.getKode())
				.concat("}");	  
	}

	public RegisterKbli toRegisterKbli() {
		return new RegisterKbli(
				this.nib, 
				this.kbli != null ? this.kbli.toKbli():null
				);
	}	

}
