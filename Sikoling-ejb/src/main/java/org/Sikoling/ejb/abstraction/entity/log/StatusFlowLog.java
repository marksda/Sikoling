package org.Sikoling.ejb.abstraction.entity.log;

import java.io.Serializable;
import java.util.Objects;

public class StatusFlowLog implements Serializable {

	private static final long serialVersionUID = 3050084604533142423L;
	private final String id;
	private final String nama;
	
	public StatusFlowLog(String id, String nama) {
		this.id = id;
		this.nama = nama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}
	
	@Override
	public int hashCode() {
		int hash = 71;
		hash = 171 * hash + Objects.hashCode(id);
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
        
        final StatusFlowLog other = (StatusFlowLog) obj;
        
        if (!this.getId().equals(other.getId())) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "StatusFlowLog {"
				.concat("id=")
				.concat(this.id)
				.concat(", ")
				.concat("nama=")
				.concat(this.nama)
				.concat("}");
	}	

}
