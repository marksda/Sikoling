package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class DokumenOss extends Dokumen implements Serializable {
	private static final long serialVersionUID = -7953417225751501778L;
	private final String nib;
	private final LocalDate tanggalPenerbitan;
	private final List<Kbli> daftarKbli;
	
	public DokumenOss(Dokumen dokumen, String nib, LocalDate tanggalPenerbitan, 
			List<Kbli> daftarKbli, String lokasiFile) {
		super(dokumen.getId(), dokumen.getNama(), dokumen.getKategoriDokumen());
		this.nib = nib;
		this.tanggalPenerbitan = tanggalPenerbitan;
		this.daftarKbli = daftarKbli;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNib() {
		return nib;
	}

	public LocalDate getTanggalPenerbitan() {
		return tanggalPenerbitan;
	}

	public List<Kbli> getDaftarKbli() {
		return daftarKbli;
	}
	
	@Override
	public int hashCode() {
		int hash = 83;
		hash = 137 * hash + Objects.hashCode(nib);
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
        
        final DokumenOss other = (DokumenOss) obj;
        
        if ( !this.nib.equals(other.getNib()) ) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "DokumenOss{" + "nib=" + nib + ", tanggal penerbitan=" + tanggalPenerbitan.toString() + "}";
	}

}
