package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.util.Objects;
import org.Sikoling.ejb.abstraction.entity.DetailDokumen;

public class DetailDokumenDTO implements Serializable {

	private static final long serialVersionUID = 1491203657821713604L;
	private DokumenDTO dokumen;
	private String lokasiFile;
	
	public DetailDokumenDTO() {
	}
	
	public DetailDokumenDTO(DetailDokumen detailDokumen) {
		this.dokumen = new DokumenDTO(detailDokumen.getDokumen());
		this.lokasiFile = detailDokumen.getLokasiFile();
	}
	
	public DokumenDTO getDokumen() {
		return dokumen;
	}
	
	public void setDokumen(DokumenDTO dokumen) {
		this.dokumen = dokumen;
	}
	
	public String getLokasiFile() {
		return lokasiFile;
	}

	public void setLokasiFile(String lokasiFile) {
		this.lokasiFile = lokasiFile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int hashCode() {
		int hash = 731;
        hash = 171 * hash + Objects.hashCode(this.dokumen.getId());
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
        
        final DetailDokumenDTO other = (DetailDokumenDTO) obj;
        
        if (!this.dokumen.getId().equalsIgnoreCase(other.getDokumen().getId())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "DetailDokumenDTO{nama="
				.concat(dokumen.getNama())
				.concat(", lokasiFile = ")
				.concat(lokasiFile)
				.concat("}");	  
	}

	public DetailDokumen toDetailDokumen() {
		return new DetailDokumen(dokumen.toDokumen(), lokasiFile);
	}
	
}
