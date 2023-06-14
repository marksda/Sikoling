package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class AutorityPerusahaan implements Serializable {

	private static final long serialVersionUID = -5743948068191153726L;
	private final Autority authority;
	private final RegisterPerusahaan registerPerusahaan;
	
	public AutorityPerusahaan(Autority authority, RegisterPerusahaan registerPerusahaan) {
		this.authority = authority;
		this.registerPerusahaan = registerPerusahaan;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Autority getAuthority() {
		return authority;
	}
	
	public RegisterPerusahaan getRegisterPerusahaan() {
		return registerPerusahaan;
	}

	
	@Override
	public int hashCode() {
		int hash = 71;
		hash = 171 * hash + Objects.hashCode(this.authority.getId());
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
        
        final AutorityPerusahaan other = (AutorityPerusahaan) obj;
        
        if (!this.authority.getId().equals(other.getAuthority().getId())) {
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
				.concat(this.authority.getId())
				.concat(", ")
				.concat("idRegisterPerusahaan=")
				.concat(this.registerPerusahaan.getId())
				.concat("}");
	}	
	
}
