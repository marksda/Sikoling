package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_kecamatan database table.
 * 
 */
@Entity
@Table(name="tbl_kecamatan")
@NamedQuery(name="KecamatanData.findAll", query="SELECT k FROM KecamatanData k")
public class KecamatanData implements Serializable {
	private static final long serialVersionUID = 5701915325336067933L;

	@Id
	private String id;

	@Column(name="kabupaten")
	@JoinColumn(name = "kabupaten", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private KabupatenData kabupaten;

	@Column(name="nama")
	private String nama;

	public KecamatanData() {
	}

	public KecamatanData(String id, KabupatenData kabupaten, String nama) {
		super();
		this.id = id;
		this.kabupaten = kabupaten;
		this.nama = nama;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public KabupatenData getKabupaten() {
		return this.kabupaten;
	}

	public void setKabupaten(KabupatenData kabupaten) {
		this.kabupaten = kabupaten;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

}