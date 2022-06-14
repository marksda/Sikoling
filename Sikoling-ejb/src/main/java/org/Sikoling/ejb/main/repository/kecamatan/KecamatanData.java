package org.Sikoling.ejb.main.repository.kecamatan;

import java.io.Serializable;
import jakarta.persistence.*;


@Entity
@Table(name="master.tbl_kecamatan")
@NamedQueries({
@NamedQuery(name="KecamatanData.findAll", query="SELECT k FROM KecamatanData k"),
@NamedQuery(name="KecamatanData.findAllByQueryNama", query="SELECT k FROM KecamatanData k WHERE k.nama ILIKE :nama"),
@NamedQuery(name="KecamatanData.findAllByIdKabupaten", query="SELECT k FROM KecamatanData k WHERE k.idKabupaten = :idKabupaten"),
@NamedQuery(name="KecamatanData.findAllByIdKabupatenAndQueryNama", query="SELECT k FROM KecamatanData k WHERE k.nama ILIKE :nama AND k.idKabupaten = :idKabupaten")})
public class KecamatanData implements Serializable {
	private static final long serialVersionUID = 5701915325336067933L;

	@Id
	private String id;

	@Column(name="kabupaten")
	private String idKabupaten;

	@Column(name="nama")
	private String nama;

	public KecamatanData() {
		
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdKabupaten() {
		return this.idKabupaten;
	}

	public void setIdKabupaten(String idKabupaten) {
		this.idKabupaten = idKabupaten;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

}