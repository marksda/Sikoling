package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;


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

	private String keterangan;

	public StatusWaliData() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

}