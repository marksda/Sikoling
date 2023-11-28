package org.Sikoling.ejb.main.repository.kategoripelakuusaha;

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
	@NamedQuery(name="KategoriPelakuUsahaData.updateId", query="UPDATE KategoriPelakuUsahaData SET id = :idBaru WHERE id = :idLama")
})
public class KategoriPelakuUsahaData implements Serializable {

	private static final long serialVersionUID = -9028556396730975196L;
	
	@Id
	private String id;
	
	private String nama;
	
	@JoinColumn(name="skala_usaha", referencedColumnName = "id", insertable = true, updatable = true)
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

	public SkalaUsahaData getSkalaUsaha() {
		return skalaUsaha;
	}

	public void setSkalaUsaha(SkalaUsahaData skalaUsaha) {
		this.skalaUsaha = skalaUsaha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

}
