package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class BentukUsaha implements Serializable{

	private static final long serialVersionUID = -5160872827538954162L;
	private final String id;
	private final String nama;
	private final String singkatan;
	private final String idJenisPelakuUsaha;
	
	
	public BentukUsaha(String id) {
		super();
		this.id = id;
		this.nama = null;
		this.singkatan = null;
		this.idJenisPelakuUsaha = null;
	}
	
	public BentukUsaha(String id, String nama) {
		super();
		this.id = id;
		this.nama = nama;
		this.singkatan = null;
		this.idJenisPelakuUsaha = null;
	}
	
	public BentukUsaha(String id, String nama, String singkatan, String idJenisPelakuUsaha) {
		super();
		this.id = id;
		this.nama = nama;
		this.singkatan = singkatan;
		this.idJenisPelakuUsaha = idJenisPelakuUsaha;
	}
	
	public String getIdJenisPelakuUsaha() {
		return idJenisPelakuUsaha;
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
		int hash = 31;
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

	public String getSingkatan() {
		return singkatan;
	}
		
}
