package org.Sikoling.ejb.abstraction.entity.permohonan;

import java.io.Serializable;
import java.util.Objects;

public class StatusPermohonanPengelolaanLimbahB3 implements Serializable {

	private static final long serialVersionUID = -6920618009260820954L;
	private final String id;
	private final String nama;
	
	public StatusPermohonanPengelolaanLimbahB3(String id, String nama) {
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
		int hash = 1017;
		hash = 131 * hash + Objects.hashCode(id);
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
        
        final StatusPermohonanPengelolaanLimbahB3 other = (StatusPermohonanPengelolaanLimbahB3) obj;
        
        if (!id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "StatusPermohonanPengelolaanLimbahB3{id=" 
				.concat(id)
				.concat("nama=")
				.concat(nama)
				.concat("}");
	}	


}
