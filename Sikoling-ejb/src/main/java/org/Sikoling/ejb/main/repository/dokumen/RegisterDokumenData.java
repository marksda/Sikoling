package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.perusahaan.RegisterPerusahaanData;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_register_dokumen")
@NamedQueries({
	@NamedQuery(name="RegisterDokumenData.findAll", query="SELECT d FROM RegisterDokumenData d"),
	@NamedQuery(name="RegisterDokumenData.findByNamaDokumen", query = "SELECT d FROM RegisterDokumenData d WHERE d.dokumenData.nama LIKE :namaDokumen"),
	@NamedQuery(name="RegisterDokumenData.findByIdDokumen", query="SELECT d FROM RegisterDokumenData d WHERE d.dokumenData.id = :idDokumen"),
	@NamedQuery(name="RegisterDokumenData.findByNamaPerusahaan", query = "SELECT d FROM RegisterDokumenData d WHERE d.perusahaanData.nama LIKE :namaPerusahaan"),
	@NamedQuery(name="RegisterDokumenData.findByIdPerusahaan", query="SELECT d FROM RegisterDokumenData d WHERE d.perusahaanData.id = :idPerusahaan")
})
public class RegisterDokumenData implements Serializable {

	private static final long serialVersionUID = 781878194764826140L;
		
	@Id
	private String id;
	
	@JoinColumn(name = "perusahaan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne(optional = false)
	private RegisterPerusahaanData perusahaanData;
	
	@Id
	@JoinColumn(name = "dokumen", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne(optional = false)
	private MasterDokumenData dokumenData;
	
	@Column(name="tanggal_registrasi")
	private LocalDate tanggalRegistrasi;
	
	@JoinColumn(name = "uploader", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne(optional = false)
	private AutorisasiData uploader;
	
	@Column(name="lokasi_file")
	private String lokasiFile;
	
	@OneToOne(mappedBy = "registerDokumenData", fetch = FetchType.LAZY)
	private RegisterDokumenOssData dokumenOssData;
	
	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	private SuratArahanData suratArahanData;
	
	public RegisterDokumenData() {
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public RegisterPerusahaanData getPerusahaanData() {
		return perusahaanData;
	}

	public void setPerusahaanData(RegisterPerusahaanData perusahaanData) {
		this.perusahaanData = perusahaanData;
	}

	public MasterDokumenData getDokumenData() {
		return dokumenData;
	}

	public void setDokumenData(MasterDokumenData dokumenData) {
		this.dokumenData = dokumenData;
	}

	public LocalDate getTanggalRegistrasi() {
		return tanggalRegistrasi;
	}

	public void setTanggalRegistrasi(LocalDate tanggalRegistrasi) {
		this.tanggalRegistrasi = tanggalRegistrasi;
	}

	public AutorisasiData getUploader() {
		return uploader;
	}

	public void setUploader(AutorisasiData uploader) {
		this.uploader = uploader;
	}

	public String getLokasiFile() {
		return lokasiFile;
	}

	public void setLokasiFile(String lokasiFile) {
		this.lokasiFile = lokasiFile;
	}

	public RegisterDokumenOssData getDokumenOssData() {
		return dokumenOssData;
	}

	public void setDokumenOssData(RegisterDokumenOssData dokumenOssData) {
		this.dokumenOssData = dokumenOssData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
	public SuratArahanData getSuratArahanData() {
		return suratArahanData;
	}

	public void setSuratArahanData(SuratArahanData suratArahanData) {
		this.suratArahanData = suratArahanData;
	}

	public int hashCode() {
		int hash = 71;
		hash = 13 * hash + Objects.hashCode(this.perusahaanData.getId());
		hash = 13 * hash + Objects.hashCode(this.dokumenData.getId());
		return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
		
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final RegisterDokumenData other = (RegisterDokumenData) obj;
        
        if (!this.perusahaanData.getId().equals(other.getPerusahaanData().getId())) {
            return false;
        }
        
        if (!this.dokumenData.getId().equals(other.getDokumenData().getId())) {
            return false;
        }
        
        return true;
	}
	
}
