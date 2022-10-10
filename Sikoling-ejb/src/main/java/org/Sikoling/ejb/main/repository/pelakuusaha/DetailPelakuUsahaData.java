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
	@NamedQuery(name="DetailPelakuUsahaData.findAll", query="SELECT p FROM DetailPelakuUsahaData p"),
	@NamedQuery(name="DetailPelakuUsahaData.findByQueryNama", query="SELECT p FROM DetailPelakuUsahaData p WHERE p.nama LIKE :nama"),
	@NamedQuery(name="DetailPelakuUsahaData.findByPelakuUsaha", query="SELECT p FROM DetailPelakuUsahaData p WHERE p.jenisPelakuUsahaData.id = :idPelakuUsaha")
})
public class DetailPelakuUsahaData implements Serializable {

	private static final long serialVersionUID = 6462001128432032704L;

	@Id
	private String id;
	
	private String nama;
	
	private String singkatan;
	
	@JoinColumn(name="kategori_pelaku_usaha", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private JenisPelakuUsahaData jenisPelakuUsahaData;

	public DetailPelakuUsahaData() {
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

	public JenisPelakuUsahaData getJenisPelakuUsahaData() {
		return jenisPelakuUsahaData;
	}

	public void setJenisPelakuUsahaData(JenisPelakuUsahaData jenisPelakuUsahaData) {
		this.jenisPelakuUsahaData = jenisPelakuUsahaData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	} 	
	
}
