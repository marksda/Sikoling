package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_nib_kbli")
@IdClass(RegisterKbliDataId.class)
public class RegisterKbliData implements Serializable {

	private static final long serialVersionUID = -1341807963805050449L;
	
	@Id
	private String nib;
		
	@Id
	@ManyToOne
	@JoinColumn(name = "kbli", referencedColumnName = "id", insertable = true, updatable = true)
	private KbliData kbli;
	
	public RegisterKbliData() {
	}

	public String getNib() {
		return nib;
	}
	
	public void setNib(String nib) {
		this.nib = nib;
	}
	
	public KbliData getKbli() {
		return kbli;
	}
	
	public void setKbli(KbliData kbli) {
		this.kbli = kbli;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 813;
		hash = 137 * hash + Objects.hashCode(this.nib);
		hash = 137 * hash + Objects.hashCode(this.kbli);
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
        
        final RegisterKbliData other = (RegisterKbliData) obj;
        
        if (!nib.equals(other.getNib()))  {
            return false;
        }
        
        if (!this.kbli.getId().equals(other.kbli.getId()))  {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "RegisterKbliData{nib="
				.concat(nib)
				.concat(", kbi=")
				.concat(kbli.getId())
				.concat("}");	  
	}

}
