package org.Sikoling.ejb.abstraction.entity.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class DokumenGenerik extends Dokumen implements Serializable {
	
	private static final long serialVersionUID = 3850586104864070141L;
	private final String nomor;
	private final LocalDate tanggal;
	
	public DokumenGenerik(String id, String nama, KategoriDokumen kategoriDokumen, String nomor, LocalDate tanggal) {
		super(id, nama, kategoriDokumen);
		this.nomor = nomor;
		this.tanggal = tanggal;
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

	@Override
	public int hashCode() {
		int hash = 173;
        hash = 121 * hash + Objects.hashCode(this.getId());
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
        
        final DokumenAktaPendirian other = (DokumenAktaPendirian) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "DokumenGenerik{"
				.concat("id=")
				.concat(this.getId())
				.concat(", nomor=")
				.concat(this.nomor)
				.concat(", tanggal=")
				.concat(tanggal.toString())
				.concat("}");
	}
		
}
