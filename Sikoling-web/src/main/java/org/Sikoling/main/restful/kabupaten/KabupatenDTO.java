package org.Sikoling.main.restful.kabupaten;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.main.restful.propinsi.PropinsiDTO;

public class KabupatenDTO implements Serializable {

	private static final long serialVersionUID = -4317489216038458871L;
	private String id;
	private String nama;
	private PropinsiDTO propinsi;
	
	public KabupatenDTO() {
	}

	public KabupatenDTO(String id, String nama, PropinsiDTO propinsi) {
		this.id = id;
		this.nama = nama;
		this.propinsi = propinsi;
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

	public PropinsiDTO getPropinsi() {
		return propinsi;
	}

	public void setPropinsi(PropinsiDTO propinsi) {
		this.propinsi = propinsi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.nama);
        hash = 13 * hash + Objects.hashCode(this.propinsi.getId());
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
        
        if (this.propinsi.getId() != other.propinsi.getId()) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "KabupatenDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}
	
	

}
