package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Desa implements Serializable {

	private static final long serialVersionUID = -7665467179125915846L;
	private final String id;
	private final String nama;
	private final Kecamatan kecamatan;
	
	public Desa(String id, String nama, Kecamatan kecamatan) {
		super();
		this.id = id;
		this.nama = nama;
		this.kecamatan = kecamatan;
	}
	
	public Desa(String id) {
		super();
		this.id = id;
		this.nama = "";
		this.kecamatan = null;
	}

	public String getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}

	public Kecamatan getKecamatan() {
		return kecamatan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 7;
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
        
        final Desa other = (Desa) obj;
        
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
		return "Desa{" + "id=" + id + "nama=" + nama + "}";
	}

}
