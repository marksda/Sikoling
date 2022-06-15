package org.Sikoling.ejb.main.repository.bentukusaha;

import java.io.Serializable;
import jakarta.persistence.*;

import org.Sikoling.ejb.main.repository.kelompokbentukusaha.JenisPelakuUsahaData;


@Entity
@Table(name="master.tbl_bentuk_usaha")
@NamedQueries({
@NamedQuery(name="BentukUsahaData.findAll", query="SELECT b FROM BentukUsahaData b"),
@NamedQuery(name="BentukUsahaData.findAllByQueryNama", query="SELECT b FROM BentukUsahaData b WHERE b.nama ILIKE :nama"),
@NamedQuery(name="BentukUsahaData.findAllByKelompokUsaha", query="SELECT b FROM BentukUsahaData b WHERE b.kelompokBentukUsaha.id = :idKelompok"),
@NamedQuery(name="BentukUsahaData.findAllByKelompokUsahaAndQueryNama", query="SELECT b FROM BentukUsahaData b WHERE b.nama ILIKE :nama AND b.kelompokBentukUsaha.id = :idKelompok")})
public class BentukUsahaData implements Serializable {
	
	private static final long serialVersionUID = -1267904959928056172L;

	@Id
	private String id;

	@Column(name="kelompok_bentuk_usaha")
	@JoinColumn(name = "kelompok_bentuk_usaha", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private JenisPelakuUsahaData kelompokBentukUsaha;

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

	public JenisPelakuUsahaData getKelompokBentukUsaha() {
		return this.kelompokBentukUsaha;
	}

	public void setKelompokBentukUsaha(JenisPelakuUsahaData kelompokBentukUsaha) {
		this.kelompokBentukUsaha = kelompokBentukUsaha;
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