package org.Sikoling.main.restful.kategoripelakuusaha;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.main.restful.skalausaha.SkalaUsahaDTO;

public class KategoriPelakuUsahaDTO implements Serializable {

	private static final long serialVersionUID = 8462185491153635603L;
	private String id;
	private String nama;
	private SkalaUsahaDTO skalaUsaha;
	
 	public KategoriPelakuUsahaDTO() {
	}
	
	public KategoriPelakuUsahaDTO(KategoriPelakuUsaha t) {
		if(t != null) {
			this.id = t.getId();
			this.nama = t.getNama();
			this.skalaUsaha = t.getSkalaUsaha() != null ? new SkalaUsahaDTO(t.getSkalaUsaha()) : null;
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

	public SkalaUsahaDTO getSkalaUsaha() {
		return skalaUsaha;
	}

	public void setSkalaUsaha(SkalaUsahaDTO skalaUsaha) {
		this.skalaUsaha = skalaUsaha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
        
        final KategoriPelakuUsahaDTO other = (KategoriPelakuUsahaDTO) obj;
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

	public KategoriPelakuUsaha toKategoriPelakuUsaha() {
		return new KategoriPelakuUsaha(
				this.id, 
				this.nama,
				this.skalaUsaha != null ? this.skalaUsaha.toSkalaUsaha() : null				
				);
	}
	
}
