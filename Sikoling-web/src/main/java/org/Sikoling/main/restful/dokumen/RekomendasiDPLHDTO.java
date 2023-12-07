package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.RekomendasiDPLH;

public class RekomendasiDPLHDTO extends DokumenDTO implements Serializable {

	private static final long serialVersionUID = -3450087905995320522L;
	private String noSurat;
	private LocalDate tanggalSurat;
	private String perihalSurat;
	
	public RekomendasiDPLHDTO() {
	}
	
	public RekomendasiDPLHDTO(RekomendasiDPLH t) {
		super(t != null ? new Dokumen(
				t.getId(), 
				t.getNama()
				) : null);

		if(t != null) {
			this.noSurat = t.getNomor();
			this.tanggalSurat = t.getTanggal();
			this.perihalSurat = t.getPerihal();
		}
	}
	
	public String getNoSurat() {
		return noSurat;
	}
	
	public void setNoSurat(String noSurat) {
		this.noSurat = noSurat;
	}
	
	public LocalDate getTanggalSurat() {
		return tanggalSurat;
	}
	
	public void setTanggalSurat(LocalDate tanggalSurat) {
		this.tanggalSurat = tanggalSurat;
	}
	
	public String getPerihalSurat() {
		return perihalSurat;
	}
	
	public void setPerihalSurat(String perihalSurat) {
		this.perihalSurat = perihalSurat;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 173;
        hash = 171 * hash + Objects.hashCode(this.getId());
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
        
        final RekomendasiDPLHDTO other = (RekomendasiDPLHDTO) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "RekomendasiDPLHDTO{id="
				.concat(this.getId())
				.concat(", nama=")
				.concat(this.getNama())
				.concat(", noSurat=")
				.concat(this.getNoSurat())
				.concat("}");	  
	}
	
	public RekomendasiDPLH toRekomendasiDPLH() {
		return new RekomendasiDPLH(
				this.getId(), 
				this.getNama(),
				noSurat, 
				tanggalSurat, 
				perihalSurat
				);
	}
}
