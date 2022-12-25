package org.Sikoling.ejb.main.repository.permohonan;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_kategori_pengurus_permohonan")
@NamedQueries({
	@NamedQuery(name="KategoriPengurusPermohonanData.findAll", query="SELECT p FROM KategoriPermohonanData p")
})
public class KategoriPengurusPermohonanData implements Serializable {

	private static final long serialVersionUID = 2634028457496165714L;
	
	@Id
	private String id;
	
	private String nama;

	public KategoriPengurusPermohonanData() {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
