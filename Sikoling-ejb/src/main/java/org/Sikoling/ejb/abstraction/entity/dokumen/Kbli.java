package org.Sikoling.ejb.abstraction.entity.dokumen;

import java.io.Serializable;
import java.util.Objects;

public class Kbli implements Serializable {

	private static final long serialVersionUID = 336395546827376279L;
	private final String kode;
	private final String nama;
	private final String kategori;
	
	public Kbli(String kode, String nama, String kategori) {
		super();
		this.kode = kode;
		this.nama = nama;
		this.kategori = kategori;
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
			
	public String getKategori() {
		return kategori;
	}

	public int hashCode() {
		int hash = 7;
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
        
        final Kbli other = (Kbli) obj;
        
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
		return "KBLI{" + "kode=" + kode + ", nama=" + nama + '}';	  
	}

}
