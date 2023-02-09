package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.main.repository.jabatan.JabatanData;
import org.Sikoling.ejb.main.repository.person.PersonData;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_pegawai_perusahaan")
@NamedQueries({
	@NamedQuery(name="PegawaiPerusahaanData.findAll", query="SELECT d FROM PegawaiPerusahaanData d"),
	@NamedQuery(name="PegawaiPerusahaanData.findByQueryNama", query="SELECT d FROM PegawaiPerusahaanData d WHERE d.personData.nama LIKE :nama"),
	@NamedQuery(name="PegawaiPerusahaanData.findByIdPerusahaan", query="SELECT d FROM PegawaiPerusahaanData d WHERE d.registerPerusahaanData.id = :idRegisterPerusahaan")
})
public class PegawaiPerusahaanData implements Serializable {

	private static final long serialVersionUID = -5313775851019211468L;
	
	@Id
	private String id;
	
	@JoinColumn(name = "person", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne(optional = false)
	private PersonData personData;
	
	@JoinColumn(name = "perusahaan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private RegisterPerusahaanData registerPerusahaanData;
	
	@JoinColumn(name = "jabatan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne(optional = false)
	private JabatanData jabatanData;

	public PegawaiPerusahaanData() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PersonData getPersonData() {
		return personData;
	}

	public void setPersonData(PersonData personData) {
		this.personData = personData;
	}

	public RegisterPerusahaanData getRegisterPerusahaanData() {
		return registerPerusahaanData;
	}

	public void setRegisterPerusahaanData(RegisterPerusahaanData registerPerusahaanData) {
		this.registerPerusahaanData = registerPerusahaanData;
	}

	public JabatanData getJabatanData() {
		return jabatanData;
	}

	public void setJabatanData(JabatanData jabatanData) {
		this.jabatanData = jabatanData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 71;
		hash = 1371 * hash + Objects.hashCode(this.id);
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
        
        final PegawaiPerusahaanData other = (PegawaiPerusahaanData) obj;
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}
	

}