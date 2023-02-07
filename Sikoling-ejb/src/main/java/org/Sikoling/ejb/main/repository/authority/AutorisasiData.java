package org.Sikoling.ejb.main.repository.authority;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.main.repository.hakakses.HakAksesData;
import org.Sikoling.ejb.main.repository.person.PersonData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_autorisasi")
@NamedQueries({
	@NamedQuery(name="AutorisasiData.findAll", query="SELECT u FROM AutorisasiData u"),
	@NamedQuery(name="AutorisasiData.findByNama", query="SELECT u FROM AutorisasiData u WHERE u.person.nama like :nama"),
	@NamedQuery(name="AutorisasiData.findByUserName", query="SELECT u FROM AutorisasiData u WHERE u.userName = :userName")	
})
public class AutorisasiData implements Serializable {

	private static final long serialVersionUID = 2467589981792742907L;
	
	@Id	
	private String id;
	
	@Column(name="user_name")
	private String userName;

    @JoinColumn(name = "person", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private PersonData person;
		
	@JoinColumn(name = "hak_akses", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private HakAksesData hakAkses;
	
	@Column(name="status_internal")
	private Boolean statusInternal;
	
	@Column(name="is_verified")
	private Boolean isVerified;
			
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public PersonData getPerson() {
		return person;
	}

	public void setPerson(PersonData personData) {
		this.person = personData;
	}

	public AutorisasiData() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HakAksesData getHakAkses() {
		return hakAkses;
	}

	public void setHakAkses(HakAksesData hakAkses) {
		this.hakAkses = hakAkses;
	}

	public Boolean getStatusInternal() {
		return statusInternal;
	}

	public void setStatusInternal(Boolean statusInternal) {
		this.statusInternal = statusInternal;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int hashCode() {
		int hash = 7103;
        hash = 1071 * hash + Objects.hashCode(id);
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
        
        final AutorisasiData other = (AutorisasiData) obj;
        
        if (!id.equals(other.getId())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "AutorisasiData{id="
				.concat(id)
				.concat("}");	  
	}
	 
}
