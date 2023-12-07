package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_dokumen")
@NamedQueries({
	@NamedQuery(name="DokumenData.updateId", query="UPDATE DokumenData SET id = :idBaru WHERE id = :idLama")
})
public class DokumenData implements Serializable {

	private static final long serialVersionUID = -3312507197298632070L;
	
	@Id
	private String id;
	
	private String nama;

	public DokumenData() {
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
