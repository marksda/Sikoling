package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class ItemAttributeDokumen implements Serializable {

	private static final long serialVersionUID = -3166732876247970646L;
	private final String nama;
	private final String nilai;
	
	
	public int hashCode() {
		int hash = 13;
		hash = 71 * hash + Objects.hashCode(this.id);
		hash = 71 * hash + Objects.hashCode(this.nama);
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
        
        final ItemAttributeDokumen other = (ItemAttributeDokumen) obj;
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        if (!this.nama.equals(other.getNama())) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "DetailDokumenPerusahaan{"
				.concat("id=")
				.concat(this.id)
				.concat(", ")
				.concat("nama=")
				.concat(this.nama)
				.concat("}");
	}

}
