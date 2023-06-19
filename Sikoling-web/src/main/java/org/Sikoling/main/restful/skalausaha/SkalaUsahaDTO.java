package org.Sikoling.main.restful.skalausaha;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;

public class SkalaUsahaDTO implements Serializable {

	private static final long serialVersionUID = -5439870531904858781L;
	private String id;
	private String nama;
	private String singkatan;
	
	public SkalaUsahaDTO() {
	}
	
	public SkalaUsahaDTO(SkalaUsaha t) {
		if(t != null) {
			this.id = t.getId();
			this.nama = t.getNama();
			this.singkatan = t.getSingkatan();
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

	public String getSingkatan() {
		return singkatan;
	}

	public void setSingkatan(String singkatan) {
		this.singkatan = singkatan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public SkalaUsaha toSkalaUsaha() {
		return new SkalaUsaha(id, nama, singkatan);
	}
	
	public int hashCode() {
		int hash = 131;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.nama);
        hash = 17 * hash + Objects.hashCode(this.singkatan);
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
        
        final SkalaUsahaDTO other = (SkalaUsahaDTO) obj;
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
		return "SkalaUsahaDTO{" + "id=" + this.id + ", nama=" + this.nama + "}";	    
	}

}
