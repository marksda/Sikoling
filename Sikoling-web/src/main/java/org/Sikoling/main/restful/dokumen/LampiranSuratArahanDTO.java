package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.LampiranSuratArahan;

public class LampiranSuratArahanDTO extends DokumenDTO implements Serializable {

	private static final long serialVersionUID = -6467139467208051665L;
	private String noSuratArahan;
	private LocalDate tanggalSuratArahan;
	
	public LampiranSuratArahanDTO() {
	}
	
	public LampiranSuratArahanDTO(LampiranSuratArahan t) {
		super(t != null ? new Dokumen(
				t.getId(), 
				t.getNama(), 
				null
				) : null
			);
		if(t != null) {
			this.noSuratArahan = t.getNoSuratArahan();
			this.tanggalSuratArahan = t.getTanggalSuratArahan();
		}
	}

	public String getNoSuratArahan() {
		return noSuratArahan;
	}

	public void setNoSuratArahan(String noSuratArahan) {
		this.noSuratArahan = noSuratArahan;
	}

	public LocalDate getTanggalSuratArahan() {
		return tanggalSuratArahan;
	}

	public void setTanggalSuratArahan(LocalDate tanggalSuratArahan) {
		this.tanggalSuratArahan = tanggalSuratArahan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 73;
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
        
        final LampiranSuratArahanDTO other = (LampiranSuratArahanDTO) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "LampiranSuratArahanDTO{id="
				.concat(this.getId())
				.concat(", nama=")
				.concat(this.getNama())
				.concat(", noSurat=")
				.concat(this.getNoSuratArahan())
				.concat("}");	  
	}

	public LampiranSuratArahan toLampiranSuratArahan() {
		KategoriDokumenDTO kategoriDokumenDTO = this.getKategoriDokumen();
		return new LampiranSuratArahan(
				this.getId(), 
				this.getNama(), 
				kategoriDokumenDTO != null ? new KategoriDokumen(
						kategoriDokumenDTO.getId(), 
						kategoriDokumenDTO.getNama(), 
						kategoriDokumenDTO.getParent()
						) : null, 
				this.noSuratArahan, 
				this.tanggalSuratArahan
				);
	}

}
