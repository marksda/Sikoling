package org.Sikoling.ejb.main.repository.penanggungjawab;

import java.io.Serializable;
import jakarta.persistence.*;
import org.Sikoling.ejb.main.repository.jabatan.JabatanData;
import org.Sikoling.ejb.main.repository.person.PersonData;
import org.Sikoling.ejb.main.repository.perusahaan.RegisterPerusahaanData;


@Entity
@Table(name="master.tbl_penanggung_jawab")
public class PenanggungJawabData implements Serializable {
	
	private static final long serialVersionUID = 6630651928270252860L;

	@Id
	private String id;
	
	@JoinColumn(name = "person", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private PersonData person;
	
	@JoinColumn(name = "pemrakarsa", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private RegisterPerusahaanData pemrakarsa;
	
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

	public RegisterPerusahaanData getPemrakarsa() {
		return pemrakarsa;
	}

	public void setPemrakarsa(RegisterPerusahaanData pemrakarsa) {
		this.pemrakarsa = pemrakarsa;
	}
	
}