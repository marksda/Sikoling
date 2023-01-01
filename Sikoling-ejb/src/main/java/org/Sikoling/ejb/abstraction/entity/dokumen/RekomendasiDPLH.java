package org.Sikoling.ejb.abstraction.entity.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class RekomendasiDPLH extends Dokumen implements Serializable {

	private static final long serialVersionUID = -926660558566246710L;
	private final String nomor;
	private final LocalDate tanggal;
	private final String perihal;
	
	public RekomendasiDPLH(String id, String nama, KategoriDokumen kategoriDokumen, String nomor, LocalDate tanggal,
			String perihal) {
		super(id, nama, kategoriDokumen);
		this.nomor = nomor;
		this.tanggal = tanggal;
		this.perihal = perihal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNomor() {
		return nomor;
	}

	public LocalDate getTanggal() {
		return tanggal;
	}

	public String getPerihal() {
		return perihal;
	}
	
	@Override
	public int hashCode() {
		int hash = 137;
        hash = 141 * hash + Objects.hashCode(this.nomor);
        hash = 141 * hash + Objects.hashCode(this.getId());
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
        
        final RekomendasiUKLUPL other = (RekomendasiUKLUPL) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "RekomendasiDPLH{ id=" 
				.concat(this.getId())
				.concat(", nomor=")
				.concat(nomor)
				.concat("}");
	}

}
