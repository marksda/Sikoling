package org.Sikoling.main.restful.pelakuusaha;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.JenisPelakuUsaha;

public class JenisPelakuUsahaDTO implements Serializable {

	private static final long serialVersionUID = 8462185491153635603L;
	private String id;
	private String nama;
	
	public JenisPelakuUsahaDTO() {
	}
	
	public JenisPelakuUsahaDTO(JenisPelakuUsaha t) {
		this.id = t.getId();
		this.nama = t.getNama();
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
	
	public JenisPelakuUsaha toJenisPelakuUsaha() {
		return new JenisPelakuUsaha(id, nama);
	}
	
	public int hashCode() {
		int hash = 131;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.nama);
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
        
        final JenisPelakuUsahaDTO other = (JenisPelakuUsahaDTO) obj;
        if (this.id.equals(other.getId())) {
            return false;
        }
        if (this.nama.equals(other.getNama())) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "JenisPelakuUsahaDTO{" + "id=" + this.id + ", nama=" + this.nama + "}";	    
	}

}
