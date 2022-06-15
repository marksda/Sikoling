package org.Sikoling.ejb.main.repository.desa;

import java.io.Serializable;
import jakarta.persistence.*;


@Entity
@Table(name="master.tbl_desa")
@NamedQueries({
@NamedQuery(name="DesaData.findAll", query="SELECT d FROM DesaData d"),
@NamedQuery(name="DesaData.findByNama", query="SELECT d FROM DesaData d WHERE d.nama ILIKE :nama"),
@NamedQuery(name="DesaData.findByKecamatan", query="SELECT d FROM DesaData d WHERE d.idKecamatan = :idKecamatan"),
@NamedQuery(name="DesaData.findByKecamatanAndNama", query="SELECT d FROM DesaData d WHERE d.nama ILIKE :nama AND d.idKecamatan = :idKecamatan")})
public class DesaData implements Serializable {
	private static final long serialVersionUID = -5126550971303462658L;

	@Id
	private String id;

	@Column(name="kecamatan")
	private String idKecamatan;

	private String nama;

	public DesaData() {
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