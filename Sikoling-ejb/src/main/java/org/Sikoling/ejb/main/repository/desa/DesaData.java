package org.Sikoling.ejb.main.repository.desa;

import java.io.Serializable;
import javax.persistence.*;

import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;


@Entity
@Table(name="master.tbl_desa")
@NamedQueries({
@NamedQuery(name="DesaData.findAll", query="SELECT d FROM DesaData d"),
@NamedQuery(name="DesaData.findAllByQueryNama", query="SELECT d FROM DesaData d WHERE d.nama ILIKE :nama"),
@NamedQuery(name="DesaData.findAllByIdKecamatan", query="SELECT d FROM DesaData d WHERE d.kecamatan.id = :idKecamatan"),
@NamedQuery(name="DesaData.findAllByIdKecamatanAndQueryNama", query="SELECT d FROM DesaData d WHERE d.nama ILIKE :nama AND d.kecamatan.id = :idKecamatan")})
public class DesaData implements Serializable {
	private static final long serialVersionUID = -5126550971303462658L;

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

	public DesaData(String id, KecamatanData kecamatan, String nama) {
		super();
		this.id = id;
		this.kecamatan = kecamatan;
		this.nama = nama;
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