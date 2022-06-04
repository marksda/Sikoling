package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_produk database table.
 * 
 */
@Entity
@Table(name="tbl_produk")
@NamedQuery(name="ProdukData.findAll", query="SELECT p FROM ProdukData p")
public class ProdukData implements Serializable {
	private static final long serialVersionUID = 8534122990459968013L;

	@Id
	private String id;

	@Column(name="kategori_paket")
	@JoinColumn(name = "kategori_paket", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private KategoriPaketData kategoriPaket;

	@Column(name="kategori_produk")
	@JoinColumn(name = "kategori_produk", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private KategoriProdukData kategoriProduk;

	private String nama;

	public ProdukData() {
	}

	public ProdukData(String id, KategoriPaketData kategoriPaket, KategoriProdukData kategoriProduk, String nama) {
		super();
		this.id = id;
		this.kategoriPaket = kategoriPaket;
		this.kategoriProduk = kategoriProduk;
		this.nama = nama;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public KategoriPaketData getKategoriPaket() {
		return this.kategoriPaket;
	}

	public void setKategoriPaket(KategoriPaketData kategoriPaket) {
		this.kategoriPaket = kategoriPaket;
	}

	public KategoriProdukData getKategoriProduk() {
		return this.kategoriProduk;
	}

	public void setKategoriProduk(KategoriProdukData kategoriProduk) {
		this.kategoriProduk = kategoriProduk;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

}