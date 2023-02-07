package org.Sikoling.main.restful.desa;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Desa;

public class DesaDTO implements Serializable {

	private static final long serialVersionUID = 6969506465322300113L;
	private String id;
	private String nama;
	
	public DesaDTO() {
	}
	
	public DesaDTO(Desa desa) {
		if(desa != null) {
			this.id = desa.getId();
			this.nama = desa.getNama();
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
	
	public int hashCode() {
		int hash = 19;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.nama);
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
        
        final DesaDTO other = (DesaDTO) obj;
        
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
		return "DesaDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}

	public Desa toDesa() {
		return new Desa(id, nama);
	}

}
