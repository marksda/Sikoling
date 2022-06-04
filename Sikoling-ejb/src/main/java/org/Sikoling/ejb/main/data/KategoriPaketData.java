package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_kategori_paket database table.
 * 
 */
@Entity
@Table(name="tbl_kategori_paket")
@NamedQuery(name="KategoriPaketData.findAll", query="SELECT k FROM KategoriPaketData k")
public class KategoriPaketData implements Serializable {
	private static final long serialVersionUID = -8348837675976894415L;

	@Id
	private String id;

	private String nama;

	public KategoriPaketData() {
	}

	public KategoriPaketData(String id, String nama) {
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