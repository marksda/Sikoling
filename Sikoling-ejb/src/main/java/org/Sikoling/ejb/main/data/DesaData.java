package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_desa database table.
 * 
 */
@Entity
@Table(name="tbl_desa")
@NamedQuery(name="DesaData.findAll", query="SELECT d FROM DesaData d")
public class DesaData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="kecamatan")
	@JoinColumn(name = "kecamatan", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private KecamatanData kecamatan;

	@Column(name="nama")
	private String nama;

	public DesaData() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public KecamatanData getKecamatan() {
		return this.kecamatan;
	}

	public void setKecamatan(KecamatanData kecamatan) {
		this.kecamatan = kecamatan;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

}