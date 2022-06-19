package org.Sikoling.ejb.main.repository.penanggungjawab;

import java.io.Serializable;
import jakarta.persistence.*;
import org.Sikoling.ejb.main.repository.jabatan.JabatanData;
import org.Sikoling.ejb.main.repository.pemrakarsa.PemrakarsaData;


@Entity
@Table(name="master.tbl_penanggung_jawab")
@NamedQueries({
@NamedQuery(name="PenanggungJawabData.findAll", query="SELECT p FROM PenanggungJawabData p"),
@NamedQuery(name="PenanggungJawabData.findByNama", query="SELECT p FROM PenanggungJawabData p WHERE p.nama ILIKE :nama"),
})
public class PenanggungJawabData implements Serializable {
	
	private static final long serialVersionUID = 6630651928270252860L;

	@Id
	private String id;
	
	@JoinColumn(name = "person", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private PersonData person;
	
	@JoinColumn(name = "pemrakarsa", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private PemrakarsaData pemrakarsa;
	
	@JoinColumn(name = "jabatan", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private JabatanData jabatan;
	
	public PenanggungJawabData() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JabatanData getJabatan() {
		return this.jabatan;
	}

	public void setJabatan(JabatanData jabatan) {
		this.jabatan = jabatan;
	}

	public PersonData getPerson() {
		return person;
	}

	public void setPerson(PersonData person) {
		this.person = person;
	}

	public PemrakarsaData getPemrakarsa() {
		return pemrakarsa;
	}

	public void setPemrakarsa(PemrakarsaData pemrakarsa) {
		this.pemrakarsa = pemrakarsa;
	}
	
}