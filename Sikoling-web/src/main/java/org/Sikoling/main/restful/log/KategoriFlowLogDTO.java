package org.Sikoling.main.restful.log;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.log.KategoriFlowLog;

public class KategoriFlowLogDTO implements Serializable {

	private static final long serialVersionUID = -4571393415695731372L;
	private String id;
	private String nama;
	
	public KategoriFlowLogDTO() {
	}
	
	public KategoriFlowLogDTO(KategoriFlowLog t) {
		if(t != null) {
			this.id = t.getId();
			this.nama = t.getNama();
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 1113;
        hash = 171 * hash + Objects.hashCode(this.id);
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
        
        final KategoriFlowLogDTO other = (KategoriFlowLogDTO) obj;
        
        if (!this.id.equalsIgnoreCase(other.getId())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "KategoriFlowLogDTO{id="
				.concat(id)
				.concat(", nama=")
				.concat(nama)
				.concat("}");	  
	}

	public KategoriFlowLog toKategoriFlowLog() {
		return new KategoriFlowLog(id, nama);
	}
}
