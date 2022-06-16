package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the tbl_status_wali_pemohon database table.
 * 
 */
@Entity
@Table(name="tbl_status_wali_pemohon")
@NamedQuery(name="StatusWaliData.findAll", query="SELECT s FROM StatusWaliData s")
public class StatusWaliData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String nama;

	public StatusWaliData() {
	}

	public StatusWaliData(String id, String nama) {
		super();
		this.id = id;
		this.nama = nama;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return this.nama;
	}

	public void setKeterangan(String nama) {
		this.nama = nama;
	}

}