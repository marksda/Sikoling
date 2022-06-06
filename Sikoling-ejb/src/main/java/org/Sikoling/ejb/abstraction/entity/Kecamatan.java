package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Kecamatan implements Serializable {

	private static final long serialVersionUID = 4983912261984260673L;
	private final String id;
	private final String nama;
	private final Kabupaten kabupaten;
	
	public Kecamatan(String id, String nama, Kabupaten kabupaten) {
		super();
		this.id = id;
		this.nama = nama;
		this.kabupaten = kabupaten;
	}

	public String getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}

	public Kabupaten getKabupaten() {
		return kabupaten;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + Objects.hashCode(this.id);
		hash = 13 * hash + Objects.hashCode(this.nama);
		hash = 13 * hash + Objects.hashCode(this.kabupaten.getNama());
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
        
        final Kecamatan other = (Kecamatan) obj;
        
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
		return "Kecamatan{" + "id=" + id + "nama=" + nama + "}";
	}
	
	

}
