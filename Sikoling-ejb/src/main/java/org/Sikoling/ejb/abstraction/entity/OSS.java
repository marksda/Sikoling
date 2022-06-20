package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/*
** Sistem Online Single Submission (OSS)
*/
public class OSS implements Serializable {

	private static final long serialVersionUID = -2123829934784643942L;
	private final String nib;
	private final Date tanggal;
	private final List<KBLI> kblis;	

	public OSS(String nib, Date tanggal, List<KBLI> kblis) {
		super();
		this.nib = nib;
		this.tanggal = tanggal;
		this.kblis = kblis;
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

	public List<KBLI> getKblis() {
		return kblis;
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
        
        final OSS other = (OSS) obj;
        
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
