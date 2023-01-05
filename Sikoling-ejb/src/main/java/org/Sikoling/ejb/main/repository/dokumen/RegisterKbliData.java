package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	@NamedQuery(name="RegisterKbliData.findByNama", query="SELECT d FROM RegisterKbliData d WHERE d.nama = :nama"),
	@NamedQuery(name="RegisterKbliData.findByKode", query="SELECT d FROM RegisterKbliData d WHERE d.kbli LIKE :kode"),
	@NamedQuery(name="RegisterKbliData.findByNib", query = "SELECT d FROM RegisterKbliData d WHERE d.nib = :nib")
})
@IdClass(RegisterKbliDataId.class)
public class RegisterKbliData implements Serializable {

	private static final long serialVersionUID = -1341807963805050449L;
	
	@Id
	private String nib;
		
	@Id
	private String kbli;
	
	private String nama;
	
	@JoinColumn(name = "nib", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private NibOssData nibOssData;
	
	public RegisterKbliData() {
	}	
	
	public String getNib() {
		return nib;
	}
	
	public void setNib(String nib) {
		this.nib = nib;
	}
	
	public String getKbli() {
		return kbli;
	}
	
	public void setKbli(String kbli) {
		this.kbli = kbli;
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
        
        if (!this.nib.equals(other.getNib()))  {
            return false;
        }
        
        if (!this.kbli.equals(other.getKbli()))  {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "RegisterKbliData{nib="
				.concat(this.nib)
				.concat(", kbi=")
				.concat(this.kbli)
				.concat(", nama=")
				.concat(nama)
				.concat("}");	  
	}

}
