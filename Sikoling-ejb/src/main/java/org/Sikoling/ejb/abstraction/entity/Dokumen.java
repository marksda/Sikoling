package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Dokumen implements Serializable {

	private static final long serialVersionUID = -1869365745189974891L;
	private final String id;
	private final String nama;
	private final KategoriDokumenPerusahaan kategoriDokumenPerusahaan;
	
	public Dokumen(String id, String nama, KategoriDokumenPerusahaan kategoriDokumenPerusahaan) {
		this.id = id;
		this.nama = nama;
		this.kategoriDokumenPerusahaan = kategoriDokumenPerusahaan;
	}	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}

	public KategoriDokumenPerusahaan getKategoriDokumenPerusahaan() {
		return kategoriDokumenPerusahaan;
	}

	public int hashCode() {
		int hash = 19;
		hash = 41 * hash + Objects.hashCode(this.id);
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
        
        final Dokumen other = (Dokumen) obj;
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "Dokumen {"
				.concat("id=")
				.concat(this.id)
				.concat(", ")
				.concat("nama=")
				.concat(this.nama)
				.concat("}");
	}	
}
