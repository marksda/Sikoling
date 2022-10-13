package org.Sikoling.ejb.main.repository.authority;

import java.io.Serializable;

import org.Sikoling.ejb.main.repository.person.PersonData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_autorisasi")
@NamedQueries({
	@NamedQuery(name="AutorisasiData.findAll", query="SELECT u FROM AutorisasiData u"),
	@NamedQuery(name="AutorisasiData.findByQueryNik", query="SELECT u FROM AutorisasiData u WHERE u.nik = :nik")
})
public class AutorisasiData implements Serializable {

	private static final long serialVersionUID = 2467589981792742907L;
	
	@Id
	private String nik;
	
	@Column(name="id_lama")
	private String idLama;
	
	@Column(name="hak_akses")
	private String hakAkses;
	
	@Column(name="status_internal")
	private Boolean statusInternal;
	
	@Column(name="is_verified")
	private Boolean isVerified;
	
	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	@OneToOne
    @MapsId
    @JoinColumn(name = "nik")
	private PersonData personData;

	public PersonData getPersonData() {
		return personData;
	}

	public void setPersonData(PersonData personData) {
		this.personData = personData;
	}

	public AutorisasiData() {
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getIdLama() {
		return idLama;
	}

	public void setIdLama(String idLama) {
		this.idLama = idLama;
	}

	public String getHakAkses() {
		return hakAkses;
	}

	public void setHakAkses(String hakAkses) {
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
