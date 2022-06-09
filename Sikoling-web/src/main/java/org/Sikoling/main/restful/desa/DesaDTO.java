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
	
	public DesaDTO(Desa desa) {
		this.id = desa.getId();
		this.nama = desa.getNama();
		this.kecamatan = new KecamatanDTO(desa.getKecamatan());
	}
	
	public DesaDTO(String id, String nama, KecamatanDTO kecamatan) {
		this.id = id;
		this.nama = nama;
		this.kecamatan = kecamatan;
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
	
	public int hashCode() {
		int hash = 7;
        hash = 27 * hash + Objects.hashCode(this.id);
        hash = 27 * hash + Objects.hashCode(this.nama);
        hash = 27 * hash + Objects.hashCode(this.kecamatan.getId());
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
        
        if (this.kecamatan.getId() != other.kecamatan.getId()) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "DesaDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}

	public Desa toDesa() {
		return new Desa(id, nama, kecamatan.toKecamatan());
	}

}
