package org.Sikoling.main.restful.pelakuusaha;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;

public class PelakuUsahaDTO implements Serializable {

	private static final long serialVersionUID = -2960356457387629854L;
	private String id;
	private String nama;
	private String singkatan;
	private KategoriPelakuUsahaDTO kategoriPelakuUsaha;
	
	public PelakuUsahaDTO() {
	}
	
	public PelakuUsahaDTO(PelakuUsaha t) {
		this.id = t.getId();
		this.nama = t.getNama();
		this.singkatan = t.getSingkatan();
		this.kategoriPelakuUsaha = new KategoriPelakuUsahaDTO(t.getKategoriPelakuUsaha());
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
		
	public KategoriPelakuUsahaDTO getKategoriPelakuUsaha() {
		return kategoriPelakuUsaha;
	}

	public void setKategoriPelakuUsaha(KategoriPelakuUsahaDTO kategoriPelakuUsaha) {
		this.kategoriPelakuUsaha = kategoriPelakuUsaha;
	}

	public PelakuUsaha toDetailPelakuUsaha() {
		return new PelakuUsaha(id, nama, singkatan, kategoriPelakuUsaha.toKategoriPelakuUsaha());
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
        
        final PelakuUsahaDTO other = (PelakuUsahaDTO) obj;
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
		return "DetailPelakuUsahaDTO{" + "id=" + this.id + ", nama=" + this.nama + "}";	    
	}

	
}
