package org.Sikoling.ejb.main.repository.kategoriproduk;

import java.io.Serializable;
import jakarta.persistence.*;


@Entity
@Table(name="master.tbl_kategori_produk_dlhk")
public class KategoriProdukData implements Serializable {
	private static final long serialVersionUID = -1689057030659783556L;

	@Id
	private String id;

	private String nama;

	public KategoriProdukData() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

}