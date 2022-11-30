package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.main.repository.person.PersonData;
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
	private MasterDokumenData dokumenData;
	
	@Column(name="tanggal_registrasi")
	private LocalDate tanggalRegistrasi;
	
	@JoinColumn(name = "uploader", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne(optional = false)
	private PersonData uploader;
	
	@Column(name="lokasi_file")
	private String lokasiFile;
	
	@OneToOne(mappedBy = "registerDokumenData")
	private RegisterDokumenOssData dokumenOssData;
	
	public RegisterDokumenData() {
	}

	public PerusahaanData getPerusahaanData() {
		return perusahaanData;
	}

	public void setPerusahaanData(PerusahaanData perusahaanData) {
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

	public PersonData getUploader() {
		return uploader;
	}

	public void setUploader(PersonData uploader) {
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
