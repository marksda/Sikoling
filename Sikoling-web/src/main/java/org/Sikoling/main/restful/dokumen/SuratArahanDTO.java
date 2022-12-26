package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.SuratArahan;

public class SuratArahanDTO extends DokumenDTO implements Serializable {
	private static final long serialVersionUID = -2092369169865137960L;
	private String noSurat;
	private LocalDate tanggalSurat;
	private String perihalSurat;
	private String uraianKegiatan;
	
	public SuratArahanDTO() {
	}
	
	public SuratArahanDTO(SuratArahan t) {
		super(t != null ? new Dokumen(
				t.getId(), 
				t.getNama(), 
				null
				) : null
			);
		if(t != null) {
			this.noSurat = t.getNoSurat();
			this.perihalSurat = t.getPerihalSurat();
			this.tanggalSurat = t.getTanggalSurat();
			this.uraianKegiatan = t.getUraianKegiatan();
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

	public String getUraianKegiatan() {
		return uraianKegiatan;
	}

	public void setUraianKegiatan(String uraianKegiatan) {
		this.uraianKegiatan = uraianKegiatan;
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
        
        final SuratArahanDTO other = (SuratArahanDTO) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "SuratArahanDTO{id="
				.concat(this.getId())
				.concat(", nama=")
				.concat(this.getNama())
				.concat(", noSurat=")
				.concat(this.getNoSurat())
				.concat("}");	  
	}

	public SuratArahan toSuratArahan() {
		KategoriDokumenDTO kategoriDokumenDTO = this.getKategoriDokumen();
		return new SuratArahan(
				this.getId(), 
				this.getNama(), 
				kategoriDokumenDTO != null ? new KategoriDokumen(
						kategoriDokumenDTO.getId(), 
						kategoriDokumenDTO.getNama(), 
						kategoriDokumenDTO.getParent()
						) : null, 
				this.noSurat, 
				this.tanggalSurat, 
				this.perihalSurat, 
				this.uraianKegiatan
				);
	}
}
