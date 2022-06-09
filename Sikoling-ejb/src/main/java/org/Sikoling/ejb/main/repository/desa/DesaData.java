package org.Sikoling.ejb.main.repository.desa;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="master.tbl_desa")
@NamedQueries({
@NamedQuery(name="DesaData.findAll", query="SELECT d FROM DesaData d"),
@NamedQuery(name="DesaData.findAllByQueryNama", query="SELECT d FROM DesaData d WHERE d.nama ILIKE :nama"),
@NamedQuery(name="DesaData.findAllByIdKecamatan", query="SELECT d FROM DesaData d WHERE d.kecamatan = :idKecamatan"),
@NamedQuery(name="DesaData.findAllByIdKecamatanAndQueryNama", query="SELECT d FROM DesaData d WHERE d.nama ILIKE :nama AND d.kecamatan = :idKecamatan")})
public class DesaData implements Serializable {
	private static final long serialVersionUID = -5126550971303462658L;

	@Id
	private String id;

	@Column(name="kecamatan")
	private String idKecamatan;

	private String nama;

	public DesaData() {
	}

	public DesaData(String id, String nama, String idKecamatan) {
		this.id = id;
		this.idKecamatan = idKecamatan;
		this.nama = nama;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdKecamatan() {
		return this.idKecamatan;
	}

	public void setIdKecamatan(String idKecamatan) {
		this.idKecamatan = idKecamatan;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

}