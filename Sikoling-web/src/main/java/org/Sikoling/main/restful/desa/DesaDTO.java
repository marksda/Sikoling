package org.Sikoling.main.restful.desa;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;

public class DesaDTO implements Serializable {

	private static final long serialVersionUID = 6969506465322300113L;
	private String id;
	private String nama;
	private String idKecamatan;
	
	public DesaDTO() {
	}
	
	public DesaDTO(Desa desa) {
		this.id = desa.getId();
		this.nama = desa.getNama();
		this.idKecamatan = desa.getKecamatan().getId();
	}
	
	public DesaDTO(String id, String nama, String idKecamatan) {
		this.id = id;
		this.nama = nama;
		this.idKecamatan = idKecamatan;
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
	
	public String getIdKecamatan() {
		return idKecamatan;
	}
	
	public void setIdKecamatan(String idKecamatan) {
		this.idKecamatan = idKecamatan;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.nama);
        hash = 41 * hash + Objects.hashCode(this.idKecamatan);
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
        
        if (this.idKecamatan != other.idKecamatan) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "DesaDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}

	public Desa toDesa() {
		Kecamatan kecamatan = new Kecamatan(getIdKecamatan());
		return new Desa(id, nama, kecamatan);
	}

}
