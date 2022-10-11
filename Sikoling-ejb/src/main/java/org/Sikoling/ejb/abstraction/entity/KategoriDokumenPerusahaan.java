package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class KategoriDokumenPerusahaan implements Serializable {

	private static final long serialVersionUID = -5095938556108761375L;
	private final String id;
	private final String nama;
	private final String idParent;
	
	public KategoriDokumenPerusahaan(String id, String nama, String idParent) {
		super();
		this.id = id;
		this.nama = nama;
		this.idParent = idParent;
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
	
	public String getIdParent() {
		return idParent;
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
        
        final KategoriDokumenPerusahaan other = (KategoriDokumenPerusahaan) obj;
        
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
		return "KategoriDokumenPerusahaan{"
				.concat("id=")
				.concat(this.id)
				.concat(", ")
				.concat("nama=")
				.concat(this.nama)
				.concat("}");
	}	

}
