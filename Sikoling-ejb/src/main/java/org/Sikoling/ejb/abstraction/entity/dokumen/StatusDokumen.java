package org.Sikoling.ejb.abstraction.entity.dokumen;

import java.io.Serializable;
import java.util.Objects;

public class StatusDokumen implements Serializable {

	private static final long serialVersionUID = -8063130045434625316L;
	private final String id;
	private final String nama;
	
	public StatusDokumen(String id, String nama) {
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
		int hash = 237;
        hash = 141 * hash + Objects.hashCode(id);
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
        
        final StatusDokumen other = (StatusDokumen) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "StatusDokumen{ id=" 
				.concat(this.getId())
				.concat(", nama=")
				.concat(nama)
				.concat("}");
	}


}
