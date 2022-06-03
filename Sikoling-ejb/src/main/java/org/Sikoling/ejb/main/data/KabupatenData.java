package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_kabupaten database table.
 * 
 */
@Entity
@Table(name="tbl_kabupaten")
@NamedQuery(name="KabupatenData.findAll", query="SELECT k FROM KabupatenData k")
public class KabupatenData implements Serializable {
	private static final long serialVersionUID = -7026002892763939209L;

	@Id
	private String id;

	@Column(name="nama")
	private String nama;

	@Column(name="produk")
	@JoinColumn(name = "propinsi", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private PropinsiData propinsi;

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

	public PropinsiData getPropinsi() {
		return this.propinsi;
	}

	public void setPropinsi(PropinsiData propinsi) {
		this.propinsi = propinsi;
	}

}