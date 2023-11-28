package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class ModelPerizinan implements Serializable {

	private static final long serialVersionUID = -6301532922420586440L;
	private final String id;
	private final String nama;
	private final String singkatan;
	
	public ModelPerizinan(String id, String nama, String singkatan) {
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
		int hash = 11;
		hash = 17 * hash + Objects.hashCode(this.id);
		hash = 17 * hash + Objects.hashCode(this.nama);
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
        
        final ModelPerizinan other = (ModelPerizinan) obj;
        
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
		return "ModelPerizinan{" + "id=" + id + "nama=" + nama + "}";
	}


}
