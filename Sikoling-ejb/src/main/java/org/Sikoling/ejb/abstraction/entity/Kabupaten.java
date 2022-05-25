package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Kabupaten implements Serializable {

	private static final long serialVersionUID = -1725097099726145918L;
	private String id;
	private String nama;
	private Propinsi propinsi;
	
	public Kabupaten(String id, String nama, Propinsi propinsi) {
		super();
		this.id = id;
		this.nama = nama;
		this.propinsi = propinsi;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Propinsi getPropinsi() {
		return propinsi;
	}

	public void setPropinsi(Propinsi propinsi) {
		this.propinsi = propinsi;
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
