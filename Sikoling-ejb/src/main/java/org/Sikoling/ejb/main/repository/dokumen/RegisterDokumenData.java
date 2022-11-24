package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.perusahaan.PerusahaanData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_master_register_dokumen")
@NamedQueries({
	@NamedQuery(name="RegisterDokumenData.findAll", query="SELECT d FROM RegisterDokumenData d"),
	@NamedQuery(name="RegisterDokumenData.findByNamaDokumen", query = "SELECT d FROM RegisterDokumenData d WHERE d.dokumen.nama LIKE :namaDokumen"),
	@NamedQuery(name="RegisterDokumenData.findByIdDokumen", query="SELECT d FROM RegisterDokumenData d WHERE d.dokumen.id = :idDokumen"),
	@NamedQuery(name="RegisterDokumenData.findByNamaPerusahaan", query = "SELECT d FROM RegisterDokumenData d WHERE d.perusahaan.nama LIKE :namaPerusahaan"),
	@NamedQuery(name="RegisterDokumenData.findByIdPerusahaan", query="SELECT d FROM RegisterDokumenData d WHERE d.perusahaan.id = :idPerusahaan")
})
public class RegisterDokumenData implements Serializable {

	private static final long serialVersionUID = 781878194764826140L;
	
	@Id
	private String id;
	
	@JoinColumn(name = "perusahaan", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne(optional = false)
	private PerusahaanData perusahaan;
	
	@JoinColumn(name = "detail_dokumen", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne(optional = false)
	private DokumenData dokumen;
	
	@Column(name="lokasi_file")
	private String lokasiFile;
	
	@Column(name="tanggal_upload", columnDefinition = "DATE")
	private LocalDate tanggalRegistrasi;
	
	@Column(name="is_berlaku")
	private boolean statusBerlaku;	

	@JoinColumn(name = "uploader", referencedColumnName = "nik", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private AutorisasiData uploader;
	
	@OneToOne(mappedBy = "registerDokumen")
    private DokumenOssData dokumenOssData;	
	
	public RegisterDokumenData() {
	}

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

	public String getLokasiFile() {
		return lokasiFile;
	}

	public void setLokasiFile(String lokasiFile) {
		this.lokasiFile = lokasiFile;
	}

	public LocalDate getTanggalRegistrasi() {
		return tanggalRegistrasi;
	}

	public void setTanggalRegistrasi(LocalDate tanggalRegistrasi) {
		this.tanggalRegistrasi = tanggalRegistrasi;
	}

	public boolean isStatusBerlaku() {
		return statusBerlaku;
	}

	public void setStatusBerlaku(boolean statusBerlaku) {
		this.statusBerlaku = statusBerlaku;
	}

	public AutorisasiData getUploader() {
		return uploader;
	}

	public void setUploader(AutorisasiData uploader) {
		this.uploader = uploader;
	}

	public DokumenOssData getDokumenOssData() {
		return dokumenOssData;
	}

	public void setDokumenOssData(DokumenOssData dokumenOssData) {
		this.dokumenOssData = dokumenOssData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
