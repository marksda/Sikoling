package org.Sikoling.ejb.main.repository.desa;

import java.io.Serializable;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import jakarta.persistence.*;


@Entity
@Table(name="master.tbl_desa")
@NamedQueries({
@NamedQuery(name="DesaData.findAll", query="SELECT d FROM DesaData d"),
@NamedQuery(name="DesaData.findByNama", query="SELECT d FROM DesaData d WHERE d.nama LIKE :nama"),
@NamedQuery(name="DesaData.findByKecamatan", query="SELECT d FROM DesaData d WHERE d.kecamatan.id = :idKecamatan"),
@NamedQuery(name="DesaData.findByKecamatanAndNama", query="SELECT d FROM DesaData d WHERE d.nama LIKE :nama AND d.kecamatan.id = :idKecamatan")})
public class DesaData implements Serializable {
	private static final long serialVersionUID = -5126550971303462658L;

	@Id
	private String id;
	
	private String nama;

	@JoinColumn(name = "kecamatan", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private KecamatanData kecamatan;

	public DesaData() {
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

	public KecamatanData getKecamatan() {
		return this.kecamatan;
	}

	public void setKecamatan(KecamatanData kecamatanData) {
		this.kecamatan = kecamatanData;
	}
	
}