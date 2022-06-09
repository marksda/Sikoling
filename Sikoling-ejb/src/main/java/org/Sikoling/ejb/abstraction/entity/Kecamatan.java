package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Kecamatan implements Serializable {

	private static final long serialVersionUID = 4983912261984260673L;
	private final String id;
	private final String nama;
	private final String idKabupaten;
	
	public Kecamatan() {
		super();
		this.id = null;
		this.nama = null;
		this.idKabupaten = null;
	}
	
	public Kecamatan(String id) {
		super();
		this.id = id;
		this.nama = null;
		this.idKabupaten = null;
	}
	
	public Kecamatan(String id, String nama, String idKabupaten) {
		super();
		this.id = id;
		this.nama = nama;
		this.idKabupaten = idKabupaten;
	}

	public String getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}

	public String getIdKabupaten() {
		return idKabupaten;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + Objects.hashCode(this.id);
		hash = 13 * hash + Objects.hashCode(this.nama);
		hash = 13 * hash + Objects.hashCode(this.idKabupaten);
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
