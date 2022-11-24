package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.RegisterKbli;

public class RegisterKbliDTO implements Serializable {

	private static final long serialVersionUID = -4442022463611044537L;
	private String nib;
	private String kode;
	
	public RegisterKbliDTO() {
	}
	
	public RegisterKbliDTO(RegisterKbli t) {
		this.nib = t.getNib();
		this.kode = t.getKode();
	}

	public String getNib() {
		return nib;
	}

	public void setNib(String nib) {
		this.nib = nib;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 137;
		hash = 171 * hash + Objects.hashCode(this.nib);
        hash = 171 * hash + Objects.hashCode(this.kode);
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
        
        if (!this.nib.equalsIgnoreCase(other.getNib())) {
            return false;
        }     
        
        if (!this.kode.equalsIgnoreCase(other.getKode())) {
            return false;
        }  

        return true;
	}
	
	@Override
	public String toString() {
		return "RegisterKbliDTO{nib="
				.concat(nib)
				.concat(", kode = ")
				.concat(kode)
				.concat("}");	  
	}

	public RegisterKbli toRegisterKbli() {
		return new RegisterKbli(nib, kode);
	}
	
	

}
