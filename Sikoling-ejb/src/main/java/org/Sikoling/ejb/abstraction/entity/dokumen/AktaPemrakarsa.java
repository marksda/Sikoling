package org.Sikoling.ejb.abstraction.entity.dokumen;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class AktaPemrakarsa implements Serializable {

	private static final long serialVersionUID = -3186081818421591782L;
	private final String nomor;
	private final Date tanggal;
	private final String namaNotaris;
	
	public AktaPemrakarsa(String nomor, Date tanggal, String namaNotaris) {
		super();
		this.nomor = nomor;
		this.tanggal = tanggal;
		this.namaNotaris = namaNotaris;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNomor() {
		return nomor;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public String getNamaNotaris() {
		return namaNotaris;
	}

	public int hashCode() {
		int hash = 17;
        hash = 121 * hash + Objects.hashCode(this.nomor);
        hash = 121 * hash + Objects.hashCode(this.tanggal.toString());
        hash = 121 * hash + Objects.hashCode(this.nomor);
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
        
        final AktaPemrakarsa other = (AktaPemrakarsa) obj;
        
        if (!this.nomor.equalsIgnoreCase(other.nomor)) {
            return false;
        }
        
        if (!Objects.equals(this.tanggal, other.tanggal)) {
            return false;
        }
        
        if (!this.namaNotaris.equalsIgnoreCase(other.namaNotaris)) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "AktaPemrakarsa{" + "nomor=" + nomor + ", namaNotaris=" + namaNotaris + ", tanggal=" + tanggal.toString() + "}";
	}
		
}
