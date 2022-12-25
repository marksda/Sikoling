package org.Sikoling.main.restful.perusahaan;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.dokumen.AktaPendirian;

public class AktaPemrakarsaDTO implements Serializable {

	private static final long serialVersionUID = 6255749816920528800L;
	private String nomor;
	private Date tanggal;
	private String namaNotaris;
	
	public AktaPemrakarsaDTO() {		
	}
	
	public AktaPemrakarsaDTO(AktaPendirian aktaPemrakarsa) {
		this.nomor = aktaPemrakarsa.getNomor();
		this.tanggal = aktaPemrakarsa.getTanggal();
		this.namaNotaris = aktaPemrakarsa.getNamaNotaris();
	}
	
	public AktaPemrakarsaDTO(String nomor, Date tanggal, String namaNotaris) {
		super();
		this.nomor = nomor;
		this.tanggal = tanggal;
		this.namaNotaris = namaNotaris;
	}

	public String getNomor() {
		return nomor;
	}

	public void setNomor(String nomor) {
		this.nomor = nomor;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public String getNamaNotaris() {
		return namaNotaris;
	}

	public void setNamaNotaris(String namaNotaris) {
		this.namaNotaris = namaNotaris;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
	public int hashCode() {
		int hash = 19;
        hash = 121 * hash + Objects.hashCode(this.nomor);
        hash = 121 * hash + Objects.hashCode(this.tanggal.toString());
        hash = 121 * hash + Objects.hashCode(this.nomor);
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
        
        final AktaPemrakarsaDTO other = (AktaPemrakarsaDTO) obj;
        
        if (!this.nomor.equalsIgnoreCase(other.nomor)) {
            return false;
        }
        
        if (!Objects.equals(this.tanggal, other.tanggal)) {
            return false;
        }
        
        if (!this.namaNotaris.equalsIgnoreCase(other.namaNotaris)) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "AktaPemrakarsaDTO{" + "nomor=" + nomor + ", namaNotaris=" + namaNotaris + ", tanggal=" + tanggal.toString() + "}";	  
	}

	public AktaPendirian toAktaPemrakarsa() {
		return new AktaPendirian(nomor, tanggal, namaNotaris);
	}
}
