package org.Sikoling.ejb.main.repository.pelakuusaha;

import java.io.Serializable;

import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_kategori_pelaku_usaha")
@NamedQueries({
	@NamedQuery(name="KategoriPelakuUsahaData.findAll", query="SELECT k FROM KategoriPelakuUsahaData k"),
	@NamedQuery(name="KategoriPelakuUsahaData.findAllBySkalaUsaha", query="SELECT k FROM KategoriPelakuUsahaData k WHERE k.skalaUsaha.id = :idSkalaUsaha"),
	@NamedQuery(name="KategoriPelakuUsahaData.findByQueryNama", query="SELECT k FROM KategoriPelakuUsahaData k WHERE k.nama LIKE :nama")
})
public class KategoriPelakuUsahaData implements Serializable {

	private static final long serialVersionUID = -9028556396730975196L;
	
	@Id
	private String id;
	
	private String nama;
	
	@JoinColumn(name="skala_usaha", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private SkalaUsahaData skalaUsaha;

	public KategoriPelakuUsahaData() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

}
