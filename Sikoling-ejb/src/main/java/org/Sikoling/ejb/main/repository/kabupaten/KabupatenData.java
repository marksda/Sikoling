package org.Sikoling.ejb.main.repository.kabupaten;

import java.io.Serializable;
import jakarta.persistence.*;


@Entity
@Table(name="master.tbl_kabupaten")
@NamedQueries({
@NamedQuery(name="KabupatenData.findAll", query="SELECT k FROM KabupatenData k"),
@NamedQuery(name="KabupatenData.findAllByQueryNama", query="SELECT k FROM KabupatenData k WHERE k.nama ILIKE :nama"),
@NamedQuery(name="KabupatenData.findAllByIdPropinsi", query="SELECT k FROM KabupatenData k WHERE k.idPropinsi = :idPropinsi"),
@NamedQuery(name="KabupatenData.findAllByIdPropinsiAndQueryNama", query="SELECT k FROM KabupatenData k WHERE k.nama ILIKE :nama AND k.idPropinsi = :idPropinsi")})
public class KabupatenData implements Serializable {
	private static final long serialVersionUID = -7026002892763939209L;

	@Id
	private String id;

	@Column(name="nama")
	private String nama;

	@Column(name="propinsi")
	private String idPropinsi;

	public KabupatenData() {
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

	public String getIdPropinsi() {
		return this.idPropinsi;
	}

	public void setIdPropinsi(String idPropinsi) {
		this.idPropinsi = idPropinsi;
	}
	
}