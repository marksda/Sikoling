package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.NibOss;

public class NibOssDTO extends DokumenDTO implements Serializable {

	private static final long serialVersionUID = -1573998338773596635L;
	private String nomor;
	private LocalDate tanggal;
//	private Lis
	
	public NibOssDTO() {
	}
	
	public NibOssDTO(NibOss t) {
		super(t != null ? new Dokumen(
				t.getId(), 
				t.getNama(), 
				null
				) : null
			);
		if(t != null) {
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
		int hash = 1073;
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
        
        final NibOssDTO other = (NibOssDTO) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "NibOssDTO{id="
				.concat(this.getId())
				.concat(", nama=")
				.concat(this.getNama())
				.concat(", nomor=")
				.concat(this.getNomor())
				.concat("}");	  
	}

	public NibOss toNibOss() {
		KategoriDokumenDTO kategoriDokumenDTO = this.getKategoriDokumen();
		return new NibOss(
				this.getId(), 
				this.getNama(), 
				kategoriDokumenDTO != null ? new KategoriDokumen(
						kategoriDokumenDTO.getId(), 
						kategoriDokumenDTO.getNama(), 
						kategoriDokumenDTO.getParent()
						) : null, 
				this.nomor, 
				this.tanggal
				);
	}

}
