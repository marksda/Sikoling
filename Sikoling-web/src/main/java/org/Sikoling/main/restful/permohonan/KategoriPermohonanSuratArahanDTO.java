package org.Sikoling.main.restful.permohonan;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonanSuratArahan;

public class KategoriPermohonanSuratArahanDTO implements Serializable {

	private static final long serialVersionUID = -493970788995457520L;
	private String id;
	private String keterangan;
	
	public KategoriPermohonanSuratArahanDTO() {
	}
	
	public KategoriPermohonanSuratArahanDTO(KategoriPermohonanSuratArahan t) {
		if(t != null) {
			this.id = t.getId();
			this.keterangan = t.getKeterangan();
		}		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
        
        final KategoriPermohonanSuratArahanDTO other = (KategoriPermohonanSuratArahanDTO) obj;
        
        if (!id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "JenisPermohonanSuratArahanDTO{id=" 
				.concat(id)
				.concat("keterangan=")
				.concat(keterangan)
				.concat("}");
	}	
	
	public KategoriPermohonanSuratArahan toKategoriPermohonanSuratArahan() {
		return new KategoriPermohonanSuratArahan(id, keterangan);
	}

}
