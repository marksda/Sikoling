package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class StatusTahapPemberkasan implements Serializable {

	private static final long serialVersionUID = 3807325442231744559L;
	private final String id;
	private final String nama;
	private final String keterangan;
	
	public StatusTahapPemberkasan(String id, String nama, String keterangan) {
		this.id = id;
		this.nama = nama;
		this.keterangan = keterangan;
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

	public String getKeterangan() {
		return keterangan;
	}
	
	@Override
	public int hashCode() {
		int hash = 71;
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
        
        final StatusTahapPemberkasan other = (StatusTahapPemberkasan) obj;
        
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
