package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.RekomendasiUKLUPL;

public class RekomendasiUKLUPLDTO extends DokumenDTO implements Serializable {

	private static final long serialVersionUID = 8266953008206667715L;
	private String noSurat;
	private LocalDate tanggalSurat;
	private String perihalSurat;
	
	public RekomendasiUKLUPLDTO() {
	}
	
	public RekomendasiUKLUPLDTO(RekomendasiUKLUPL t) {
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
		int hash = 117;
        hash = 121 * hash + Objects.hashCode(this.getId());
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
        
        final RekomendasiUKLUPLDTO other = (RekomendasiUKLUPLDTO) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "RekomendasiUKLUPLDTO{"
				.concat("id=")
				.concat(this.getId())
				.concat(", nomor=")
				.concat(this.noSurat)
				.concat(", tanggal=")
				.concat(tanggalSurat.toString())
				.concat("}");
	}

	public RekomendasiUKLUPL toRekomendasiUKLUPL() {
		return new RekomendasiUKLUPL(
				this.getId(), 
				this.getNama(),	
				this.noSurat, 
				this.tanggalSurat, 
				this.perihalSurat
				);
	}
	
}
