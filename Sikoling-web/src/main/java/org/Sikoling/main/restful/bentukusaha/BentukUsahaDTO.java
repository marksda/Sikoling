package org.Sikoling.main.restful.bentukusaha;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.BentukUsaha;

public class BentukUsahaDTO implements Serializable {

	private static final long serialVersionUID = 1259008356598578698L;
	private String id;
	private String idKelompokBentukUsaha;
	private  String nama;
	
	public BentukUsahaDTO() {

	}
	
	public BentukUsahaDTO(BentukUsaha bentukUsaha) {
		this.id = bentukUsaha.getId();
		this.idKelompokBentukUsaha = bentukUsaha.getIdKelompokBentukUsaha();
		this.nama = bentukUsaha.getNama();
	}
	
	public BentukUsahaDTO(String id, String idKelompokBentukUsaha, String nama) {
		super();
		this.id = id;
		this.idKelompokBentukUsaha = idKelompokBentukUsaha;
		this.nama = nama;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdKelompokBentukUsaha() {
		return idKelompokBentukUsaha;
	}

	public void setIdKelompokBentukUsaha(String idKelompokBentukUsaha) {
		this.idKelompokBentukUsaha = idKelompokBentukUsaha;
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
	
	public int hashCode() {
		int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.nama);
        hash = 23 * hash + Objects.hashCode(this.idKelompokBentukUsaha);
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
        
        if (this.idKelompokBentukUsaha != other.idKelompokBentukUsaha) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "BentukUsahaDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}

	public BentukUsaha toBentukUsaha() {
		return new BentukUsaha(id, nama, idKelompokBentukUsaha);
	}
}
