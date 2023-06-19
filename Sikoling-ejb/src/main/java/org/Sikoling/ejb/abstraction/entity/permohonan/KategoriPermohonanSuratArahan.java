package org.Sikoling.ejb.abstraction.entity.permohonan;

import java.io.Serializable;
import java.util.Objects;

public class KategoriPermohonanSuratArahan implements Serializable {

	private static final long serialVersionUID = -2686761379390064420L;
	private final String id;
	private final String keterangan;
	
	public KategoriPermohonanSuratArahan(String id, String keterangan) {
		this.id = id;
		this.keterangan = keterangan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public String getKeterangan() {
		return keterangan;
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
        
        final KategoriPermohonanSuratArahan other = (KategoriPermohonanSuratArahan) obj;
        
        if (!id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "JenisPermohonanSuratArahan{id=" 
				.concat(id)
				.concat("keterangan=")
				.concat(keterangan)
				.concat("}");
	}	

}
