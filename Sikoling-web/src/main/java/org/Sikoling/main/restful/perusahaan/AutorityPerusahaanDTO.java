package org.Sikoling.main.restful.perusahaan;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.AutorityPerusahaan;
import org.Sikoling.main.restful.autority.AutorityDTO;

public class AutorityPerusahaanDTO implements Serializable {

	private static final long serialVersionUID = 491207506437595211L;
	private AutorityDTO authority;
	private RegisterPerusahaanDTO registerPerusahaan;
	
	public AutorityPerusahaanDTO() {
	}
	
	public AutorityPerusahaanDTO(AutorityPerusahaan t) {
		if(t != null) {
			this.authority = t.getAuthority() != null ?
					new AutorityDTO(t.getAuthority()):null;
			this.registerPerusahaan = t.getRegisterPerusahaan() != null ?
					new RegisterPerusahaanDTO(t.getRegisterPerusahaan()):null;
		}
	}

	public AutorityDTO getAuthority() {
		return authority;
	}

	public void setAuthority(AutorityDTO authority) {
		this.authority = authority;
	}

	public RegisterPerusahaanDTO getRegisterPerusahaan() {
		return registerPerusahaan;
	}

	public void setRegisterPerusahaan(RegisterPerusahaanDTO registerPerusahaan) {
		this.registerPerusahaan = registerPerusahaan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public int hashCode() {
		int hash = 71;
		hash = 171 * hash + Objects.hashCode(this.authority.getId());
		hash = 171 * hash + Objects.hashCode(this.registerPerusahaan.getId());
		return hash;
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
