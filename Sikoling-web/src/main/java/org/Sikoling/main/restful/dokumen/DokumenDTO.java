package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Dokumen;

public class DokumenDTO implements Serializable {

	private static final long serialVersionUID = -2378592503272433605L;
	private String id;
	private KategoriDokumenDTO kategori;
	private String nama;
	
	public DokumenDTO() {
	}
	
	public DokumenDTO(Dokumen dokumen) {
		this.id = dokumen.getId();
		this.nama = dokumen.getNama();
		this.kategori = new KategoriDokumenDTO(dokumen.getKategoriDokumen());		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNama() {
		return nama;
	}
	
	public void setNama(String nama) {
		this.nama = nama;
	}
	
	public KategoriDokumenDTO getKategori() {
		return kategori;
	}
	
	public void setKategori(KategoriDokumenDTO kategori) {
		this.kategori = kategori;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 73;
        hash = 171 * hash + Objects.hashCode(this.id);
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
        
        final DokumenDTO other = (DokumenDTO) obj;
        
        if (!this.id.equalsIgnoreCase(other.id)) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "KategoriDokumenDTO{id="
				.concat(id)
				.concat(", nama=")
				.concat(nama)
				.concat("}");	  
	}

	public Dokumen toDokumen() {
		return new Dokumen(id, nama, kategori.toKategoriDokumen());
	}

}
