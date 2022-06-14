package org.Sikoling.main.restful.kategoriproduk;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.KategoriProduk;

public class KategoriProdukDTO implements Serializable {

	private static final long serialVersionUID = 3673766206316908974L;
	private String id;
	private String nama;
	
	public KategoriProdukDTO() {
		
	}
	
	public KategoriProdukDTO(KategoriProduk t) {
		this.id = t.getId();
		this.nama = t.getNama();
	}	
	
	public KategoriProdukDTO(String id, String nama) {
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
	
	public int hashCode() {
		int hash = 41;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.nama);
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
        
        final KategoriProdukDTO other = (KategoriProdukDTO) obj;
        
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
		return "KategoriProdukDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}	

	public KategoriProduk toKategoriProduk( ) {
		return new KategoriProduk(id, nama);
	}
}
