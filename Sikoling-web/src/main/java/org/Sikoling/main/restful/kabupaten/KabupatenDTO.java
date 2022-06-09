package org.Sikoling.main.restful.kabupaten;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Kabupaten;

public class KabupatenDTO implements Serializable {

	private static final long serialVersionUID = -4317489216038458871L;
	private String id;
	private String nama;
	private String idPropinsi;
	
	public KabupatenDTO() {
	}
	
	public KabupatenDTO(Kabupaten kabupaten) {
		this.id = kabupaten.getId();
		this.nama = kabupaten.getNama();
		this.idPropinsi = kabupaten.getIdPropinsi();
	}

	public KabupatenDTO(String id, String nama, String idPropinsi) {
		this.id = id;
		this.nama = nama;
		this.idPropinsi = idPropinsi;
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

	public String getIdPropinsi() {
		return idPropinsi;
	}

	public void setIdPropinsi(String idPropinsi) {
		this.idPropinsi = idPropinsi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.nama);
        hash = 13 * hash + Objects.hashCode(this.idPropinsi);
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
        
        final KabupatenDTO other = (KabupatenDTO) obj;
        
        if (this.id != other.id) {
            return false;
        }
        
        if (this.nama != other.nama) {
            return false;
        }
        
        if (this.idPropinsi != other.idPropinsi) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "KabupatenDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}
	
	public Kabupaten toKabupaten() {
		return new Kabupaten(id, nama, idPropinsi);
	}

}
