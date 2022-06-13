package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Kabupaten implements Serializable {

	private static final long serialVersionUID = -1725097099726145918L;
	private final String id;
	private final String nama;
	private final String idPropinsi;
	
	public Kabupaten(String id) {
		this.id = id;
		this.nama = null;
		this.idPropinsi = null;
	}
	
	public Kabupaten(String id, String nama) {
		this.id = id;
		this.nama = nama;
		this.idPropinsi = "";
	}
	
	public Kabupaten(String id, String nama, String idPropinsi) {
		this.id = id;
		this.nama = nama;
		this.idPropinsi = idPropinsi;
	}

	public String getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}

	public String getIdPropinsi() {
		return idPropinsi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + Objects.hashCode(this.id);
		hash = 13 * hash + Objects.hashCode(this.nama);
		hash = 13 * hash + Objects.hashCode(this.idPropinsi);
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
        
        final Kabupaten other = (Kabupaten) obj;
        
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
		return "Kabupaten{" + "id=" + id + "nama=" + nama + "}";
	}
	
}
