package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class JenisPelakuUsaha implements Serializable {

	private static final long serialVersionUID = -7606706066333265936L;
	private final String id;
	private final String nama;
	
	public JenisPelakuUsaha(String id, String nama) {
		super();
		this.id = id;
		this.nama = nama;
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
        
        final JenisPelakuUsaha other = (JenisPelakuUsaha) obj;
        
        if (!this.nama.equals(other.nama)) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "KelompokUsaha{" + "nama=" + nama + "}";
	}
	
}
