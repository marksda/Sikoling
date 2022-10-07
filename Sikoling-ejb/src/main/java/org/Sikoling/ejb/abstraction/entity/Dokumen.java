package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Dokumen implements Serializable {

	private static final long serialVersionUID = -1869365745189974891L;
	private final String id;
	private final String jenis;
	private final String nama;
	
	public Dokumen(String id, String jenis, String nama) {
		super();
		this.id = id;
		this.jenis = jenis;
		this.nama = nama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public String getJenis() {
		return jenis;
	}

	public String getNama() {
		return nama;
	}
	
	public int hashCode() {
		int hash = 19;
		hash = 41 * hash + Objects.hashCode(this.id);
		hash = 41 * hash + Objects.hashCode(this.jenis);
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
        
        final Dokumen other = (Dokumen) obj;
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        if (!this.jenis.equals(other.getJenis())) {
            return false;
        }
        
        if (!this.nama.equals(other.getNama())) {
            return false;
        }
        
        return true;
	}

	
	@Override
	public String toString() {
		return "Dokumen {"
				.concat("id=")
				.concat(this.id)
				.concat(", ")
				.concat("jenis=")
				.concat(this.jenis)
				.concat(", ")
				.concat("nama=")
				.concat(this.nama)
				.concat("}");
	}	
}
