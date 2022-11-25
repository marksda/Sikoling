package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class RegisterDokumen implements Serializable {
	
	private static final long serialVersionUID = 5607669072989245707L;
	private final Perusahaan perusahaan;
	private final Dokumen dokumen;
	private final String lokasiFile;
	private final LocalDate tanggalRegistrasi;
	private final boolean statusBerlaku;
	private final Autorisasi autorisasi;

	public RegisterDokumen(Dokumen dokumen, Perusahaan perusahaan, String lokasiFile, LocalDate tanggalRegistrasi,
			boolean statusBerlaku, Autorisasi autorisasi) {
		this.dokumen = dokumen;
		this.perusahaan = perusahaan;
		this.lokasiFile = lokasiFile;
		this.tanggalRegistrasi = tanggalRegistrasi;
		this.statusBerlaku = statusBerlaku;
		this.autorisasi = autorisasi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Perusahaan getPerusahaan() {
		return perusahaan;
	}

	public Dokumen getDokumen() {
		return dokumen;
	}

	public String getLokasiFile() {
		return lokasiFile;
	}

	public LocalDate getTanggalRegistrasi() {
		return tanggalRegistrasi;
	}

	public boolean isStatusBerlaku() {
		return statusBerlaku;
	}

	public Autorisasi getAutorisasi() {
		return autorisasi;
	}
	
	@Override
	public int hashCode() {
		int hash = 13;
		hash = 131 * hash + Objects.hashCode(this.perusahaan.getId());
		hash = 131 * hash + Objects.hashCode(this.dokumen.getId());
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
        
        if ( !this.perusahaan.getId().equals(other.getPerusahaan().getId()) ) {
            return false;
        }
        
        if ( !this.dokumen.getId().equals(other.getDokumen().getId()) ) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "RegisterDokumen{" + "npwp perusahaan=" + perusahaan.getId() + ", id dokumen=" + dokumen.getId() + "}";
	}
	
}
