package org.Sikoling.main.restful.propinsi;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Propinsi;

public class PropinsiDTO implements Serializable {

	private static final long serialVersionUID = 530670866344098801L;
	private String id;
	private String text;
	
	public PropinsiDTO() {
		
	}

	public PropinsiDTO(String id, String nama) {
		this.id = id;
		this.text = nama;
	}
	
	public PropinsiDTO(Propinsi propinsi) {
		this.id = propinsi.getId();
		this.text = propinsi.getNama();
	}

	public Propinsi toPropinsi() {
        return new Propinsi(this.id, this.text);
    }
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return text;
	}

	public void setNama(String nama) {
		this.text = nama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.text);
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
        if (this.text != other.text) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "PropinsiDTO{" + "id=" + this.id + ", nama=" + this.text + "}";	    
	}
	
}
