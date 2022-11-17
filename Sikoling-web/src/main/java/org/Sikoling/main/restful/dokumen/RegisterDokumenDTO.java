package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.main.restful.perusahaan.PerusahaanDTO;

public class RegisterDokumenDTO implements Serializable {

	private static final long serialVersionUID = 1384518698621127848L;
	private String id;
	private PerusahaanDTO perusahaan;
	private DokumenDTO dokumen;
	private Date tanggal;
	private boolean isBerlaku;
	
	public RegisterDokumenDTO() {
	}
	
	public RegisterDokumenDTO(RegisterDokumen registerDokumen) {
		this.id = registerDokumen.getId();
		this.perusahaan = new PerusahaanDTO(registerDokumen.getPerusahaan());
		this.dokumen = new DokumenDTO(registerDokumen.getDokumen());
		this.tanggal = registerDokumen.getTanggalTransaksi();
		this.isBerlaku = registerDokumen.getIsBerlaku();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PerusahaanDTO getPerusahaan() {
		return perusahaan;
	}

	public void setPerusahaan(PerusahaanDTO perusahaan) {
		this.perusahaan = perusahaan;
	}

	public DokumenDTO getDokumen() {
		return dokumen;
	}

	public void setDokumen(DokumenDTO dokumen) {
		this.dokumen = dokumen;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public boolean isBerlaku() {
		return isBerlaku;
	}

	public void setBerlaku(boolean isBerlaku) {
		this.isBerlaku = isBerlaku;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 129;
        hash = 171 * hash + Objects.hashCode(this.id);
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
        
        if (!this.id.equalsIgnoreCase(other.getId())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "RegisterDokumenDTO{id="
				.concat(id)
				.concat(", namaPerusahaan=")
				.concat(perusahaan.getNama())
				.concat(", namaDokumen=")
				.concat(dokumen.getNama())
				.concat("}");	  
	}

	public RegisterDokumen toRegisterDokumen() {
		return new RegisterDokumen(id, dokumen.toDokumen(), tanggal, isBerlaku, perusahaan.toPerusahaan(), null);
	}
	
}
