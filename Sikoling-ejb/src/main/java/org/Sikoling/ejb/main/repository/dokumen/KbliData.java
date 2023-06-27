package org.Sikoling.ejb.main.repository.dokumen;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_kbli_2020")
@NamedQueries({
	@NamedQuery(name="KbliData.findAll", query = "SELECT d FROM KbliData d"),
	@NamedQuery(name="KbliData.findByNama", query = "SELECT d FROM KbliData d WHERE d.nama LIKE :nama"),
	@NamedQuery(name="KbliData.findByKode", query = "SELECT d FROM KbliData d WHERE d.id LIKE :kode"),
	@NamedQuery(name="KbliData.findByKategori", query = "SELECT d FROM KbliData d WHERE d.kategori = :kategori")
})
public class KbliData {
	
	@Id
	private String id;
	
	private String nama;
	
	private String kategori;
	
	public KbliData() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}	
	
}
