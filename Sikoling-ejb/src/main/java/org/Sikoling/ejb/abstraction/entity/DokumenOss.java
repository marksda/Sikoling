package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class DokumenOss extends Dokumen implements Serializable {

	private static final long serialVersionUID = -2123829934784643942L;
	private final String nib;
	private final Date tanggal;
	private final List<KBLI> daftarKbli;	


	public DokumenOss(String id, String nama, KategoriDokumen kategoriDokumen, String nib, Date tanggal,
			List<KBLI> daftarKbli) {
		super(id, nama, kategoriDokumen);
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

	public Date getTanggal() {
		return tanggal;
	}

	public List<KBLI> getDaftarKbli() {
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
