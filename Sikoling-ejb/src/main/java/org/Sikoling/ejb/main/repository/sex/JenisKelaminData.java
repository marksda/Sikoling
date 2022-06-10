package org.Sikoling.ejb.main.repository.sex;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_jenis_kelamin database table.
 * 
 */
@Entity
@Table(name="tbl_jenis_kelamin")
@NamedQueries({
@NamedQuery(name="JenisKelaminData.findAll", query="SELECT j FROM JenisKelaminData j"),
@NamedQuery(name="JenisKelaminData.findAllByQueryNama", query="SELECT j FROM JenisKelaminData j WHERE j.nama ILIKE :nama")
})
public class JenisKelaminData implements Serializable {
	private static final long serialVersionUID = -4654923742493315238L;

	@Id
	private String id;

	private String nama;

	public JenisKelaminData() {
	}

	public JenisKelaminData(String id, String nama) {
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