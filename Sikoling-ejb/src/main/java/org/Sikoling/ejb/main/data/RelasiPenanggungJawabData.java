package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_relasi_penanggung_jawab database table.
 * 
 */
@Entity
@Table(name="tbl_relasi_penanggung_jawab")
@NamedQuery(name="RelasiPenanggungJawabData.findAll", query="SELECT r FROM RelasiPenanggungJawabData r")
public class RelasiPenanggungJawabData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="kategori_penanggung_jawab")
	private String kategoriPenanggungJawab;

	@Column(name="penanggung_jawab")
	private String penanggungJawab;

	public RelasiPenanggungJawabData() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKategoriPenanggungJawab() {
		return this.kategoriPenanggungJawab;
	}

	public void setKategoriPenanggungJawab(String kategoriPenanggungJawab) {
		this.kategoriPenanggungJawab = kategoriPenanggungJawab;
	}

	public String getPenanggungJawab() {
		return this.penanggungJawab;
	}

	public void setPenanggungJawab(String penanggungJawab) {
		this.penanggungJawab = penanggungJawab;
	}

}