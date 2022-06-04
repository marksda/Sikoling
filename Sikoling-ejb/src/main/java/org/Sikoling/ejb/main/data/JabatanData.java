package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_jabatan database table.
 * 
 */
@Entity
@Table(name="tbl_jabatan")
@NamedQuery(name="JabatanData.findAll", query="SELECT j FROM JabatanData j")
public class JabatanData implements Serializable {
	private static final long serialVersionUID = 3134858894526254188L;

	@Id
	private String id;

	private String nama;

	public JabatanData() {
	}

	public JabatanData(String id, String nama) {
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

	public void setNama(String nama) {
		this.nama = nama;
	}

}