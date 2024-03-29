package org.Sikoling.main.restful.permohonan;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.permohonan.StatuswaliPermohonan;

public class StatusPengurusPermohonanDTO implements Serializable {

	private static final long serialVersionUID = -7023312516491392089L;
	private String id;
	private String nama;
	
	public StatusPengurusPermohonanDTO() {
	}
	
	public StatusPengurusPermohonanDTO(StatuswaliPermohonan t) {
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
		int hash = 37;
		hash = 121 * hash + Objects.hashCode(id);
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
        
        final StatusPengurusPermohonanDTO other = (StatusPengurusPermohonanDTO) obj;
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "StatusPengurusPermohonanDTO {"
				.concat("id=")
				.concat(id)
				.concat(", nama=")
				.concat(nama)
				.concat("}");
	}

	public StatuswaliPermohonan toStatusPengurusPermohonan() {
		return new StatuswaliPermohonan(id, nama);
	}
}
