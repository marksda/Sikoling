package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class DetailPelakuUsaha implements Serializable {

	private static final long serialVersionUID = 1121508636394824313L;
	private final String id;
	private final String nama;
	private final String singkatan;
	
	public DetailPelakuUsaha(String id, String nama, String singkatan) {
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
		int hash = 91;
		hash = 11 * hash + Objects.hashCode(this.id);
		hash = 11 * hash + Objects.hashCode(this.nama);
		hash = 11 * hash + Objects.hashCode(this.singkatan);
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
        
        final DetailPelakuUsaha other = (DetailPelakuUsaha) obj;
        
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
		return "DetailPelakuUsaha{id="
				.concat(this.id)
				.concat(", nama=")
				.concat(this.nama)
				.concat(", singkatan=")
				.concat(this.singkatan)
				.concat("}");
	}	
	

}
