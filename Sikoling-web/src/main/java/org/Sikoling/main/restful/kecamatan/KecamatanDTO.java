package org.Sikoling.main.restful.kecamatan;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Kecamatan;

public class KecamatanDTO implements Serializable {

	private static final long serialVersionUID = 7449068860478766279L;
	private String id;
	private String nama;
	private String idKabupaten;
		
	public KecamatanDTO() {
		
	}
	
	public KecamatanDTO(String id) {
		this.id = id;
	}
	
	public KecamatanDTO(Kecamatan kecamatan) {
		this.id = kecamatan.getId();
		this.nama = kecamatan.getNama();
		this.idKabupaten = kecamatan.getIdKabupaten();
	}
	
	public KecamatanDTO(String id, String nama, String idKabupaten) {
		super();
		this.id = id;
		this.nama = nama;
		this.idKabupaten = idKabupaten;
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

	public String getIdKabupaten() {
		return idKabupaten;
	}

	public void setIdKabupaten(String idKabupaten) {
		this.idKabupaten = idKabupaten;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.nama);
        hash = 41 * hash + Objects.hashCode(this.idKabupaten);
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
        
        final KecamatanDTO other = (KecamatanDTO) obj;
        
        if (this.id != other.id) {
            return false;
        }
        
        if (this.nama != other.nama) {
            return false;
        }
        
        if (this.idKabupaten != other.idKabupaten) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "KecamatanDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}
	
	public Kecamatan toKecamatan() {
		return new Kecamatan(id, nama, idKabupaten);
	}

}
