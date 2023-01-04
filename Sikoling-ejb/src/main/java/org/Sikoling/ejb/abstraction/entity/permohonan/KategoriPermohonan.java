package org.Sikoling.ejb.abstraction.entity.permohonan;

import java.io.Serializable;
import java.util.Objects;

public class KategoriPermohonan implements Serializable {

	private static final long serialVersionUID = -7079680267959922337L;	
	private final String id;
	private final String nama;
	
	public KategoriPermohonan(String id, String nama) {
		this.id = id;
		this.nama = nama;
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
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 137 * hash + Objects.hashCode(this.id);
		hash = 137 * hash + Objects.hashCode(this.nama);
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
        
        final KategoriPermohonan other = (KategoriPermohonan) obj;
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        if (!this.nama.equals(other.getNama())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "KategoriPermohonan{" + "id=" + id + "nama=" + nama + "}";
	}


}
