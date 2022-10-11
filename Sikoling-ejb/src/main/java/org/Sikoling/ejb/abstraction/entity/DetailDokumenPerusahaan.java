package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class DetailDokumenPerusahaan implements Serializable {

	private static final long serialVersionUID = -3166732876247970646L;
	private final String id;
	private final String nama;
	private final KategoriDokumenPerusahaan kategoriDokumenPerusahaan;
	
	public DetailDokumenPerusahaan(String id, String nama, KategoriDokumenPerusahaan kategoriDokumenPerusahaan) {
		super();
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
		int hash = 13;
		hash = 71 * hash + Objects.hashCode(this.id);
		hash = 71 * hash + Objects.hashCode(this.nama);
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
        
        final DetailDokumenPerusahaan other = (DetailDokumenPerusahaan) obj;
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        if (!this.nama.equals(other.getNama())) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "DetailDokumenPerusahaan{"
				.concat("id=")
				.concat(this.id)
				.concat(", ")
				.concat("nama=")
				.concat(this.nama)
				.concat("}");
	}

}
