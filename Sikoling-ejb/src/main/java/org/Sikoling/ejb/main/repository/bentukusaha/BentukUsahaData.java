package org.Sikoling.ejb.main.repository.bentukusaha;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="master.tbl_bentuk_usaha")
@NamedQueries({
@NamedQuery(name="BentukUsahaData.findAll", query="SELECT b FROM BentukUsahaData b"),
@NamedQuery(name="BentukUsahaData.findAllByQueryNama", query="SELECT b FROM BentukUsahaData b WHERE b.nama ILIKE :nama"),
@NamedQuery(name="BentukUsahaData.findAllByKelompokUsaha", query="SELECT b FROM BentukUsahaData b WHERE b.idKelompokBentukUsaha = :idKelompok"),
@NamedQuery(name="BentukUsahaData.findAllByKelompokUsahaAndQueryNama", query="SELECT b FROM BentukUsahaData b WHERE b.nama ILIKE :nama AND b.idKelompokBentukUsaha = :idKelompok")})
public class BentukUsahaData implements Serializable {
	
	private static final long serialVersionUID = -1267904959928056172L;

	@Id
	private String id;

	@Column(name="kelompok_bentuk_usaha")
	private String idKelompokBentukUsaha;

	@Column(name="nama")
	private String nama;

	public BentukUsahaData() {
	}

	public BentukUsahaData(String id, String idKelompokBentukUsaha, String nama) {
		super();
		this.id = id;
		this.idKelompokBentukUsaha = idKelompokBentukUsaha;
		this.nama = nama;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKelompokBentukUsaha() {
		return this.idKelompokBentukUsaha;
	}

	public void setIdKelompokBentukUsaha(String idKelompokBentukUsaha) {
		this.idKelompokBentukUsaha = idKelompokBentukUsaha;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

}