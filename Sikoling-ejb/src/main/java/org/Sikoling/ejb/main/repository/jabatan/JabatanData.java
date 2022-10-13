package org.Sikoling.ejb.main.repository.jabatan;

import java.io.Serializable;
import jakarta.persistence.*;


@Entity
@Table(name="master.tbl_jabatan")
@NamedQueries({
	@NamedQuery(name="JabatanData.findAll", query="SELECT j FROM JabatanData j"),
	@NamedQuery(name="JabatanData.findByNama", query="SELECT j FROM JabatanData j WHERE j.nama LIKE :nama")
})
public class JabatanData implements Serializable {
	private static final long serialVersionUID = 3134858894526254188L;

	@Id
	private String id;

	private String nama;

	public JabatanData() {
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