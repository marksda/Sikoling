package org.Sikoling.ejb.main.repository.bentukusaha;

import java.io.Serializable;
import jakarta.persistence.*;


@Entity
@Table(name="master.tbl_bentuk_usaha")
@NamedQueries({
@NamedQuery(name="BentukUsahaData.findAll", query="SELECT b FROM BentukUsahaData b"),
@NamedQuery(name="BentukUsahaData.findByNama", query="SELECT b FROM BentukUsahaData b WHERE b.nama ILIKE :nama"),
@NamedQuery(name="BentukUsahaData.findByPelakuUsaha", query="SELECT b FROM BentukUsahaData b WHERE b.idPelakuUsaha = :idPelakuUsaha"),
@NamedQuery(name="BentukUsahaData.findByPelakuUsahaAndNama", query="SELECT b FROM BentukUsahaData b WHERE b.nama ILIKE :nama AND b.idPelakuUsaha = :idPelakuUsaha")})
public class BentukUsahaData implements Serializable {
	
	private static final long serialVersionUID = -1267904959928056172L;

	@Id
	private String id;

	@Column(name="pelaku_usaha")
	private String idPelakuUsaha;

	@Column(name="nama")
	private String nama;
	
	@Column(name="singkatan")
	private String singkatan;

	public BentukUsahaData() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdPelakuUsaha() {
		return this.idPelakuUsaha;
	}

	public void setIdPelakuUsaha(String idPelakuUsaha) {
		this.idPelakuUsaha = idPelakuUsaha;
	}

	public String getNama() {
		return this.nama;
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
	
	

}