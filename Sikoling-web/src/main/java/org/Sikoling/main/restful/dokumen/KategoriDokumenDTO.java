package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.KategoriDokumen;

public class KategoriDokumenDTO implements Serializable {

	private static final long serialVersionUID = -5328310032173999224L;
	private String id;
	private String nama;
	private String parent;
	
	public KategoriDokumenDTO() {
	}
	
	public KategoriDokumenDTO(KategoriDokumen k) {
		this.id = k.getId();
		this.nama = k.getNama();
		this.parent = k.getParent();
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

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 31;
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
        
        final KategoriDokumenDTO other = (KategoriDokumenDTO) obj;
        
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

	public KategoriDokumen toKategoriDokumen() {
		return new KategoriDokumen(id, nama, parent);
	}


}
