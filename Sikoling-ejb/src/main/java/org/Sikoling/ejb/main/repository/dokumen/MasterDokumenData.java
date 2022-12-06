package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_dokumen_perusahaan")
@NamedQueries({
	@NamedQuery(name="MasterDokumenData.findAll", query = "SELECT d FROM MasterDokumenData d"),
	@NamedQuery(name="MasterDokumenData.findByNama", query = "SELECT d FROM MasterDokumenData d WHERE d.nama LIKE :nama"),
	@NamedQuery(name="MasterDokumenData.findByKategori", query = "SELECT d FROM MasterDokumenData d WHERE d.kategoriDokumenData.id = :kategori")
})
public class MasterDokumenData implements Serializable {

	private static final long serialVersionUID = -3312507197298632070L;
	
	@Id
	private String id;
	
	private String nama;
	
	@JoinColumn(name = "kategori", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne(optional = false)
	private KategoriDokumenData kategoriDokumenData;

	public MasterDokumenData() {
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

	public KategoriDokumenData getKategoriDokumenData() {
		return kategoriDokumenData;
	}

	public void setKategoriDokumenData(KategoriDokumenData kategoriDokumenData) {
		this.kategoriDokumenData = kategoriDokumenData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
