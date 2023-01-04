package org.Sikoling.ejb.main.repository.log;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_kategori_log")
@NamedQueries({
	@NamedQuery(name="KategoriLogData.findAll", query="SELECT p FROM KategoriLogData p")	
})
public class KategoriLogData implements Serializable {

	private static final long serialVersionUID = -6741872398673596100L;
	
	@Id
	private String id;
	
	private String nama;

	public KategoriLogData() {
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
