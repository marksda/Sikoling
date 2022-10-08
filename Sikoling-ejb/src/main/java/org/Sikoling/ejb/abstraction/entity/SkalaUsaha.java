package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class SkalaUsaha implements Serializable {

	private static final long serialVersionUID = -2069101397105967489L;
	private final String id;	
	private final String nama;
	private final String singkatan;
	
	public SkalaUsaha(String id, String nama, String singkatan) {
		super();
		this.id = id;
		this.nama = nama;
		this.singkatan = singkatan;
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

	public String getSingkatan() {
		return singkatan;
	}
	
	public int hashCode() {
		int hash = 7;
		hash = 91 * hash + Objects.hashCode(this.id);
		hash = 91 * hash + Objects.hashCode(this.nama);
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
        
        final SkalaUsaha other = (SkalaUsaha) obj;
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        if (!this.nama.equals(other.nama)) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "SkalaUsaha { id="
				.concat(this.id)
				.concat(", nama=")
				.concat(this.nama)
				.concat(", singkatan=")
				.concat(this.singkatan)
				.concat("}");
	}	
	

}
