package org.Sikoling.main.restful.kategoripenanggungjawab;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.KategoriPenanggungJawab;

public class KategoriPenanggungJawabDTO implements Serializable {

	private static final long serialVersionUID = -1281671920126702017L;	
	private String id;
	private String nama;
	
	public KategoriPenanggungJawabDTO() {
	}
	
	public KategoriPenanggungJawabDTO(KategoriPenanggungJawab kategoriPenanggungJawab) {
		super();
		this.id = kategoriPenanggungJawab.getId();
		this.nama = kategoriPenanggungJawab.getNama();
	}
	
	public KategoriPenanggungJawabDTO(String id, String nama) {
		super();
		this.id = id;
		this.nama = nama;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.nama);
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
        
        final KategoriPenanggungJawabDTO other = (KategoriPenanggungJawabDTO) obj;
        
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
		return "KategoriPenanggungJawabDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}

	public KategoriPenanggungJawab toKategoriPenanggungJawab() {
		return new KategoriPenanggungJawab(id, nama);
	}



}
