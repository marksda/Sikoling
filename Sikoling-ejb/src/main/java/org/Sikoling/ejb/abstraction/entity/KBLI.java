package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

/*
** Klasifikasi Baku Lapangan Usaha Indonesia(KBLI)
*/
public class KBLI implements Serializable {

	private static final long serialVersionUID = 336395546827376279L;
	private final String kode;
	private final String nama;
	
	public KBLI(String kode, String nama) {
		super();
		this.kode = kode;
		this.nama = nama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getKode() {
		return kode;
	}

	public String getNama() {
		return nama;
	}
		
	public int hashCode() {
		int hash = 3;
        hash = 101 * hash + Objects.hashCode(this.kode);
        hash = 101 * hash + Objects.hashCode(this.nama);
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
        
        final KBLI other = (KBLI) obj;
        
        if (!this.kode.equalsIgnoreCase(other.kode)) {
            return false;
        }

        if (!this.nama.equalsIgnoreCase(other.nama)) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "KBLI{" + "kode=" + kode + ", nama=" + nama + '}';	  
	}

}
