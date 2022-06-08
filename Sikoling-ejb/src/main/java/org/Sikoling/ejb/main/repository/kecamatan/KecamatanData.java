package org.Sikoling.ejb.main.repository.kecamatan;

import java.io.Serializable;
import javax.persistence.*;

import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;


/**
 * The persistent class for the tbl_kecamatan database table.
 * 
 */
@Entity
@Table(name="master.tbl_kecamatan")
@NamedQueries({
@NamedQuery(name="KecamatanData.findAll", query="SELECT k FROM KecamatanData k"),
@NamedQuery(name="KecamatanData.findAllByQueryNama", query="SELECT k FROM KecamatanData k WHERE k.nama ILIKE :nama"),
@NamedQuery(name="KecamatanData.findAllByIdKabupaten", query="SELECT k FROM KecamatanData k WHERE k.kabupaten.id = :idKabupaten"),
@NamedQuery(name="KecamatanData.findAllByIdKabupatenAndQueryNama", query="SELECT k FROM KecamatanData k WHERE k.nama ILIKE :nama AND k.kabupaten.id = :idKabupaten")})
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