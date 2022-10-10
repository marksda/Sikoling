package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_kategori_skala_usaha")
@NamedQueries({
	@NamedQuery(name="SkalaUsahaData.findAll", query="SELECT s FROM SkalaUsahaData s"),
	@NamedQuery(name="SkalaUsahaData.findByQueryNama", query="SELECT s FROM SkalaUsahaData s WHERE s.nama LIKE :nama")
})
public class SkalaUsahaData implements Serializable {

	private static final long serialVersionUID = -5936817258662714088L;
	
	@Id
	private String id;
	
	private String nama;
	
	private String singkatan;

	public SkalaUsahaData() {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
