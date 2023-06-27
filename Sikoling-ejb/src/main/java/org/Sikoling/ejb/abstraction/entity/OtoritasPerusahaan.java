package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class OtoritasPerusahaan implements Serializable {

	private static final long serialVersionUID = -5743948068191153726L;
	private final Otoritas otoritas;
	private final RegisterPerusahaan registerPerusahaan;
	
	public OtoritasPerusahaan(Otoritas otoritas, RegisterPerusahaan registerPerusahaan) {
		this.otoritas = otoritas;
		this.registerPerusahaan = registerPerusahaan;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Otoritas getOtoritas() {
		return otoritas;
	}
	
	public RegisterPerusahaan getRegisterPerusahaan() {
		return registerPerusahaan;
	}
	
	@Override
	public int hashCode() {
		int hash = 71;
		hash = 171 * hash + Objects.hashCode(this.otoritas.getId());
		hash = 171 * hash + Objects.hashCode(this.registerPerusahaan.getId());
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
        
        final OtoritasPerusahaan other = (OtoritasPerusahaan) obj;
        
        if (!this.otoritas.getId().equals(other.getOtoritas().getId())) {
            return false;
        }
        
        if (!this.registerPerusahaan.getId().equals(other.getRegisterPerusahaan().getId())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "AuthorityPerusahaan {"
				.concat("idAuthority=")
				.concat(this.otoritas.getId())
				.concat(", ")
				.concat("idRegisterPerusahaan=")
				.concat(this.registerPerusahaan.getId())
				.concat("}");
	}	

}
