package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.dokumen.DokumenAktaPendirian;
import org.Sikoling.main.restful.pegawai.PegawaiDTO;

public class AktaPendirianDTO extends DokumenDTO implements Serializable {

	private static final long serialVersionUID = 6255749816920528800L;
	private String nomor;
	private LocalDate tanggal;
	private String namaNotaris;
	private PegawaiDTO penanggungJawab;
	
	public AktaPendirianDTO() {		
	}
	
	public AktaPendirianDTO(DokumenAktaPendirian t) {		
		if(t != null) {		
			this.setId(t.getId());
			this.setNama(t.getNama());
			this.nomor = t.getNomor();
			this.tanggal = t.getTanggal();
			this.namaNotaris = t.getNamaNotaris();
			this.penanggungJawab = t.getPenanggungJawab() != null ? 
					new PegawaiDTO(t.getPenanggungJawab()) : null;
			this.setKategoriDokumen(
					t.getKategoriDokumen() != null ?
							new KategoriDokumenDTO(t.getKategoriDokumen()) : null
					);
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
	
	public String getNamaNotaris() {
		return namaNotaris;
	}
	
	public void setNamaNotaris(String namaNotaris) {
		this.namaNotaris = namaNotaris;
	}
	
	public PegawaiDTO getPenanggungJawab() {
		return penanggungJawab;
	}
	
	public void setPenanggungJawab(PegawaiDTO penanggungJawab) {
		this.penanggungJawab = penanggungJawab;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 191;
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
        
        final AktaPendirianDTO other = (AktaPendirianDTO) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "AktaPemrakarsaDTO{" + "nomor=" + nomor + ", namaNotaris=" + namaNotaris + ", tanggal=" + tanggal.toString() + "}";	  
	}

	public DokumenAktaPendirian toAktaPendirian() {
		return new DokumenAktaPendirian(
				this.getId(), 
				this.getNama(), 
				this.getKategoriDokumen() != null ? this.getKategoriDokumen().toKategoriDokumen():null, 
				nomor, 
				tanggal, 
				namaNotaris, 
				penanggungJawab != null ? penanggungJawab.toPegawai():null
				);
	}
}
