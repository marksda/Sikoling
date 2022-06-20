package org.Sikoling.main.restful.pemrakarsa;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.KBLI;

public class KBLIDTO implements Serializable {

	private static final long serialVersionUID = 5334713148360421780L;
	private String kode;
	private String nama;
	
	public KBLIDTO() {
		
	}
	
	public KBLIDTO(KBLI kbli) {
		this.kode = kbli.getKode();
		this.nama = kbli.getNama();
	}
	
	public KBLIDTO(String kode, String nama) {
		super();
		this.kode = kode;
		this.nama = nama;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 29;
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
        
        final KBLIDTO other = (KBLIDTO) obj;
        
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
		return "KBLIDTO{" + "kode=" + kode + ", nama=" + nama + '}';	  
	}

	public KBLI toKBLI() {
		return new KBLI(kode, nama);
	}
}
