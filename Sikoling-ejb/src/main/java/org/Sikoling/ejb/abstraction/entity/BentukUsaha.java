package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class BentukUsaha implements Serializable{

	private static final long serialVersionUID = -5160872827538954162L;
	private final String id;
	private final String idKelompokBentukUsaha;
	private final String nama;
	
	
	public BentukUsaha(String id) {
		super();
		this.id = id;
		this.idKelompokBentukUsaha = null;
		this.nama = "";
	}
	
	public BentukUsaha(String id, String nama) {
		super();
		this.id = id;
		this.idKelompokBentukUsaha = null;
		this.nama = nama;
	}
	
	public BentukUsaha(String id, String nama, String idKelompokBentukUsaha) {
		super();
		this.id = id;
		this.idKelompokBentukUsaha = idKelompokBentukUsaha;
		this.nama = nama;
	}

	public String getId() {
		return id;
	}

	public String getIdKelompokBentukUsaha() {
		return idKelompokBentukUsaha;
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
		hash = 13 * hash + Objects.hashCode(this.idKelompokBentukUsaha);
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
        
        final BentukUsaha other = (BentukUsaha) obj;
        
        if (!this.nama.equals(other.nama)) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "BentukUsaha{" + "id=" + id + ", nama=" + nama + "}";
	}
	
	
}
