package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;

public class RegisterDokumen implements Serializable {	
	private static final long serialVersionUID = 5607669072989245707L;
	private final String id;
	private final Dokumen dokumen;
	private final Perusahaan perusahaan;
	private final String lokasiFile;
	private final LocalDate tanggalRegistrasi;
	private final Authority uploader;
	
	public RegisterDokumen(String id, Dokumen dokumen, Perusahaan perusahaan, String lokasiFile,
			LocalDate tanggalRegistrasi, Authority uploader) {
		this.id = id;
		this.dokumen = dokumen;
		this.perusahaan = perusahaan;
		this.lokasiFile = lokasiFile;
		this.tanggalRegistrasi = tanggalRegistrasi;
		this.uploader = uploader;
	}
	
	public String getId() {
		return id;
	}

	public String getLokasiFile() {
		return lokasiFile;
	}

	public LocalDate getTanggalRegistrasi() {
		return tanggalRegistrasi;
	}
	
	public Dokumen getDokumen() {
		return dokumen;
	}

	public Perusahaan getPerusahaan() {
		return perusahaan;
	}

	public Authority getUploader() {
		return uploader;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = 141 * hash + Objects.hashCode(dokumen.getId());
		hash = 141 * hash + Objects.hashCode(perusahaan.getId());
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
        
        if ( !this.perusahaan.getId().equals(other.getPerusahaan().getId()) ) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "RegisterDokumen{" + "idDokumen=" + dokumen.getId() + ", idPerusahaan=" + perusahaan.getId() + "}";
	}
	
}
