package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Dokumen;
import org.Sikoling.ejb.abstraction.entity.DokumenOss;

public class DokumenOssDTO extends DokumenDTO implements Serializable {

	private static final long serialVersionUID = -8619439553490560056L;	
	private String nib;
	private LocalDate tanggalPenerbitan;
	private List<KbliDTO> daftarKbliDTO;
	
	public DokumenOssDTO() {
	}
	
	public DokumenOssDTO(DokumenOss t) {
		super(new Dokumen(t.getId(), t.getNama(), t.getKategoriDokumen()));
		this.nib = t.getNib();
		this.tanggalPenerbitan = t.getTanggalPenerbitan();
		this.daftarKbliDTO = t.getDaftarKbli().stream().map(item -> new KbliDTO(item)).collect(Collectors.toList());
	}

	public String getNib() {
		return nib;
	}

	public void setNib(String nib) {
		this.nib = nib;
	}

	public LocalDate getTanggalPenerbitan() {
		return tanggalPenerbitan;
	}

	public void setTanggalPenerbitan(LocalDate tanggalPenerbitan) {
		this.tanggalPenerbitan = tanggalPenerbitan;
	}

	public List<KbliDTO> getDaftarKbliDTO() {
		return daftarKbliDTO;
	}

	public void setDaftarKbliDTO(List<KbliDTO> daftarKbliDTO) {
		this.daftarKbliDTO = daftarKbliDTO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 141;
        hash = 83 * hash + Objects.hashCode(this.nib);
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
        
        final DokumenOssDTO other = (DokumenOssDTO) obj;
        
        if (!this.nib.equalsIgnoreCase(other.getNib())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "DokumenOssDTO{nib="
				.concat(nib)
				.concat(", tanggal penerbitan = ")
				.concat(tanggalPenerbitan.toString())
				.concat("}");	  
	}

	public DokumenOss toDokumenOss() {
		Dokumen dokumen = new Dokumen(
				this.getId(), 
				this.getNama(), 
				this.getKategori().toKategoriDokumen()
				);
		
		return new DokumenOss(
				dokumen, 
				this.nib, 
				this.tanggalPenerbitan, 
				this.daftarKbliDTO.stream().map(t -> t.toKbli()).collect(Collectors.toList()));
	}

}
