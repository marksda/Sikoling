package org.Sikoling.ejb.main.repository.kelompokbentukusaha;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

import org.Sikoling.ejb.main.repository.bentukusaha.BentukUsahaData;


@Entity
@Table(name="master.tbl_kelompok_bentuk_usaha")
@NamedQuery(name="KelompokBentukUsahaData.findAll", query="SELECT k FROM KelompokBentukUsahaData k")
public class KelompokBentukUsahaData implements Serializable {
	private static final long serialVersionUID = -8189342523798654485L;

	@Id
	private String id;

	@Column(name="nama")
	private String nama;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kelompok_bentuk_usaha", fetch = FetchType.EAGER)
	private List<BentukUsahaData> daftarBentukUsahaData;

	public KelompokBentukUsahaData() {
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