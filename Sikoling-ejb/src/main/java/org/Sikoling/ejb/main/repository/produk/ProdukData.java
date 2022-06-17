package org.Sikoling.ejb.main.repository.produk;

import jakarta.persistence.*;
import java.io.Serializable;
import org.Sikoling.ejb.main.repository.kategoriproduk.KategoriProdukData;


@Entity
@Table(name="master.tbl_produk")
@NamedQueries({
@NamedQuery(name="ProdukData.findAll", query="SELECT p FROM ProdukData p"),
@NamedQuery(name="ProdukData.findByNama", query="SELECT p FROM ProdukData p WHERE p.nama ILIKE :nama")
})
public class ProdukData implements Serializable {
	private static final long serialVersionUID = 8534122990459968013L;

	@Id
	private String id;

	@JoinColumn(name = "kategori_produk", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
	private KategoriProdukData kategoriProduk;

	private String nama;

	public ProdukData() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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