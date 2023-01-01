package org.Sikoling.ejb.main.repository.dokumen;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_kbli_2020")
@NamedQueries({
	@NamedQuery(name="Kbli2020Data.findAll", query = "SELECT d FROM Kbli2020Data d"),
	@NamedQuery(name="Kbli2020Data.findByNama", query = "SELECT d FROM Kbli2020Data d WHERE d.nama LIKE :nama"),
	@NamedQuery(name="Kbli2020Data.findByKode", query = "SELECT d FROM Kbli2020Data d WHERE d.id LIKE :kode"),
	@NamedQuery(name="Kbli2020Data.findByKategori", query = "SELECT d FROM Kbli2020Data d WHERE d.kategori = :kategori")
})
public class Kbli2020Data {
	
	@Id
	private String id;
	
	private String nama;
	
	private String kategori;
	
	public Kbli2020Data() {
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
