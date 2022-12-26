package org.Sikoling.ejb.abstraction.entity.dokumen;

import java.io.Serializable;
import java.util.Objects;

public class Dokumen implements Serializable {
	
	private static final long serialVersionUID = -1869365745189974891L;
	private final String id;
	private final String nama;	
	private final KategoriDokumen kategoriDokumen;
	
	public Dokumen(String id, String nama, KategoriDokumen kategoriDokumen) {
		this.id = id;
		this.nama = nama;
		this.kategoriDokumen = kategoriDokumen;
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

	public KategoriDokumen getKategoriDokumen() {
		return kategoriDokumen;
	}

	public int hashCode() {
		int hash = 91;
		hash = 13 * hash + Objects.hashCode(this.id);
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
		return "Dokumen={id="
				.concat(id)
				.concat(", nama=")
				.concat(nama)
				.concat("}");
	}	
	
}
