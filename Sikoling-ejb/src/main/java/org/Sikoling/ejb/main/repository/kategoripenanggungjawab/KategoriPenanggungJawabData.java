package org.Sikoling.ejb.main.repository.kategoripenanggungjawab;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="master.tbl_kategori_penanggung_jawab")
@NamedQueries({
@NamedQuery(name="KategoriPenanggungJawabData.findAll", query="SELECT k FROM KategoriPenanggungJawabData k"),
@NamedQuery(name="KategoriPenanggungJawabData.findAllByQueryNama", query="SELECT k FROM KategoriPenanggungJawabData k WHERE k.nama ILIKE :nama")})
public class KategoriPenanggungJawabData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String nama;

	public KategoriPenanggungJawabData() {
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