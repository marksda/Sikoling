package org.Sikoling.ejb.abstraction.entity.permohonan;

import java.io.Serializable;
import java.util.Objects;

public class JenisPermohonanPengelolaanLimbahB3 implements Serializable {

	private static final long serialVersionUID = 7296903993920031524L;
	private final String id;
	private final String nama;
	
	public JenisPermohonanPengelolaanLimbahB3(String id, String nama) {
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
        
        final JenisPermohonanPengelolaanLimbahB3 other = (JenisPermohonanPengelolaanLimbahB3) obj;
        
        if (!id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "JenisPerohonanPengelolaanLimbahB3{id=" 
				.concat(id)
				.concat("nama=")
				.concat(nama)
				.concat("}");
	}	


}
