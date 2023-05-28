package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class KategoriPelakuUsaha implements Serializable {

	private static final long serialVersionUID = -7606706066333265936L;
	private final String id;
	private final String nama;
	private final SkalaUsaha skalaUsaha;
	
	public KategoriPelakuUsaha(String id, String nama, SkalaUsaha skalaUsaha) {
		this.id = id;
		this.nama = nama;
		this.skalaUsaha = skalaUsaha;
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
		int hash = 71;
		hash = 13 * hash + Objects.hashCode(this.id);
		hash = 13 * hash + Objects.hashCode(this.nama);
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
        
        final KategoriPelakuUsaha other = (KategoriPelakuUsaha) obj;
        
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
		return "JenisPelakuUsaha{"
				.concat("id=")
				.concat(this.id)
				.concat(", nama=")
				.concat(this.nama)
				.concat("}");
	}

	
	public SkalaUsaha getSkalaUsaha() {
		return skalaUsaha;
	}
	
}
