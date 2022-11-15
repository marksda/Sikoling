package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.util.Date;

import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.perusahaan.PerusahaanData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="transaksi.tbl_master_register_dokumen")
@NamedQueries({
	@NamedQuery(name="TransaksiDokumenData.findAll", query="SELECT d FROM TransaksiDokumenData d")
})
public class RegisterDokumenData implements Serializable {

	private static final long serialVersionUID = 781878194764826140L;
	
	@Id
	private String id;
	
	@JoinColumn(name = "perusahaan", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private PerusahaanData perusahaan;
	
	@JoinColumn(name = "detail_dokumen", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private DokumenData dokumen;
	
	@Column(name="tanggal_upload")
	@Temporal(TemporalType.DATE)
	private Date tanggalUpload;
	
	@Column(name="is_berlaku")
	private boolean isBerlaku;	

	@JoinColumn(name = "uploader", referencedColumnName = "nik", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private AutorisasiData uploader;
	
	@Column(columnDefinition = "json")
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PerusahaanData getPerusahaan() {
		return perusahaan;
	}

	public void setPerusahaan(PerusahaanData perusahaan) {
		this.perusahaan = perusahaan;
	}

	public DokumenData getDokumen() {
		return dokumen;
	}

	public void setDokumen(DokumenData dokumen) {
		this.dokumen = dokumen;
	}

	public Date getTanggalUpload() {
		return tanggalUpload;
	}

	public void setTanggalUpload(Date tanggalUpload) {
		this.tanggalUpload = tanggalUpload;
	}

	public boolean getIsBerlaku() {
		return isBerlaku;
	}

	public void setIsBerlaku(boolean isBerlaku) {
		this.isBerlaku = isBerlaku;
	}

	public AutorisasiData getUploader() {
		return uploader;
	}

	public void setUploader(AutorisasiData uploader) {
		this.uploader = uploader;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
