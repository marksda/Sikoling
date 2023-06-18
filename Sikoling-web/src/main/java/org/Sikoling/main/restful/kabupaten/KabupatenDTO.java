package org.Sikoling.main.restful.kabupaten;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.main.restful.propinsi.PropinsiDTO;

public class KabupatenDTO implements Serializable {

	private static final long serialVersionUID = -4317489216038458871L;
	private String id;
	private String nama;
	private PropinsiDTO propinsi;
	
	public KabupatenDTO() {
	}
	
	public KabupatenDTO(Kabupaten t) {
		if(t != null) {
			this.id = t.getId();
			this.nama = t.getNama();
			this.propinsi = t.getPropinsi() != null ? new PropinsiDTO(t.getPropinsi()):null;
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
		int hash = 29;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.nama);
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

        return true;
	}

	@Override
	public String toString() {
		return "KabupatenDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}
	
	public Kabupaten toKabupaten() {
		return new Kabupaten(
				id, 
				nama,
				propinsi != null ? propinsi.toPropinsi(): null
				);
	}

}
