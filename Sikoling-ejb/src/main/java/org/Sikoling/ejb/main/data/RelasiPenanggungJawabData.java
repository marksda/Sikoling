package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;

import org.Sikoling.ejb.main.repository.penanggungjawab.PenanggungJawabData;


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
	@JoinColumn(name = "kategori_penanggung_jawab", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private KategoriPenanggungJawabData kategoriPenanggungJawab;

	@Column(name="penanggung_jawab")
	@JoinColumn(name = "penanggung_jawab", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private PenanggungJawabData penanggungJawab;

	public RelasiPenanggungJawabData() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public KategoriPenanggungJawabData getKategoriPenanggungJawab() {
		return this.kategoriPenanggungJawab;
	}

	public void setKategoriPenanggungJawab(KategoriPenanggungJawabData kategoriPenanggungJawab) {
		this.kategoriPenanggungJawab = kategoriPenanggungJawab;
	}

	public PenanggungJawabData getPenanggungJawab() {
		return this.penanggungJawab;
	}

	public void setPenanggungJawab(PenanggungJawabData penanggungJawab) {
		this.penanggungJawab = penanggungJawab;
	}

}