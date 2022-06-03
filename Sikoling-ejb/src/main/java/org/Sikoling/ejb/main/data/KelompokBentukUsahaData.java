package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_kelompok_bentuk_usaha database table.
 * 
 */
@Entity
@Table(name="tbl_kelompok_bentuk_usaha")
@NamedQuery(name="KelompokBentukUsahaData.findAll", query="SELECT k FROM KelompokBentukUsahaData k")
public class KelompokBentukUsahaData implements Serializable {
	private static final long serialVersionUID = -8189342523798654485L;

	@Id
	private String id;

	private String keterangan;

	public KelompokBentukUsahaData() {
	}

	public KelompokBentukUsahaData(String id, String keterangan) {
		super();
		this.id = id;
		this.keterangan = keterangan;
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