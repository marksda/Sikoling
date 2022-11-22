package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Kbli;

public class KbliDTO implements Serializable {
	
	private static final long serialVersionUID = 1180913705797914480L;
	private String kode;
	private String nama;
	private String kategori;
	
 	public KbliDTO() {
	}
	
	public KbliDTO(Kbli t) {
		this.kode = t.getKode();
		this.nama = t.getNama();
		this.kategori = t.getKategori();
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
	
	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 13;
        hash = 171 * hash + Objects.hashCode(this.kode);
        hash = 171 * hash + Objects.hashCode(this.nama);
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
        
        final KbliDTO other = (KbliDTO) obj;
        
        if (!this.kode.equalsIgnoreCase(other.getKode())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "KbliDTO{kode="
				.concat(kode)
				.concat(", nama = ")
				.concat(nama)
				.concat("}");	  
	}

	public Kbli toKbli() {
		return new Kbli(kode, nama, kategori);
	}
	
}
