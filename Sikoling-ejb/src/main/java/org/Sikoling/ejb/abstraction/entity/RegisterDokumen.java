package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class RegisterDokumen implements Serializable {	
	private static final long serialVersionUID = 5607669072989245707L;
	private final Dokumen dokumen;
	private final String lokasiFile;
	private final LocalDate tanggalRegistrasi;
	private final boolean statusBerlaku;

	public RegisterDokumen(Dokumen dokumen, String lokasiFile, 
			LocalDate tanggalRegistrasi, boolean statusBerlaku) {
		this.dokumen = dokumen;
		this.lokasiFile = lokasiFile;
		this.tanggalRegistrasi = tanggalRegistrasi;
		this.statusBerlaku = statusBerlaku;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	@Override
	public int hashCode() {
		int hash = 17;
		hash = 141 * hash + Objects.hashCode(this.dokumen.getId());
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
        
        return true;
	}

	@Override
	public String toString() {
		return "RegisterDokumen{" + "idDokumen=" + dokumen.getId() + ", namaDokumen=" + dokumen.getNama() + "}";
	}
	
}
