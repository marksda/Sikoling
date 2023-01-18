package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_nib_kbli")
@NamedQueries({
	@NamedQuery(name="RegisterKbliData.findAll", query="SELECT d FROM RegisterKbliData d"),
	@NamedQuery(name="RegisterKbliData.findByNama", query="SELECT d FROM RegisterKbliData d WHERE d.kbli.nama = :nama"),
	@NamedQuery(name="RegisterKbliData.findByKode", query="SELECT d FROM RegisterKbliData d WHERE d.kbli.id LIKE :kode"),
	@NamedQuery(name="RegisterKbliData.findByNib", query = "SELECT d FROM RegisterKbliData d WHERE d.nib.nomor = :nib")
})
@IdClass(RegisterKbliDataId.class)
public class RegisterKbliData implements Serializable {

	private static final long serialVersionUID = -1341807963805050449L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "nib", referencedColumnName = "nomor", insertable = true, updatable = true)
	private NibOssData nib;
		
	@Id
	@ManyToOne
	@JoinColumn(name = "kbli", referencedColumnName = "id", insertable = true, updatable = true)
	private Kbli2020Data kbli;
	
	public RegisterKbliData() {
	}

	public NibOssData getNib() {
		return nib;
	}

	
	public void setNib(NibOssData nib) {
		this.nib = nib;
	}

	
	public Kbli2020Data getKbli() {
		return kbli;
	}

	
	public void setKbli(Kbli2020Data kbli) {
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
        
        if (!nib.getNomor().equals(other.nib.getNomor()))  {
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
				.concat(nib.getNomor())
				.concat(", kbi=")
				.concat(kbli.getId())
				.concat("}");	  
	}

}
