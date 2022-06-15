package org.Sikoling.main.restful.bentukusaha;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.BentukUsaha;

public class BentukUsahaDTO implements Serializable {

	private static final long serialVersionUID = 1259008356598578698L;
	private String id;
	private String nama;
	private String singkatan;
	private String idKelompok;
	
	public BentukUsahaDTO() {

	}
	
	public BentukUsahaDTO(BentukUsaha bentukUsaha) {
		this.id = bentukUsaha.getId();
		this.nama = bentukUsaha.getNama();
		this.singkatan = bentukUsaha.getSingkatan();
		this.idKelompok = bentukUsaha.getIdJenisPelakuUsaha();
	}	

	public BentukUsahaDTO(String id, String nama, String singkatan, String idKelompok) {
		this.id = id;
		this.nama = nama;
		this.singkatan = singkatan;
		this.idKelompok = idKelompok;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
	
	public String getSingkatan() {
		return singkatan;
	}

	public void setSingkatan(String singkatan) {
		this.singkatan = singkatan;
	}

	public String getIdKelompok() {
		return idKelompok;
	}

	public void setIdKelompok(String idKelompok) {
		this.idKelompok = idKelompok;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 13;
        hash = 27 * hash + Objects.hashCode(this.id);
        hash = 27 * hash + Objects.hashCode(this.nama);
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
        
        final BentukUsahaDTO other = (BentukUsahaDTO) obj;
        
        if (this.id != other.id) {
            return false;
        }
        
        if (this.nama != other.nama) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "BentukUsahaDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}

	public BentukUsaha toBentukUsaha() {
		return new BentukUsaha(id, nama, singkatan, idKelompok);
	}
}
