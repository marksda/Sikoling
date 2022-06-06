package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_kategori_penanggung_jawab database table.
 * 
 */
@Entity
@Table(name="tbl_kategori_penanggung_jawab")
@NamedQuery(name="KategoriPenanggungJawabData.findAll", query="SELECT k FROM KategoriPenanggungJawabData k")
public class KategoriPenanggungJawabData implements Serializable {
	private static final long serialVersionUID = -8178957926412495736L;

	@Id
	private String id;

	private String nama;

	public KategoriPenanggungJawabData() {
	}

	public KategoriPenanggungJawabData(String id, String nama) {
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