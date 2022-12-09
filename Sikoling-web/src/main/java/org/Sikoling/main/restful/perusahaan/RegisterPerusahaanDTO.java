package org.Sikoling.main.restful.perusahaan;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.main.restful.person.PersonDTO;

public class RegisterPerusahaanDTO implements Serializable {

	private static final long serialVersionUID = 2393298210508926234L;
	private LocalDate tanggalRegistrasi;
	private PersonDTO kreator;
	private PersonDTO verifikator;
	private PerusahaanDTO perusahaan;
	
	public RegisterPerusahaanDTO() {
	}
	
	public RegisterPerusahaanDTO(RegisterPerusahaan t) {
		this.tanggalRegistrasi = t.getTanggalRegistrasi();
		this.kreator = new PersonDTO(t.getKreator());
		this.verifikator = new PersonDTO(t.getVerifikator());
		this.perusahaan = new PerusahaanDTO(t.getPerusahaan());
	}

	public LocalDate getTanggalRegistrasi() {
		return tanggalRegistrasi;
	}

	public void setTanggalRegistrasi(LocalDate tanggalRegistrasi) {
		this.tanggalRegistrasi = tanggalRegistrasi;
	}

	public PersonDTO getKreator() {
		return kreator;
	}

	public void setKreator(PersonDTO kreator) {
		this.kreator = kreator;
	}

	public PersonDTO getVerifikator() {
		return verifikator;
	}

	public void setVerifikator(PersonDTO verifikator) {
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
	
	@Override
	public int hashCode() {
		int hash = 91;
		hash = 131 * hash + Objects.hashCode(perusahaan.getId());
		hash = 131 * hash + Objects.hashCode(kreator.getNik());
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
				tanggalRegistrasi, 
				kreator.toPerson(), 
				verifikator != null?verifikator.toPerson():null, 
				perusahaan.toPerusahaan()
				);
	}

}
