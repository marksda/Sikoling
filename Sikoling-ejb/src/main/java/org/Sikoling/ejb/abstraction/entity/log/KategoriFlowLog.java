package org.Sikoling.ejb.abstraction.entity.log;

import java.io.Serializable;
import java.util.Objects;

public class KategoriFlowLog implements Serializable {

	private static final long serialVersionUID = 4476319225804032279L;
	private final String id;
	private final String nama;
	
	public KategoriFlowLog(String id, String nama) {
		super();
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
        
        final KategoriFlowLog other = (KategoriFlowLog) obj;
        
        if (!this.getId().equals(other.getId())) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "KategoriFlowLog {"
				.concat("id=")
				.concat(this.id)
				.concat(", ")
				.concat("nama=")
				.concat(this.nama)
				.concat("}");
	}	

}
