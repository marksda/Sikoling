package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class RegisterPerusahaan implements Serializable {

	private static final long serialVersionUID = -4021307873874947821L;
	private final LocalDate tanggalRegistrasi;
	private final Person kreator;
	private final Person verifikator;
	private final Perusahaan perusahaan;
	
	public RegisterPerusahaan(LocalDate tanggalRegistrasi, Person kreator, Person verifikator, Perusahaan perusahaan) {
		super();
		this.tanggalRegistrasi = tanggalRegistrasi;
		this.kreator = kreator;
		this.verifikator = verifikator;
		this.perusahaan = perusahaan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LocalDate getTanggalRegistrasi() {
		return tanggalRegistrasi;
	}

	public Person getKreator() {
		return kreator;
	}

	public Person getVerifikator() {
		return verifikator;
	}

	public Perusahaan getPerusahaan() {
		return perusahaan;
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
        
        final RegisterPerusahaan other = (RegisterPerusahaan) obj;
        if ( !this.perusahaan.getId().equals(other.getPerusahaan().getId()) ) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "RegisterPerusahaan { npwp="
				.concat(perusahaan.getId())
				.concat(", nama=")
				.concat(perusahaan.getNama())
				.concat(". tanggal registrasi =")
				.concat(tanggalRegistrasi.toString())
				.concat("}");
	}
	
}
