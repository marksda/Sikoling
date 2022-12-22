package org.Sikoling.ejb.main.repository.authority;

import java.io.Serializable;

import org.Sikoling.ejb.main.repository.hakakses.HakAksesData;
import org.Sikoling.ejb.main.repository.person.PersonData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_autorisasi")
@NamedQueries({
	@NamedQuery(name="AutorisasiData.findAll", query="SELECT u FROM AutorisasiData u"),
	@NamedQuery(name="AutorisasiData.findByNama", query="SELECT u FROM AutorisasiData u WHERE u.personData.nama like :nama"),
	@NamedQuery(name="AutorisasiData.findByUserName", query="SELECT u FROM AutorisasiData u WHERE u.userName = :userName")	
})
public class AutorisasiData implements Serializable {

	private static final long serialVersionUID = 2467589981792742907L;
	
	@Id	
	private String id;
	
	@Column(name="user_name")
	private String userName;

    @JoinColumn(name = "person", referencedColumnName = "id")
	@OneToOne(optional = false)
	private PersonData personData;
		
	@JoinColumn(name = "hak_akses", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne(optional = false)
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

	public PersonData getPersonData() {
		return personData;
	}

	public void setPersonData(PersonData personData) {
		this.personData = personData;
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

}
