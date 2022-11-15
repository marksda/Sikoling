package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class KategoriDokumen implements Serializable {

	private static final long serialVersionUID = -5095938556108761375L;
	private final String id;
	private final String nama;
	private final String parent;
	
	public KategoriDokumen(String id, String nama, String parent) {
		this.id = id;
		this.nama = nama;
		this.parent = parent;
	}
		
	public String getParent() {
		return parent;
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
        
        final KategoriDokumen other = (KategoriDokumen) obj;
        
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
