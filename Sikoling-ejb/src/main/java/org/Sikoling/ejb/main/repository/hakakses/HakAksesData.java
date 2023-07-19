package org.Sikoling.ejb.main.repository.hakakses;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_hak_akses")
@NamedQueries({
	@NamedQuery(name="HakAksesData.updateId", query="UPDATE HakAksesData SET id = :idBaru WHERE id = :idLama")
})
public class HakAksesData implements Serializable {

	private static final long serialVersionUID = 3823074314363288588L;
	
	@Id
	private String id;
	
	private String nama;
	
	private String keterangan;

	public HakAksesData() {
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

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

}
