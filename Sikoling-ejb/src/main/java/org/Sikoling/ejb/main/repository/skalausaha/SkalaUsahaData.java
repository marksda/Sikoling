package org.Sikoling.ejb.main.repository.skalausaha;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_kategori_skala_usaha")
@NamedQueries({
	@NamedQuery(name="SkalaUsahaData.updateId", query="UPDATE SkalaUsahaData SET id = :idBaru WHERE id = :idLama")
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
