package org.Sikoling.main.restful.desa;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.main.restful.kecamatan.KecamatanDTO;

public class DesaDTO implements Serializable {

	private static final long serialVersionUID = 6969506465322300113L;
	private String id;
	private String nama;
	private KecamatanDTO kecamatan;
	
	public DesaDTO() {
	}
	
	public DesaDTO(Desa t) {
		if(t != null) {
			this.id = t.getId();
			this.nama = t.getNama();
			this.kecamatan = t.getKecamatan() != null ?
					new KecamatanDTO(t.getKecamatan()):null;
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
	
	public KecamatanDTO getKecamatan() {
		return kecamatan;
	}

	public void setKecamatan(KecamatanDTO kecamatan) {
		this.kecamatan = kecamatan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
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
		return new Desa(
				id, 
				nama,
				kecamatan != null ? kecamatan.toKecamatan():null
				);
	}

}
