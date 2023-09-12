package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import org.Sikoling.ejb.abstraction.entity.dokumen.DokumenGenerik;

public class DokumenGenerikDTO extends DokumenDTO implements Serializable {

	private static final long serialVersionUID = -1038627508667993985L;
	private String nomor;
	private LocalDate tanggal;
	
	public DokumenGenerikDTO() {
	}
	
	public DokumenGenerikDTO(DokumenGenerik t) {
		if(t != null) {
			this.setId(t.getId());
			this.setNama(t.getNama());
			KategoriDokumenDTO kategoriDokumenDTO = t.getKategoriDokumen() != null ?
					new KategoriDokumenDTO(t.getKategoriDokumen()) : null;
			this.setKategoriDokumen(kategoriDokumenDTO);
			this.nomor = t.getNomor();
			this.tanggal = t.getTanggal();
		}
	}

	
	public String getNomor() {
		return nomor;
	}

	
	public void setNomor(String nomor) {
		this.nomor = nomor;
	}

	
	public LocalDate getTanggal() {
		return tanggal;
	}

	
	public void setTanggal(LocalDate tanggal) {
		this.tanggal = tanggal;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 1053;
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
        
        final DokumenGenerikDTO other = (DokumenGenerikDTO) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "DokumenGenerikDTO{id="
				.concat(this.getId())
				.concat(", nama=")
				.concat(this.getNama())
				.concat(", nomor=")
				.concat(this.getNomor())
				.concat("}");	  
	}

	public DokumenGenerik toDokumenGenerik() {
		return new DokumenGenerik(
				this.getId(), 
				this.getNama(), 
				this.getKategoriDokumen() != null ? 
						this.getKategoriDokumen().toKategoriDokumen() : null, 
				this.nomor, 
				this.tanggal
				);
	}

	
}
