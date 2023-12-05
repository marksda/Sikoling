package org.Sikoling.ejb.main.repository.dokumen;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_kbli_2020")
@NamedQueries({
	@NamedQuery(name="KbliData.updateId", query="UPDATE KbliData SET id = :idBaru WHERE id = :idLama")
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
