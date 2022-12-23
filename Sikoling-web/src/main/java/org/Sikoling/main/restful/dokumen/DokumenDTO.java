package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Dokumen;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class DokumenDTO implements Serializable {

	private static final long serialVersionUID = -2378592503272433605L;
	private String id;
	private String nama;
	private KategoriDokumenDTO kategoriDokumen;
	private JsonObject detailAttributeDokumen;
	
	public DokumenDTO() {
	}
	
	public DokumenDTO(Dokumen dokumen) {
		if(dokumen != null) {
			this.id = dokumen.getId();
			this.nama = dokumen.getNama();
			this.kategoriDokumen = dokumen.getKategoriDokumen() != null ? new KategoriDokumenDTO(dokumen.getKategoriDokumen()) : null;	
			this.detailAttributeDokumen = dokumen.getDetailAttributeDokumen() != null ? dokumen.getDetailAttributeDokumen() : Json.createObjectBuilder().build();
		}
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
			
	public KategoriDokumenDTO getKategoriDokumen() {
		return kategoriDokumen;
	}

	public void setKategoriDokumen(KategoriDokumenDTO kategoriDokumen) {
		this.kategoriDokumen = kategoriDokumen;
	}

	public JsonObject getDetailAttributeDokumen() {
		return detailAttributeDokumen;
	}

	public void setDetailAttributeDokumen(JsonObject detailAttributeDokumen) {
		this.detailAttributeDokumen = detailAttributeDokumen;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 73;
        hash = 171 * hash + Objects.hashCode(this.id);
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
		return new Dokumen(
				id, 
				nama, 
				kategoriDokumen != null ? kategoriDokumen.toKategoriDokumen() : null, 
				detailAttributeDokumen
				);
	}

}
