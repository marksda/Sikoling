package org.Sikoling.main.restful.propinsi;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Propinsi;

public class PropinsiDTO implements Serializable {

	private static final long serialVersionUID = 530670866344098801L;
	private String id;
	private String nama;
	
	public PropinsiDTO() {		
	}
	
	public PropinsiDTO(Propinsi propinsi) {
		if(propinsi != null) {
			this.id = propinsi.getId();
			this.nama = propinsi.getNama();
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
		int hash = 751;
        hash = 71 * hash + Objects.hashCode(id );
        hash = 71 * hash + Objects.hashCode(nama);
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
        
        final PropinsiDTO other = (PropinsiDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nama != other.nama) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		if(id == null) {
			return null;
		}			
			
		return "PropinsiDTO{" + "id=" + this.id + ", nama=" + this.nama + "}";	    
	}

	public Propinsi toPropinsi() {
        return new Propinsi(this.id, this.nama);
    }
}
