package org.Sikoling.main.restful.kecamatan;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.main.restful.kabupaten.KabupatenDTO;

public class KecamatanDTO implements Serializable {

	private static final long serialVersionUID = 7449068860478766279L;
	private String id;
	private String nama;
	private KabupatenDTO kabupaten;
		
	public KecamatanDTO() {		
	}
	
	public KecamatanDTO(Kecamatan t) {
		if(t != null) {
			this.id = t.getId();
			this.nama = t.getNama();
			this.kabupaten = t.getKabupaten() != null ?
					new KabupatenDTO(t.getKabupaten()):null;
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

	public KabupatenDTO getKabupaten() {
		return kabupaten;
	}

	public void setKabupaten(KabupatenDTO kabupaten) {
		this.kabupaten = kabupaten;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.nama);
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

        return true;
	}

	@Override
	public String toString() {
		return "KecamatanDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}
	
	public Kecamatan toKecamatan() {
		return new Kecamatan(
				id, 
				nama,
				kabupaten != null ? kabupaten.toKabupaten():null
				);
	}

}
