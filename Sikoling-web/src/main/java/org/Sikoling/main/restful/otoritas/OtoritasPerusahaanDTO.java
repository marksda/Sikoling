package org.Sikoling.main.restful.otoritas;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.OtoritasPerusahaan;
import org.Sikoling.main.restful.perusahaan.RegisterPerusahaanDTO;

public class OtoritasPerusahaanDTO implements Serializable {

	private static final long serialVersionUID = 491207506437595211L;
	private OtoritasDTO otoritas;
	private RegisterPerusahaanDTO registerPerusahaan;
	
	public OtoritasPerusahaanDTO() {
	}
	
	public OtoritasPerusahaanDTO(OtoritasPerusahaan t) {
		if(t != null) {
			this.otoritas = t.getOtoritas() != null ?
					new OtoritasDTO(t.getOtoritas()):null;
			this.registerPerusahaan = t.getRegisterPerusahaan() != null ?
					new RegisterPerusahaanDTO(t.getRegisterPerusahaan()):null;
		}
	}

	public OtoritasDTO getOtoritas() {
		return otoritas;
	}

	public void setOtoritas(OtoritasDTO otoritas) {
		this.otoritas = otoritas;
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
        
        if (!this.otoritas.getId().equals(other.getOtoritas().getId())) {
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
		hash = 171 * hash + Objects.hashCode(this.otoritas.getId());
		hash = 171 * hash + Objects.hashCode(this.registerPerusahaan.getId());
		return hash;
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
	
	public OtoritasPerusahaan toOtoritasPerusahaan() {
		return new OtoritasPerusahaan(
				this.otoritas != null ? this.otoritas.toOtoritas():null,
				this.registerPerusahaan != null ? this.registerPerusahaan.toRegisterPerusahaan():null
				);
	}
	
}
