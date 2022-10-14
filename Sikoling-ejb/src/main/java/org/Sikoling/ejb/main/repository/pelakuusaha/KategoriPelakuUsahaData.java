package org.Sikoling.ejb.main.repository.pelakuusaha;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_kategori_pelaku_usaha")
@NamedQueries({
	@NamedQuery(name="KategoriPelakuUsahaData.findAll", query="SELECT k FROM KategoriPelakuUsahaData k"),
	@NamedQuery(name="KategoriPelakuUsahaData.findByQueryNama", query="SELECT k FROM KategoriPelakuUsahaData k WHERE k.nama LIKE :nama")
})
public class KategoriPelakuUsahaData implements Serializable {

	private static final long serialVersionUID = -9028556396730975196L;
	
	@Id
	private String id;
	
	private String nama;

	public KategoriPelakuUsahaData() {
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
