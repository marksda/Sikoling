package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class DetailDokumen implements Serializable {

	private static final long serialVersionUID = -3166732876247970646L;
	private final Dokumen dokumen;
	private final String lokasiFile;

	public DetailDokumen(Dokumen dokumen, String lokasiFile) {
		this.dokumen = dokumen;
		this.lokasiFile = lokasiFile;
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

	public int hashCode() {
		int hash = 71;
		hash = 411 * hash + Objects.hashCode(this.dokumen.getId());
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
        
        final DetailDokumen other = (DetailDokumen) obj;
        
        if (!this.dokumen.getId().equals(other.getDokumen().getId())) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "DetailDokumen {"
				.concat("id=")
				.concat(this.dokumen.getId())
				.concat(", ")
				.concat("nama=")
				.concat(this.dokumen.getNama())
				.concat("}");
	}	

}
