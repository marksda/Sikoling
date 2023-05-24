package org.Sikoling.main.restful.authority;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.HakAkses;

public class HakAksesDTO implements Serializable {

	private static final long serialVersionUID = 3581599802586704556L;
	private String id;
	private String nama;
	private String keterangan;
	
	public HakAksesDTO() {
	}
	
	public HakAksesDTO(HakAkses t) {
		if(t != null) {
			this.id = t.getId();
			this.nama = t.getNama();
			this.keterangan = t.getKeterangan();
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

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 87;
		hash = 171 * hash + Objects.hashCode(this.id);
		hash = 171 * hash + Objects.hashCode(this.nama);
		hash = 171 * hash + Objects.hashCode(this.keterangan);
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
        
        final HakAksesDTO other = (HakAksesDTO) obj;
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "HakAksesDTO {"
				.concat("id=")
				.concat(this.id)
				.concat(", nama=")
				.concat(this.nama)
				.concat(", keterangan=")
				.concat(this.keterangan)
				.concat("}");
	}
	
	public HakAkses toHakAkses() {
		return new HakAkses(id, nama, keterangan);
	}

}
