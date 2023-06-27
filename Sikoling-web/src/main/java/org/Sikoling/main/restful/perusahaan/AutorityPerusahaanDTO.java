package org.Sikoling.main.restful.perusahaan;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.OtoritasPerusahaan;
import org.Sikoling.main.restful.otoritas.OtoritasDTO;

public class AutorityPerusahaanDTO implements Serializable {

	private static final long serialVersionUID = 491207506437595211L;
	private OtoritasDTO authority;
	private RegisterPerusahaanDTO registerPerusahaan;
	
	public AutorityPerusahaanDTO() {
	}
	
	public AutorityPerusahaanDTO(OtoritasPerusahaan t) {
		if(t != null) {
			this.authority = t.getOtoritas() != null ?
					new OtoritasDTO(t.getOtoritas()):null;
			this.registerPerusahaan = t.getRegisterPerusahaan() != null ?
					new RegisterPerusahaanDTO(t.getRegisterPerusahaan()):null;
		}
	}

	public OtoritasDTO getAuthority() {
		return authority;
	}

	public void setAuthority(OtoritasDTO authority) {
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
        
        final OtoritasPerusahaan other = (OtoritasPerusahaan) obj;
        
        if (!this.authority.getId().equals(other.getOtoritas().getId())) {
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
	
	public OtoritasPerusahaan toAutorityPerusahaan() {
		return new OtoritasPerusahaan(
				this.authority != null ? this.authority.toOtoritas():null,
				this.registerPerusahaan != null ? this.registerPerusahaan.toRegisterPerusahaan():null
				);
	}
	
}
