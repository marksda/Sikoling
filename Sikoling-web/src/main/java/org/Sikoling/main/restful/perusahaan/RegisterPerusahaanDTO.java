package org.Sikoling.main.restful.perusahaan;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.main.restful.authority.AuthorityDTO;

public class RegisterPerusahaanDTO implements Serializable {

	private static final long serialVersionUID = 2393298210508926234L;
	private String id;
	private LocalDate tanggalRegistrasi;
	private AuthorityDTO kreator;
	private AuthorityDTO verifikator;
	private PerusahaanDTO perusahaan;
	private Boolean statusVerifikasi;	
	
	public RegisterPerusahaanDTO() {
	}
	
	public RegisterPerusahaanDTO(RegisterPerusahaan t) {
		if(t != null) {
			this.id = t.getId();
			this.tanggalRegistrasi = t.getTanggalRegistrasi();
			this.kreator = t.getKreator() != null ? new AuthorityDTO(t.getKreator()) : null;
			this.verifikator = t.getVerifikator() != null ? new AuthorityDTO(t.getVerifikator()) : null;
			this.perusahaan = t.getPerusahaan() != null ? new PerusahaanDTO(t.getPerusahaan()) : null;
			this.statusVerifikasi = t.getStatusVerifikasi();
		}
	}

	public LocalDate getTanggalRegistrasi() {
		return tanggalRegistrasi;
	}

	public void setTanggalRegistrasi(LocalDate tanggalRegistrasi) {
		this.tanggalRegistrasi = tanggalRegistrasi;
	}

	public AuthorityDTO getKreator() {
		return kreator;
	}

	public void setKreator(AuthorityDTO kreator) {
		this.kreator = kreator;
	}

	public AuthorityDTO getVerifikator() {
		return verifikator;
	}

	public void setVerifikator(AuthorityDTO verifikator) {
		this.verifikator = verifikator;
	}

	public PerusahaanDTO getPerusahaan() {
		return perusahaan;
	}

	public void setPerusahaan(PerusahaanDTO perusahaan) {
		this.perusahaan = perusahaan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Boolean getStatusVerifikasi() {
		return statusVerifikasi;
	}

	public void setStatusVerifikasi(Boolean statusVerifikasi) {
		this.statusVerifikasi = statusVerifikasi;
	}

	@Override
	public int hashCode() {
		int hash = 91;
		hash = 131 * hash + Objects.hashCode(perusahaan.getId());
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
        
        final RegisterPerusahaanDTO other = (RegisterPerusahaanDTO) obj;
        if ( !this.perusahaan.getId().equals(other.getPerusahaan().getId()) ) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "RegisterPerusahaanDTO { npwp="
				.concat(perusahaan.getId())
				.concat(", nama=")
				.concat(perusahaan.getNama())
				.concat(". tanggal registrasi =")
				.concat(tanggalRegistrasi.toString())
				.concat("}");
	}
	
	public RegisterPerusahaan toRegisterPerusahaan() {
		return new RegisterPerusahaan(
				id,
				tanggalRegistrasi, 
				kreator != null ? kreator.toAuthority() : null, 
				verifikator != null ? verifikator.toAuthority() : null, 
				perusahaan.toPerusahaan(),
				statusVerifikasi
				);
	}

}
