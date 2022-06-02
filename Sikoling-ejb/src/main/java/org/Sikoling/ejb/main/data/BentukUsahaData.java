package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_bentuk_usaha database table.
 * 
 */
@Entity
@Table(name="tbl_bentuk_usaha")
@NamedQuery(name="BentukUsahaData.findAll", query="SELECT b FROM BentukUsahaData b")
public class BentukUsahaData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="kelompok_bentuk_usaha")
	@JoinColumn(name = "kelompok_bentuk_usaha", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private KelompokBentukUsahaData kelompokBentukUsaha;

	private String keterangan;

	public BentukUsahaData() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public KelompokBentukUsahaData getKelompokBentukUsaha() {
		return this.kelompokBentukUsaha;
	}

	public void setKelompokBentukUsaha(KelompokBentukUsahaData kelompokBentukUsaha) {
		this.kelompokBentukUsaha = kelompokBentukUsaha;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

}