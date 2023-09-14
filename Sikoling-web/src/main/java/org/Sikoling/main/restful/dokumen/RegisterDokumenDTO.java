package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.DokumenAktaPendirian;
import org.Sikoling.ejb.abstraction.entity.dokumen.DokumenGenerik;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.LampiranSuratArahan;
import org.Sikoling.ejb.abstraction.entity.dokumen.DokumenNibOss;
import org.Sikoling.ejb.abstraction.entity.dokumen.RekomendasiDPLH;
import org.Sikoling.ejb.abstraction.entity.dokumen.RekomendasiUKLUPL;
import org.Sikoling.ejb.abstraction.entity.dokumen.SuratArahan;
import org.Sikoling.main.restful.otoritas.OtoritasDTO;
import org.Sikoling.main.restful.perusahaan.RegisterPerusahaanDTO;

public class RegisterDokumenDTO implements Serializable {

	private static final long serialVersionUID = 1384518698621127848L;
	private String id;
	private DokumenDTO dokumen;
	private RegisterPerusahaanDTO registerPerusahaan;
	private String lokasiFile;
	private StatusDokumenDTO statusDokumen;
	private LocalDate tanggalRegistrasi;
	private OtoritasDTO uploader;
	private Boolean statusVerified;
	
	public RegisterDokumenDTO() {
	}
	
	public RegisterDokumenDTO(RegisterDokumen t) {		
		if(t != null) {
			this.id = t.getId();
			Dokumen dokumen = t.getDokumen() != null ? t.getDokumen() : null;
			
			if(dokumen instanceof DokumenAktaPendirian) {
				this.dokumen = new AktaPendirianDTO((DokumenAktaPendirian) dokumen);
			}
			else if(dokumen instanceof DokumenNibOss) {
				this.dokumen = new DokumenNibOssDTO((DokumenNibOss) dokumen);
			}
			else if(dokumen instanceof DokumenGenerik) {
				this.dokumen = new DokumenGenerikDTO((DokumenGenerik) dokumen);
			}
			else if(dokumen instanceof SuratArahan) {
				this.dokumen = new SuratArahanDTO((SuratArahan) dokumen);
			}
			else if(dokumen instanceof LampiranSuratArahan) {
				this.dokumen = new LampiranSuratArahanDTO((LampiranSuratArahan) dokumen);
			}			
			else if(dokumen instanceof RekomendasiUKLUPL) {
				this.dokumen = new RekomendasiUKLUPLDTO((RekomendasiUKLUPL) dokumen);
			}
			else if(dokumen instanceof RekomendasiDPLH) {
				this.dokumen = new RekomendasiDPLHDTO((RekomendasiDPLH) dokumen);
			}
			
			this.registerPerusahaan = t.getRegisterPerusahaan() != null ? new RegisterPerusahaanDTO(t.getRegisterPerusahaan()) : null;
			this.lokasiFile = t.getLokasiFile();
			this.statusDokumen = t.getStatusDokumen() != null ? new StatusDokumenDTO(t.getStatusDokumen()) : null;
			this.tanggalRegistrasi = t.getTanggalRegistrasi();
			this.uploader = t.getUploader() != null ? new OtoritasDTO(t.getUploader()) : null;
			this.statusVerified = t.getStatusVerified() != null ? t.getStatusVerified() : null;
		}
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OtoritasDTO getUploader() {
		return uploader;
	}

	public void setUploader(OtoritasDTO uploader) {
		this.uploader = uploader;
	}

	public DokumenDTO getDokumen() {
		return dokumen;
	}

	public void setDokumen(DokumenDTO dokumen) {
		this.dokumen = dokumen;
	}

	public RegisterPerusahaanDTO getRegisterPerusahaan() {
		return registerPerusahaan;
	}

	public void setRegisterPerusahaan(RegisterPerusahaanDTO registerPerusahaan) {
		this.registerPerusahaan = registerPerusahaan;
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
        
        if (!this.registerPerusahaan.getId().equals(other.getRegisterPerusahaan().getId())) {
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
				.concat(registerPerusahaan.getId())
				.concat("}");	  
	}

	public RegisterDokumen toRegisterDokumen() {
		Dokumen dokumen = null;
		
		if(this.dokumen != null) {
			if(this.dokumen instanceof AktaPendirianDTO) {
				dokumen = ((AktaPendirianDTO) this.dokumen).toAktaPendirian();
			}
			else if(this.dokumen instanceof DokumenNibOssDTO) {
				dokumen = ((DokumenNibOssDTO) this.dokumen).toDokumenNibOss();
			}
			else if(this.dokumen instanceof DokumenGenerikDTO) {
				dokumen = ((DokumenGenerikDTO) this.dokumen).toDokumenGenerik();
			}
			else if(this.dokumen instanceof SuratArahanDTO) {
				dokumen = ((SuratArahanDTO) this.dokumen).toSuratArahan();
			}
			else if(this.dokumen instanceof LampiranSuratArahanDTO) {
				dokumen = ((LampiranSuratArahanDTO) this.dokumen).toLampiranSuratArahan();
			}
			else if(this.dokumen instanceof RekomendasiUKLUPLDTO) {
				dokumen = ((RekomendasiUKLUPLDTO) this.dokumen).toRekomendasiUKLUPL();
			}
			else if(this.dokumen instanceof RekomendasiDPLHDTO) {
				dokumen = ((RekomendasiDPLHDTO) this.dokumen).toRekomendasiDPLH();
			}			
		}
		
		return new RegisterDokumen(
				id,
				dokumen,
				registerPerusahaan != null ? registerPerusahaan.toRegisterPerusahaan() : null,
				lokasiFile, 
				statusDokumen != null ? statusDokumen.toStatusDokumen() : null,
				tanggalRegistrasi, 
				uploader != null ? uploader.toOtoritas() : null,
				statusVerified != null ? statusVerified.booleanValue() : null						
				);
	}
	
	public StatusDokumenDTO getStatusDokumen() {
		return statusDokumen;
	}
	
	public void setStatusDokumen(StatusDokumenDTO statusDokumen) {
		this.statusDokumen = statusDokumen;
	}
	
}
