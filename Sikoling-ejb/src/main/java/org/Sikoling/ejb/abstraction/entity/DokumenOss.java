package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


public class DokumenOss extends DetailDokumen implements Serializable {

	private static final long serialVersionUID = -2123829934784643942L;
	private final String nib;
	private final LocalDate tanggal;
	private final List<Kbli> daftarKbli;
	
	public DokumenOss(Dokumen dokumen, String lokasiFile, String nib, LocalDate tanggal, List<Kbli> daftarKbli) {
		super(dokumen, lokasiFile);
		this.nib = nib;
		this.tanggal = tanggal;
		this.daftarKbli = daftarKbli;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNib() {
		return nib;
	}

	public LocalDate getTanggal() {
		return tanggal;
	}

	public List<Kbli> getDaftarKbli() {
		return daftarKbli;
	}
	
	public int hashCode() {
		int hash = 17;
        hash = 113 * hash + Objects.hashCode(this.nib);
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
        
        if (!this.nib.equalsIgnoreCase(other.nib)) {
            return false;
        }        

        return true;
	}

	@Override
	public String toString() {
		return "OSS{" + "nib=" + nib + ", tanggal=" + tanggal.toString() + '}';	  
	}
	
}
