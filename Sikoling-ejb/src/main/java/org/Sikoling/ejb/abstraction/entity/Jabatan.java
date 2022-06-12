package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Jabatan implements Serializable {

	private static final long serialVersionUID = 702489998264182010L;
	private final String id;
	private final String nama;
	
	public Jabatan(String id, String nama) {
		super();
		this.id = id;
		this.nama = nama;
	}
	
	public Jabatan(String id) {
		super();
		this.id = id;
		this.nama = "";
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
        
        final Jabatan other = (Jabatan) obj;
        
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
		return "Jabatan{" + "id=" + id + "nama=" + nama + "}";
	}

}
