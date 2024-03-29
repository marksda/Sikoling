package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.main.repository.otoritas.OtoritasData;
import org.Sikoling.ejb.main.repository.perusahaan.RegisterPerusahaanData;

import jakarta.persistence.CascadeType;
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
@Table(name="transaksi.tbl_register_dokumen")
@NamedQueries({
	@NamedQuery(name="RegisterDokumenData.updateId", query="UPDATE RegisterDokumenData SET id = :idBaru WHERE id = :idLama")
})
public class RegisterDokumenData implements Serializable {

	private static final long serialVersionUID = 781878194764826140L;
		
	@Id
	private String id;

	@JoinColumn(name = "perusahaan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private RegisterPerusahaanData perusahaanData;
	
	@JoinColumn(name = "dokumen", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private DokumenData dokumenData;
	
	@Column(name="tanggal_registrasi", insertable = true, updatable = true)
	private LocalDate tanggalRegistrasi;
	
	@JoinColumn(name = "uploader", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private OtoritasData uploader;
	
	@Column(name="lokasi_file", insertable = true, updatable = true)
	private String lokasiFile;
	
	@JoinColumn(name = "status_dokumen", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private StatusDokumenData statusDokumen;
	
	@Column(name = "is_validated", insertable = true, updatable = true)
	private Boolean statusVerified;
	
	@OneToOne(mappedBy="registerDokumenData", cascade = CascadeType.ALL)
	private AktaPendirianData aktaPendirianData;
	
	@OneToOne(mappedBy = "registerDokumenData", cascade = CascadeType.ALL)	
	private DokumenNibOssData nibOssData;
	
	@OneToOne(mappedBy = "registerDokumenData", cascade = CascadeType.ALL)	
	private DokumenGenerikData generikData;
	
	@OneToOne(mappedBy = "registerDokumenData", cascade = CascadeType.PERSIST)
	private SuratArahanData suratArahanData;
	
	@OneToOne(mappedBy = "registerDokumenData", cascade = CascadeType.ALL)
	private LampiranSuratArahanData lampiranSuratArahanData;
	
	@OneToOne(mappedBy = "registerDokumenData", cascade = CascadeType.ALL)
	private RekomendasiUKLUPLData rekomendasiUKLUPLData;	
	
	@OneToOne(mappedBy = "registerDokumenData", cascade = CascadeType.ALL)
	private RekomendasiDPLHData rekomendasiDPLHData;
		
	public RegisterDokumenData() {
	}
				
	public DokumenNibOssData getNibOssData() {
		return nibOssData;
	}
	
	public void setNibOssData(DokumenNibOssData nibOssData) {
		this.nibOssData = nibOssData;
	}
	
	public RekomendasiDPLHData getRekomendasiDPLHData() {
		return rekomendasiDPLHData;
	}
	
	public void setRekomendasiDPLHData(RekomendasiDPLHData rekomendasiDPLHData) {
		this.rekomendasiDPLHData = rekomendasiDPLHData;
	}
		
	public RekomendasiUKLUPLData getRekomendasiUKLUPLData() {
		return rekomendasiUKLUPLData;
	}
	
	public void setRekomendasiUKLUPLData(RekomendasiUKLUPLData rekomendasiUKLUPLData) {
		this.rekomendasiUKLUPLData = rekomendasiUKLUPLData;
	}
	
	public LampiranSuratArahanData getLampiranSuratArahanData() {
		return lampiranSuratArahanData;
	}
	
	public void setLampiranSuratArahanData(LampiranSuratArahanData lampiranSuratArahanData) {
		this.lampiranSuratArahanData = lampiranSuratArahanData;
	}
	
	public AktaPendirianData getAktaPendirianData() {
		return aktaPendirianData;
	}
	
	public void setAktaPendirianData(AktaPendirianData aktaPendirianData) {
		this.aktaPendirianData = aktaPendirianData;
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
	
	public DokumenData getDokumenData() {
		return dokumenData;
	}
	
	public void setDokumenData(DokumenData dokumenData) {
		this.dokumenData = dokumenData;
	}
	
	public LocalDate getTanggalRegistrasi() {
		return tanggalRegistrasi;
	}
	
	public void setTanggalRegistrasi(LocalDate tanggalRegistrasi) {
		this.tanggalRegistrasi = tanggalRegistrasi;
	}
	
	public OtoritasData getUploader() {
		return uploader;
	}
	
	public void setUploader(OtoritasData uploader) {
		this.uploader = uploader;
	}
	
	public String getLokasiFile() {
		return lokasiFile;
	}
	
	public void setLokasiFile(String lokasiFile) {
		this.lokasiFile = lokasiFile;
	}
	
	public Boolean getStatusVerified() {
		return statusVerified;
	}
	
	public void setStatusVerified(Boolean statusVerified) {
		this.statusVerified = statusVerified;
	}
	
	public StatusDokumenData getStatusDokumen() {
		return statusDokumen;
	}
	
	public void setStatusDokumen(StatusDokumenData statusDokumen) {
		this.statusDokumen = statusDokumen;
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
	
	public DokumenGenerikData getGenerikData() {
		return generikData;
	}

	public void setGenerikData(DokumenGenerikData generikData) {
		this.generikData = generikData;
	}
	
	@Override
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
