package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class KelompokBentukUsaha implements Serializable {

	private static final long serialVersionUID = -7606706066333265936L;
	private final String id;
	private final String nama;
	private final List<BentukUsaha> daftarBentukUsaha;
	
	public KelompokBentukUsaha(String id, String nama, List<BentukUsaha> daftarBentukUsaha) {
		super();
		this.id = id;
		this.nama = nama;
		this.daftarBentukUsaha = daftarBentukUsaha;
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
        
        final KelompokBentukUsaha other = (KelompokBentukUsaha) obj;
        
        if (!this.nama.equals(other.nama)) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "KelompokUsaha{" + "nama=" + nama + "}";
	}

	public List<BentukUsaha> getDaftarBentukUsaha() {
		return daftarBentukUsaha;
	}
	
}
