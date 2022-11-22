package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DetailDokumen;
import org.Sikoling.ejb.abstraction.entity.DokumenOss;
import org.Sikoling.ejb.abstraction.entity.Kbli;

public class DokumenOssDTO extends DetailDokumenDTO implements Serializable {

	private static final long serialVersionUID = -8619439553490560056L;	
	private String nib;
	private LocalDate tanggal;	//tanggal penerbitan dokumen
	private List<KbliDTO> daftarKbli;
	
	public DokumenOssDTO() {
	}
	
	public DokumenOssDTO(DetailDokumen detailDokumen, String nib, LocalDate tanggal, List<KbliDTO> daftarKbli) {
		super(detailDokumen);
		this.nib = nib;
		this.tanggal = tanggal;
		this.daftarKbli = daftarKbli;
	}

	public String getNib() {
		return nib;
	}

	public void setNib(String nib) {
		this.nib = nib;
	}

	public LocalDate getTanggal() {
		return tanggal;
	}

	public void setTanggal(LocalDate tanggal) {
		this.tanggal = tanggal;
	}

	public List<KbliDTO> getDaftarKbli() {
		return daftarKbli;
	}

	public void setDaftarKbli(List<KbliDTO> daftarKbli) {
		this.daftarKbli = daftarKbli;
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
				.concat(", tanggal = ")
				.concat(tanggal.toString())
				.concat("}");	  
	}

	public DokumenOss toDokumenOss() {
		return new DokumenOss(
				this.getDokumen().toDokumen(), 
				null, 
				nib, 
				tanggal, 
				daftarKbli.stream().map(t -> new Kbli(t.getKode(), t.getNama(), t.getKategori())).collect(Collectors.toList())
				);
	}

}
