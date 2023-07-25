package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.StatusDokumen;

public class RegisterDokumen implements Serializable {	
	private static final long serialVersionUID = 5607669072989245707L;
	private final String id;
	private final Dokumen dokumen;
	private final RegisterPerusahaan registerPerusahaan;
	private final String lokasiFile;
	private final StatusDokumen statusDokumen;
	private final LocalDate tanggalRegistrasi;
	private final Otoritas uploader;
	private final Boolean statusVerified;

	public RegisterDokumen(String id, Dokumen dokumen, RegisterPerusahaan registerPerusahaan, String lokasiFile,
			StatusDokumen statusDokumen, LocalDate tanggalRegistrasi, Otoritas uploader, Boolean statusVerified) {
		this.id = id;
		this.dokumen = dokumen;
		this.registerPerusahaan = registerPerusahaan;
		this.lokasiFile = lokasiFile;
		this.statusDokumen = statusDokumen;
		this.tanggalRegistrasi = tanggalRegistrasi;
		this.uploader = uploader;
		this.statusVerified = statusVerified;
	}

	public String getId() {
		return id;
	}

	public String getLokasiFile() {
		return lokasiFile;
	}

	public StatusDokumen getStatusDokumen() {
		return statusDokumen;
	}

	public LocalDate getTanggalRegistrasi() {
		return tanggalRegistrasi;
	}
	
	public Dokumen getDokumen() {
		return dokumen;
	}

	public RegisterPerusahaan getRegisterPerusahaan() {
		return registerPerusahaan;
	}

	public Otoritas getUploader() {
		return uploader;
	}

	public Boolean getStatusVerified() {
		return statusVerified;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = 141 * hash + Objects.hashCode(dokumen.getId());
		hash = 141 * hash + Objects.hashCode(registerPerusahaan.getId());
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
        
        final RegisterDokumen other = (RegisterDokumen) obj;
        if ( !this.dokumen.getId().equals(other.getDokumen().getId()) ) {
            return false;
        }
        
        if ( !this.registerPerusahaan.getId().equals(other.getRegisterPerusahaan().getId()) ) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "RegisterDokumen{" + "idDokumen=" + dokumen.getId() + ", idPerusahaan=" + registerPerusahaan.getId() + "}";
	}
	
}
