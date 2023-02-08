package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.AktaPendirian;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.LampiranSuratArahan;
import org.Sikoling.ejb.abstraction.entity.dokumen.NibOss;
import org.Sikoling.ejb.abstraction.entity.dokumen.RekomendasiDPLH;
import org.Sikoling.ejb.abstraction.entity.dokumen.RekomendasiUKLUPL;
import org.Sikoling.ejb.abstraction.entity.dokumen.SuratArahan;
import org.Sikoling.main.restful.authority.AuthorityDTO;
import org.Sikoling.main.restful.perusahaan.RegisterPerusahaanDTO;

public class RegisterDokumenDTO implements Serializable {

	private static final long serialVersionUID = 1384518698621127848L;
	private String id;
	private DokumenDTO dokumen;
	private RegisterPerusahaanDTO perusahaan;
	private String lokasiFile;
	private StatusDokumenDTO statusDokumen;
	private LocalDate tanggalRegistrasi;
	private AuthorityDTO uploader;
	private Boolean statusVerified;
	
	public RegisterDokumenDTO() {
	}
	
	public RegisterDokumenDTO(RegisterDokumen t) {		
		if(t != null) {
			this.id = t.getId();
			Dokumen dokumen = t.getDokumen() != null ? t.getDokumen() : null;
			if(dokumen instanceof SuratArahan) {
				this.dokumen = new SuratArahanDTO((SuratArahan) dokumen);
			}
			else if(dokumen instanceof LampiranSuratArahan) {
				this.dokumen = new LampiranSuratArahanDTO((LampiranSuratArahan) dokumen);
			}
			else if(dokumen instanceof AktaPendirian) {
				this.dokumen = new AktaPendirianDTO((AktaPendirian) dokumen);
			}
			else if(dokumen instanceof RekomendasiUKLUPL) {
				this.dokumen = new RekomendasiUKLUPLDTO((RekomendasiUKLUPL) dokumen);
			}
			else if(dokumen instanceof RekomendasiDPLH) {
				this.dokumen = new RekomendasiDPLHDTO((RekomendasiDPLH) dokumen);
			}
			else if(dokumen instanceof NibOss) {
				this.dokumen = new NibOssDTO((NibOss) dokumen);
			}
			else {
				this.dokumen = null;
			}
			
			this.perusahaan = t.getPerusahaan() != null ? new RegisterPerusahaanDTO(t.getPerusahaan()) : null;
			this.lokasiFile = t.getLokasiFile();
			this.statusDokumen = t.getStatusDokumen() != null ? new StatusDokumenDTO(t.getStatusDokumen()) : null;
			this.tanggalRegistrasi = t.getTanggalRegistrasi();
			this.uploader = t.getUploader() != null ? new AuthorityDTO(t.getUploader()) : null;
			this.statusVerified = t.getStatusVerified() != null ? t.getStatusVerified() : null;
		}
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AuthorityDTO getUploader() {
		return uploader;
	}

	public void setUploader(AuthorityDTO uploader) {
		this.uploader = uploader;
	}

	public DokumenDTO getDokumen() {
		return dokumen;
	}

	public void setDokumen(DokumenDTO dokumen) {
		this.dokumen = dokumen;
	}

	public RegisterPerusahaanDTO getPerusahaan() {
		return perusahaan;
	}

	public void setPerusahaan(RegisterPerusahaanDTO perusahaan) {
		this.perusahaan = perusahaan;
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

	public Boolean getStatusVerified() {
		return statusVerified;
	}

	public void setStatusVerified(Boolean statusVerified) {
		this.statusVerified = statusVerified;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 183;
        hash = 171 * hash + Objects.hashCode(id);
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
        
        final RegisterDokumenDTO other = (RegisterDokumenDTO) obj;
        
        if (!this.dokumen.getId().equals(other.getDokumen().getId())) {
            return false;
        }   
        
        if (!this.perusahaan.getId().equals(other.getPerusahaan().getId())) {
            return false;
        }  

        return true;
	}
	
	@Override
	public String toString() {
		return "RegisterDokumenDTO{idDokumen="
				.concat(dokumen.getId())
				.concat(", namaDokumen=")
				.concat(dokumen.getNama())
				.concat(", idRegisterPerusahaan=")
				.concat(perusahaan.getId())
				.concat("}");	  
	}

	public RegisterDokumen toRegisterDokumen() {
		Dokumen dokumen = null;
		
		if(this.dokumen != null) {
			if(this.dokumen instanceof SuratArahanDTO) {
				dokumen = ((SuratArahanDTO) this.dokumen).toSuratArahan();
			}
			else if(this.dokumen instanceof LampiranSuratArahanDTO) {
				dokumen = ((LampiranSuratArahanDTO) this.dokumen).toLampiranSuratArahan();
			}
			else if(this.dokumen instanceof AktaPendirianDTO) {
				dokumen = ((AktaPendirianDTO) this.dokumen).toAktaPendirian();
			}
			else if(this.dokumen instanceof RekomendasiUKLUPLDTO) {
				dokumen = ((RekomendasiUKLUPLDTO) this.dokumen).toRekomendasiUKLUPL();
			}
			else if(this.dokumen instanceof RekomendasiDPLHDTO) {
				dokumen = ((RekomendasiDPLHDTO) this.dokumen).toRekomendasiDPLH();
			}
			else if(this.dokumen instanceof NibOssDTO) {
				dokumen = ((NibOssDTO) this.dokumen).toNibOss();
			}
		}
		
		return new RegisterDokumen(
				id,
				dokumen,
				perusahaan != null ? perusahaan.toRegisterPerusahaan() : null,
				lokasiFile, 
				statusDokumen != null ? statusDokumen.toStatusDokumen() : null,
				tanggalRegistrasi, 
				uploader != null ? uploader.toAuthority() : null,
				statusVerified != null ? statusVerified.booleanValue() : null						
				);
	}
	
}
