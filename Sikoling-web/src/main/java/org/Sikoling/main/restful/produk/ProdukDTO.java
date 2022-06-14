package org.Sikoling.main.restful.produk;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Produk;

public class ProdukDTO implements Serializable {

	private static final long serialVersionUID = -8270544525133323068L;
	private String id;
	private String nama;
	private String idKategoriProduk;
	
	public ProdukDTO() {
		
	}
	
	public ProdukDTO(Produk t) {
		this.id = t.getId();
		this.nama = t.getNama();
		this.idKategoriProduk = t.getIdKategoriProduk();
	}
	
	public ProdukDTO(String id, String nama, String idKategoriProduk) {
		super();
		this.id = id;
		this.nama = nama;
		this.idKategoriProduk = idKategoriProduk;
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

	public String getIdKategoriProduk() {
		return idKategoriProduk;
	}

	public void setIdKategoriProduk(String idKategoriProduk) {
		this.idKategoriProduk = idKategoriProduk;
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
		return new Produk(id, nama, idKategoriProduk);
	}
}
