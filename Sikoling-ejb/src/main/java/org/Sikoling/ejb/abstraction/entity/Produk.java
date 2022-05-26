package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Produk implements Serializable {

	private static final long serialVersionUID = 1719488297810116974L;
	private String id;
	private String nama;
	private KategoriPaket kategoriPaket;
	private KategoriProduk kategoriProduk;
	
	public Produk(String id, String nama, KategoriPaket kategoriPaket, KategoriProduk kategoriProduk) {
		super();
		this.id = id;
		this.nama = nama;
		this.kategoriPaket = kategoriPaket;
		this.kategoriProduk = kategoriProduk;
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

	public KategoriPaket getKategoriPaket() {
		return kategoriPaket;
	}

	public void setKategoriPaket(KategoriPaket kategoriPaket) {
		this.kategoriPaket = kategoriPaket;
	}

	public KategoriProduk getKategoriProduk() {
		return kategoriProduk;
	}

	public void setKategoriProduk(KategoriProduk kategoriProduk) {
		this.kategoriProduk = kategoriProduk;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + Objects.hashCode(this.id);
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
        
        final Produk other = (Produk) obj;
        
        if (!this.id.equals(other.id)) {
            return false;
        }
        
        if (!this.nama.equals(other.nama)) {
            return false;
        }
        
        return true;
	}

	
	@Override
	public String toString() {
		return "Produk{" + "id=" + id + "nama=" + nama + "}";
	}

}
