package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class RegisterPerusahaan implements Serializable {

	private static final long serialVersionUID = -4021307873874947821L;
	private final String id;
	private final LocalDate tanggalRegistrasi;
	private final Otoritas kreator;
	private final Otoritas verifikator;
	private final Perusahaan perusahaan;
	private final Boolean statusVerifikasi;	
	
	public RegisterPerusahaan(String id, LocalDate tanggalRegistrasi, Otoritas kreator, Otoritas verifikator,
			Perusahaan perusahaan, Boolean statusVerifikasi) {
		this.id = id;
		this.tanggalRegistrasi = tanggalRegistrasi;
		this.kreator = kreator;
		this.verifikator = verifikator;
		this.perusahaan = perusahaan;
		this.statusVerifikasi = statusVerifikasi;
//		this.pengakses = pengakses;
	}

	public String getId() {
		return id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LocalDate getTanggalRegistrasi() {
		return tanggalRegistrasi;
	}

	public Otoritas getKreator() {
		return kreator;
	}

	public Otoritas getVerifikator() {
		return verifikator;
	}

	public Perusahaan getPerusahaan() {
		return perusahaan;
	}
	
	public Boolean getStatusVerifikasi() {
		return statusVerifikasi;
	}

	@Override
	public int hashCode() {
		int hash = 41;
		hash = 131 * hash + Objects.hashCode(id);
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
        if ( !this.id.equals(other.getId()) ) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "RegisterPerusahaan { id="
				.concat(id)
				.concat(", nama=")
				.concat(perusahaan.getNama())
				.concat(". tanggal registrasi =")
				.concat(tanggalRegistrasi.toString())
				.concat("}");
	}
	
}
