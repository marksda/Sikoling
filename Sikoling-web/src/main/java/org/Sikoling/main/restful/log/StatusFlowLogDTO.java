package org.Sikoling.main.restful.log;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.log.StatusFlowLog;

public class StatusFlowLogDTO implements Serializable {

	private static final long serialVersionUID = 1266542921398989480L;
	private String id;
	private String nama;
	
	public StatusFlowLogDTO() {
	}
	
	public StatusFlowLogDTO(StatusFlowLog t) {
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
		int hash = 1737;
		hash = 1211 * hash + Objects.hashCode(id);
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
        
        final StatusFlowLogDTO other = (StatusFlowLogDTO) obj;
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "StatusFlowLogDTO {"
				.concat("id=")
				.concat(id)
				.concat("}");
	}	

	public StatusFlowLog toStatusFlowLog() {
		return new StatusFlowLog(id, nama);
	}
}
