package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class RegisterKbli implements Serializable {

	private static final long serialVersionUID = 2998858268709853490L;
	private final String nib;
	private final String kode;
	private final String nama;
	
	public RegisterKbli(String nib, String kode, String nama) {
		this.nib = nib;
		this.kode = kode;
		this.nama = nama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNib() {
		return nib;
	}

	public String getKode() {
		return kode;
	}

	public String getNama() {
		return nama;
	}
	
	public int hashCode() {
		int hash = 17;
		hash = 131 * hash + Objects.hashCode(this.nib);
        hash = 131 * hash + Objects.hashCode(this.kode);
        hash = 131 * hash + Objects.hashCode(this.nama);
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
        
        final RegisterKbli other = (RegisterKbli) obj;
        
        if (!this.nib.equalsIgnoreCase(other.getNib())) {
            return false;
        }
        
        if (!this.kode.equalsIgnoreCase(other.getKode())) {
            return false;
        }

        if (!this.nama.equalsIgnoreCase(other.getNama())) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "RegisterKbli{ nib=" + nib + ", kode=" + kode + ", nama=" + nama + '}';	  
	}


}
