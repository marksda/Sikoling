package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class KategoriPenanggungJawab implements Serializable {
	
	private static final long serialVersionUID = -6070575858915847937L;
	private final String id;
	private final String nama;
	
	public KategoriPenanggungJawab(String id, String nama) {
		super();
		this.id = id;
		this.nama = nama;
	}

	public String getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 41 * hash + Objects.hashCode(this.id);
		hash = 41 * hash + Objects.hashCode(this.nama);
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
        
        final KategoriPenanggungJawab other = (KategoriPenanggungJawab) obj;
        
        if (!this.id.equals(other.id)) {
            return false;
        }
        
        if (!this.nama.equals(other.nama)) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "KategoriPenanggungJawab{" + "id=" + id + "nama=" + nama + "}";
	}

}
