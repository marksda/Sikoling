package org.Sikoling.ejb.abstraction.entity.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.json.JsonObject;

public class RekomendasiUKLUPL extends Dokumen implements Serializable {
	
	private static final long serialVersionUID = 1698034215951652720L;
	private final String nomor;
	private final LocalDate tanggal;
	
	public RekomendasiUKLUPL(String id, String nama, KategoriDokumen kategoriDokumen, JsonObject detailAttributeDokumen,
			String nomor, LocalDate tanggal) {
		super(id, nama, kategoriDokumen, detailAttributeDokumen);
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
        
        if (!this.nomor.equalsIgnoreCase(other.nomor)) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "RekomendasiUKLUPL{ id=" 
				.concat(this.getId())
				.concat(", nomor=")
				.concat(nomor)
				.concat("}");
	}
	
}
