package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_detail_dokumen_perusahaan")
@NamedQueries({
	@NamedQuery(name="DetailDokumenPerusahaanData.findAll", query = "SELECT d FROM DetailDokumenPerusahaanData d"),
	@NamedQuery(name="DetailDokumenPerusahaanData.findByNama", query = "SELECT d FROM DetailDokumenPerusahaanData d WHERE d.nama LIKE :nama"),
	@NamedQuery(name="DetailDokumenPerusahaanData.findByKategori", query = "SELECT d FROM DetailDokumenPerusahaanData d WHERE d.kategori = kategori")
})
public class DetailDokumenPerusahaanData implements Serializable {

	private static final long serialVersionUID = -3312507197298632070L;
	
	@Id
	private String id;
	
	private String nama;
	
	@JoinColumn(name = "kategori", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private KategoriDokumenPerusahaanData kategori;

	public DetailDokumenPerusahaanData() {
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

	public KategoriDokumenPerusahaanData getKategori() {
		return kategori;
	}

	public void setKategori(KategoriDokumenPerusahaanData kategori) {
		this.kategori = kategori;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
