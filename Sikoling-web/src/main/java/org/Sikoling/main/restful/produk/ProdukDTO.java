package org.Sikoling.main.restful.produk;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Produk;
import org.Sikoling.main.restful.kategoriproduk.KategoriProdukDTO;

public class ProdukDTO implements Serializable {

	private static final long serialVersionUID = -8270544525133323068L;
	private String id;
	private String nama;
	private KategoriProdukDTO kategoriProduk;
	
	public ProdukDTO() {
		
	}
	
	public ProdukDTO(Produk t) {
		if(t != null) {
			this.id = t.getId();
			this.nama = t.getNama();
			this.kategoriProduk = t.getKategoriProduk() != null ? new KategoriProdukDTO(t.getKategoriProduk()):null;
		}		
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

	public KategoriProdukDTO getKategoriProduk() {
		return kategoriProduk;
	}

	public void setKategoriProduk(KategoriProdukDTO kategoriProduk) {
		this.kategoriProduk = kategoriProduk;
	}
	
	public int hashCode() {
		int hash = 31;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.nama);
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
        
        final ProdukDTO other = (ProdukDTO) obj;
        
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
		return "ProdukDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}	

	public Produk toProduk() {
		return new Produk(id, nama, kategoriProduk != null ? kategoriProduk.toKategoriProduk():null);
	}
}
