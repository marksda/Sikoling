package org.Sikoling.ejb.main.repository.kabupaten;

import java.io.Serializable;
import javax.persistence.*;

import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;


/**
 * The persistent class for the tbl_kabupaten database table.
 * 
 */
@Entity
@Table(name="master.tbl_kabupaten")
@NamedQueries({
@NamedQuery(name="KabupatenData.findAll", query="SELECT k FROM KabupatenData k"),
@NamedQuery(name="KabupatenData.findAllByQueryNama", query="SELECT k FROM KabupatenData k WHERE k.nama ILIKE :nama"),
@NamedQuery(name="KabupatenData.findAllByIdPropinsi", query="SELECT k FROM KabupatenData k WHERE k.propinsi.id = :id"),
@NamedQuery(name="KabupatenData.findAllByIdPropinsiAndQueryNama", query="SELECT k FROM KabupatenData k WHERE k.propinsi.id = :id")})
public class KabupatenData implements Serializable {
	private static final long serialVersionUID = -7026002892763939209L;

	@Id
	private String id;

	@Column(name="nama")
	private String nama;

	@Column(name="propinsi")
	@JoinColumn(name = "propinsi", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private PropinsiData propinsi;

	public KabupatenData() {
	}

	public KabupatenData(String id, String nama, PropinsiData propinsi) {
		super();
		this.id = id;
		this.nama = nama;
		this.propinsi = propinsi;
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

	public PropinsiData getPropinsi() {
		return this.propinsi;
	}

}