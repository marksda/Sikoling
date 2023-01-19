package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.dokumen.StatusDokumen;

public class StatusDokumenDTO implements Serializable {

	private static final long serialVersionUID = 1765726356556909505L;
	private String id;
	private String nama;
	
	public StatusDokumenDTO() {
	}
	
	public StatusDokumenDTO(StatusDokumen t) {
		if(t != null) {
			this.id = t.getId();
			this.nama = t.getNama();
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 137;
		hash = 171 * hash + Objects.hashCode(id);
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
        
        final StatusDokumenDTO other = (StatusDokumenDTO) obj;
        
        if ( !id.equals(other.getId())) {
            return false;
        }

        return true;
	}
	
	@Override
	public String toString() {
		return "StatusDokumenDTO{id="
				.concat(id)
				.concat(", nama = ")
				.concat(nama)
				.concat("}");	  
	}
	
	public StatusDokumen toStatusDokumen() {
		return new StatusDokumen(id, nama);
	}

}
