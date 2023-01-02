package org.Sikoling.main.restful.permohonan;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.StatusTahapPemberkasan;

public class StatusTahapPemberkasanDTO implements Serializable {

	private static final long serialVersionUID = -3798209031452631389L;
	private String id;
	private String nama;
	private String keterangan;
	
	public StatusTahapPemberkasanDTO() {
	}
	
	public StatusTahapPemberkasanDTO(StatusTahapPemberkasan t) {
		if( t != null) {
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
		int hash = 317;
		hash = 1021 * hash + Objects.hashCode(id);
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
        
        final StatusTahapPemberkasanDTO other = (StatusTahapPemberkasanDTO) obj;
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "StatusTahapPemberkasanDTO {"
				.concat("id=")
				.concat(id)
				.concat(", nama=")
				.concat(nama)
				.concat("}");
	}
	
	public StatusTahapPemberkasan toStatusTahapPemberkasan() {
		return new StatusTahapPemberkasan(
				id, 
				nama, 
				keterangan
				);
	}

}
