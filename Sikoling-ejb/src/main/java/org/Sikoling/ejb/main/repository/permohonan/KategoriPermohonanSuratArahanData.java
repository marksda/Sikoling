package org.Sikoling.ejb.main.repository.permohonan;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_kategori_permohonan_surat_arahan")
@NamedQueries({
	@NamedQuery(name="KategoriPermohonanSuratArahanData.findAll", query="SELECT p FROM KategoriPermohonanSuratArahanData p")
})
public class KategoriPermohonanSuratArahanData implements Serializable {

	private static final long serialVersionUID = -7594677665752109615L;
	
	@Id
	private String id;
	
	private String keterangan;

	public KategoriPermohonanSuratArahanData() {
		super();
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
        
        final KategoriPermohonanSuratArahanData other = (KategoriPermohonanSuratArahanData) obj;
        
        if (!id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}


}
