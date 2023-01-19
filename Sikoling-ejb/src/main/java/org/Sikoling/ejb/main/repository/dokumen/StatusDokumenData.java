package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_status_dokumen")
@NamedQueries({
	@NamedQuery(name="StatusDokumenData.findAll", query="SELECT d FROM StatusDokumenData d")
})
public class StatusDokumenData implements Serializable {

	private static final long serialVersionUID = 441278124075811119L;
	
	@Id
	private String id;
	
	private String nama;

	public StatusDokumenData() {
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
		int hash = 8013;
		hash = 137 * hash + Objects.hashCode(id);
		hash = 137 * hash + Objects.hashCode(nama);
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
        
        final StatusDokumenData other = (StatusDokumenData) obj;
        
        if (!id.equals(other.getId()))  {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "StatusDokumenData{id="
				.concat(id)
				.concat(", nama=")
				.concat(nama)
				.concat("}");	  
	}


}
