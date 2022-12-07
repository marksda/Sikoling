package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.Serializable;

import org.Sikoling.ejb.main.repository.person.PersonData;

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
@Table(name="transaksi.tbl_person_perusahaan")
@NamedQueries({
	@NamedQuery(name="PersonPerusahaanData.findAll", query="SELECT d FROM PersonPerusahaanData d"),
	@NamedQuery(name="PersonPerusahaanData.findByPemilik", query = "SELECT d FROM PersonPerusahaanData d WHERE d.person.id = :personId")
})
@IdClass(PersonPerusahaanDataId.class)
public class PersonPerusahaanData implements Serializable {

	private static final long serialVersionUID = -5441108494516995827L;
	
	@Id
	@JoinColumn(name = "person", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private PersonData person;
	
	@Id
	@JoinColumn(name = "perusahaan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private PerusahaanData perusahaan;

	public PersonData getPerson() {
		return person;
	}

	public void setPerson(PersonData person) {
		this.person = person;
	}

	public PerusahaanData getPerusahaan() {
		return perusahaan;
	}

	public void setPerusahaan(PerusahaanData perusahaan) {
		this.perusahaan = perusahaan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
