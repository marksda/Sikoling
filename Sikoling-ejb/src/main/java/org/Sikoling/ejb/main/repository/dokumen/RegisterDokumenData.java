package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.perusahaan.PerusahaanData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(RegisterDokumenDataId.class)
public class RegisterDokumenData implements Serializable {

	private static final long serialVersionUID = 781878194764826140L;
		
	@Id
	@JoinColumn(name = "perusahaan", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne(optional = false)
	private PerusahaanData perusahaanData;
	
	@Id
	@JoinColumn(name = "dokumen", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne(optional = false)
	private DokumenData dokumenData;
	
	@Column(name="lokasi_file")
	private String lokasiFile;
	
	@Column(name="tanggal_upload", columnDefinition = "DATE")
	private LocalDate tanggalRegistrasi;
	
	@Column(name="is_berlaku")
	private boolean statusBerlaku;	

	@JoinColumn(name = "uploader", referencedColumnName = "nik", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private AutorisasiData uploader;
	
	@OneToOne(mappedBy = "registerDokumenData")
	private RegisterDokumenOssData registerDokumenOssData;
	
	public RegisterDokumenData() {
	}

	public PerusahaanData getPerusahaanData() {
		return perusahaanData;
	}

	public void setPerusahaan(PerusahaanData perusahaanData) {
		this.perusahaanData = perusahaanData;
	}

	public DokumenData getDokumenData() {
		return dokumenData;
	}

	public void setDokumenData(DokumenData dokumenData) {
		this.dokumenData = dokumenData;
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

	public RegisterDokumenOssData getRegisterDokumenOssData() {
		return registerDokumenOssData;
	}

	public void setRegisterDokumenOssData(RegisterDokumenOssData registerDokumenOssData) {
		this.registerDokumenOssData = registerDokumenOssData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
