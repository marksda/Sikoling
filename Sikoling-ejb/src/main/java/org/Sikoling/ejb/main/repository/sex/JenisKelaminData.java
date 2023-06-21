package org.Sikoling.ejb.main.repository.sex;

import java.io.Serializable;
import jakarta.persistence.*;


@Entity
@Table(name="master.tbl_jenis_kelamin")
public class JenisKelaminData implements Serializable {
	private static final long serialVersionUID = -4654923742493315238L;

	@Id
	private String id;

	private String nama;

	public JenisKelaminData() {
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