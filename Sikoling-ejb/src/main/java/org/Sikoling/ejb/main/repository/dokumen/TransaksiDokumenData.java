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

@Entity
@Table(name="transaksi.tbl_dokumen")
@NamedQueries({
	@NamedQuery(name="DokumenPerusahaanData.findAll", query="SELECT d FROM DokumenData d")
})
public class TransaksiDokumenData implements Serializable {

	private static final long serialVersionUID = 781878194764826140L;
	
	@Id
	private String id;
	
	@JoinColumn(name = "perusahaan", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private PerusahaanData perusahaan;
	
	@JoinColumn(name = "detail_dokumen", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private DokumenData detailDokumen;
	
	@Column(name="tanggal_upload")
	private Date tanggalUpload;
	
	@Column(name="is_berlaku")
	private boolean isBerlaku;	

	@JoinColumn(name = "uploader", referencedColumnName = "nik", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private AutorisasiData uploader;
	
	@Column(columnDefinition = "json")
	private String attribute;
	
	@Column(name="lokasi_file")
	private String lokasiFile;

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

	public DokumenData getDetailDokumen() {
		return detailDokumen;
	}

	public void setDetailDokumen(DokumenData detailDokumen) {
		this.detailDokumen = detailDokumen;
	}

	public Date getTanggalUpload() {
		return tanggalUpload;
	}

	public void setTanggalUpload(Date tanggalUpload) {
		this.tanggalUpload = tanggalUpload;
	}

	public boolean isBerlaku() {
		return isBerlaku;
	}

	public void setBerlaku(boolean isBerlaku) {
		this.isBerlaku = isBerlaku;
	}

	public AutorisasiData getUploader() {
		return uploader;
	}

	public void setUploader(AutorisasiData uploader) {
		this.uploader = uploader;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getLokasiFile() {
		return lokasiFile;
	}

	public void setLokasiFile(String lokasiFile) {
		this.lokasiFile = lokasiFile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
