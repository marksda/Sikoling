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
@Table(name="transaksi.tbl_register_kbli_2020")
@NamedQueries({
	@NamedQuery(name="RegisterKbliData.findAll", query="SELECT d FROM RegisterKbliData d"),
	@NamedQuery(name="RegisterKbliData.findByNama", query="SELECT d FROM RegisterKbliData d WHERE d.kbliData.nama = :nama"),
	@NamedQuery(name="RegisterKbliData.findByKode", query="SELECT d FROM RegisterKbliData d WHERE d.kbliData.id LIKE :kode"),
	@NamedQuery(name="RegisterKbliData.findByNib", query = "SELECT d FROM RegisterKbliData d WHERE d.registerDokumenOssData.nib = :nib")
})
@IdClass(RegisterKbliDataId.class)
public class RegisterKbliData implements Serializable {

	private static final long serialVersionUID = -1341807963805050449L;
	
	@Id
	@JoinColumn(name = "register_dokumen_oss", referencedColumnName = "nib", insertable = true, updatable = false)
	@ManyToOne(optional = false)
	private RegisterDokumenOssData registerDokumenOssData;
		
	@Id
	@JoinColumn(name = "kode", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne(optional = false)
	private KbliData kbliData;
	
	public RegisterKbliData() {
	}
		
	public RegisterDokumenOssData getDokumenOssData() {
		return registerDokumenOssData;
	}

	public void setRegisterDokumenOssData(RegisterDokumenOssData registerDokumenOssData) {
		this.registerDokumenOssData = registerDokumenOssData;
	}
	
	public KbliData getKbliData() {
		return kbliData;
	}
	
	public void setKbliData(KbliData kbliData) {
		this.kbliData = kbliData;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 83;
		hash = 137 * hash + Objects.hashCode(this.registerDokumenOssData.getNib());
		hash = 137 * hash + Objects.hashCode(this.kbliData.getId());
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
        
        if ( !(this.registerDokumenOssData.getNib().equals(other.registerDokumenOssData.getNib()) && 
        		this.kbliData.getId().equals(other.kbliData.getId())) ) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "RegisterKbliData{" + "nib=" + registerDokumenOssData.getNib() + ", kode=" + kbliData.getId() + "}";
	}

}
