package org.Sikoling.ejb.main.repository.pelakuusaha;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_detail_pelaku_usaha")
@NamedQueries({
	@NamedQuery(name="PelakuUsahaData.findAll", query="SELECT p FROM PelakuUsahaData p"),
	@NamedQuery(name="PelakuUsahaData.findByQueryNama", query="SELECT p FROM PelakuUsahaData p WHERE p.nama LIKE :nama"),
	@NamedQuery(name="PelakuUsahaData.findByKategoriPelakuUsaha", query="SELECT p FROM PelakuUsahaData p WHERE p.kategoriPelakuUsahaData.id = :idKategoriPelakuUsaha")
})
public class PelakuUsahaData implements Serializable {

	private static final long serialVersionUID = 6462001128432032704L;

	@Id
	private String id;
	
	private String nama;
	
	private String singkatan;
	
	@JoinColumn(name="kategori_pelaku_usaha", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private KategoriPelakuUsahaData kategoriPelakuUsahaData;

	public PelakuUsahaData() {
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

	public String getSingkatan() {
		return singkatan;
	}

	public void setSingkatan(String singkatan) {
		this.singkatan = singkatan;
	}

	public KategoriPelakuUsahaData getKategoriPelakuUsahaData() {
		return kategoriPelakuUsahaData;
	}

	public void setKategoriPelakuUsahaData(KategoriPelakuUsahaData kategoriPelakuUsahaData) {
		this.kategoriPelakuUsahaData = kategoriPelakuUsahaData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	} 	
	
}
