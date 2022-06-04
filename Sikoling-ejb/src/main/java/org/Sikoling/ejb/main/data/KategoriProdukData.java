package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_kategori_produk database table.
 * 
 */
@Entity
@Table(name="tbl_kategori_produk")
@NamedQuery(name="KategoriProdukData.findAll", query="SELECT k FROM KategoriProdukData k")
public class KategoriProdukData implements Serializable {
	private static final long serialVersionUID = -1689057030659783556L;

	@Id
	private String id;

	private String nama;

	public KategoriProdukData() {
	}

	public KategoriProdukData(String id, String nama) {
		super();
		this.id = id;
		this.nama = nama;
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