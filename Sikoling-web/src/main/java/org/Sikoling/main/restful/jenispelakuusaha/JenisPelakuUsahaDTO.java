package org.Sikoling.main.restful.jenispelakuusaha;

import java.io.Serializable;
import java.util.Objects;
import org.Sikoling.ejb.abstraction.entity.JenisPelakuUsaha;


public class JenisPelakuUsahaDTO implements Serializable {

	private static final long serialVersionUID = -854418089466652392L;
	private String id;
	private String nama;
	
	public JenisPelakuUsahaDTO() {		
	}
	
	public JenisPelakuUsahaDTO(JenisPelakuUsaha jenisPelakuUsaha) {
		this.id = jenisPelakuUsaha.getId();
		this.nama = jenisPelakuUsaha.getNama();
	}
	
	public JenisPelakuUsahaDTO(String id, String nama) {
		super();
		this.id = id;
		this.nama = nama;
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
		int hash = 41;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.nama);
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
		return "JenisPelakuUsahaDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}
	
	public JenisPelakuUsaha toJenisPelakuUsaha() {
		return new JenisPelakuUsaha(id, nama);
	}

}
