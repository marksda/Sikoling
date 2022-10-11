package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_kategori_dokumen_perusahaan")
@NamedQueries({
	@NamedQuery(name="KategoriDokumenPerusahaanData.findAll", query = "SELECT d FROM KategoriDokumenPerusahaanData d"),
	@NamedQuery(name="KategoriDokumenPerusahaanData.findByName", query = "SELECT d FROM KategoriDokumenPerusahaanData d WHERE d.nama LIKE :nama")
})
public class KategoriDokumenPerusahaanData implements Serializable {

	private static final long serialVersionUID = -3798387570437701800L;
	
	@Id
	private String id;
	
	private String nama;
	
	private String parent;

	public KategoriDokumenPerusahaanData() {
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

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
