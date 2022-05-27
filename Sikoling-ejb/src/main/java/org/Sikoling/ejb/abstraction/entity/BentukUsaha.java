package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class BentukUsaha implements Serializable{

	private static final long serialVersionUID = -5160872827538954162L;
	private String id;
	private KelompokBentukUsaha kelompokUsaha;
	private String nama;
	
	public BentukUsaha(String id, KelompokBentukUsaha kelompokUsaha, String nama) {
		super();
		this.id = id;
		this.kelompokUsaha = kelompokUsaha;
		this.nama = nama;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public KelompokBentukUsaha getKelompokUsaha() {
		return kelompokUsaha;
	}

	public void setKelompokUsaha(KelompokBentukUsaha kelompokUsaha) {
		this.kelompokUsaha = kelompokUsaha;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + Objects.hashCode(this.nama);
		hash = 13 * hash + Objects.hashCode(this.kelompokUsaha.getNama());
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
		return "BentukUsaha{" + "nama=" + nama + ", KelompokUsaha=" + this.kelompokUsaha.getNama() + "}";
	}
	
	
}
