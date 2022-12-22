package org.Sikoling.ejb.main.repository.permohonan;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_status_pengurus_permohonan")
@NamedQueries({
	@NamedQuery(name="StatusPengurusPermohonanData.findAll", query="SELECT p FROM StatusPengurusPermohonanData p"),
	@NamedQuery(name="StatusPengurusPermohonanData.findByQueryNama", query="SELECT p FROM StatusPengurusPermohonanData p WHERE p.nama LIKE :nama")
})
public class StatusPengurusPermohonanData implements Serializable {

	private static final long serialVersionUID = 2225246190837080688L;
	
	@Id
	private String id;
	
	private String nama;

	public StatusPengurusPermohonanData() {
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
