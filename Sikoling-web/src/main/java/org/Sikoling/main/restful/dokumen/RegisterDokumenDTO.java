package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Dokumen;
import org.Sikoling.ejb.abstraction.entity.DokumenOss;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;

public class RegisterDokumenDTO implements Serializable {

	private static final long serialVersionUID = 1384518698621127848L;
	private DokumenDTO dokumenDTO;
	private String lokasiFile;
	private LocalDate tanggalRegistrasi;
	private boolean statusBerlaku;
	
	public RegisterDokumenDTO() {
	}
	
	public RegisterDokumenDTO(RegisterDokumen registerDokumen) {		
		this.dokumenDTO = convertDokumenToDokumenDTO(registerDokumen.getDokumen());
		this.lokasiFile = registerDokumen.getLokasiFile();
		this.tanggalRegistrasi = registerDokumen.getTanggalRegistrasi();
		this.statusBerlaku = registerDokumen.isStatusBerlaku();
	}
	
	public DokumenDTO getDokumenDTO() {
		return dokumenDTO;
	}

	public void setDokumenDTO(DokumenDTO dokumenDTO) {
		this.dokumenDTO = dokumenDTO;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int hashCode() {
		int hash = 183;
        hash = 171 * hash + Objects.hashCode(dokumenDTO.getId());
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
        
        if (!this.dokumenDTO.getId().equals(other.getDokumenDTO().getId())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "RegisterDokumenDTO{idDokumen="
				.concat(dokumenDTO.getId())
				.concat(", namaDokumen=")
				.concat(dokumenDTO.getNama())
				.concat("}");	  
	}

	public RegisterDokumen toRegisterDokumen() {
		return new RegisterDokumen(
				dokumenDTO.toDokumen(), 
				lokasiFile, 
				tanggalRegistrasi, 
				statusBerlaku);
	}
	
	//fungsi ini digunakan untuk menginisialisasi dokumen induk 
	//dengan dokumen turunannya
	private DokumenDTO convertDokumenToDokumenDTO(Dokumen dokumen) {
		DokumenDTO dokumenDTO;
		switch (dokumen.getClass().getSimpleName()) {
		case "DokumenOss":
			dokumenDTO = new DokumenOssDTO((DokumenOss) dokumen);
			break;
		default:
			dokumenDTO = null;
			break;
		}
		return dokumenDTO;
	}
}
