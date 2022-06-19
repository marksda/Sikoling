package org.Sikoling.ejb.main.repository.penanggungjawab;

import java.io.Serializable;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminData;
import jakarta.persistence.*;


@Entity
@Table(name="master.tbl_person")
@NamedQuery(name="PersonData.findAll", query="SELECT p FROM PersonData p")
public class PersonData implements Serializable {
	
	private static final long serialVersionUID = 3940657094951097521L;

	@Id
	private String id;

	private String nama;
	
	@Embedded
	private AlamatPersonData alamat;

	@Column(name="scan_ktp")
	private String scanKtp;

	@JoinColumn(name = "sex", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private JenisKelaminData sex;

	private String telepone;

	public PersonData() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getScanKtp() {
		return this.scanKtp;
	}

	public void setScanKtp(String scanKtp) {
		this.scanKtp = scanKtp;
	}

	public JenisKelaminData getSex() {
		return this.sex;
	}

	public void setSex(JenisKelaminData sex) {
		this.sex = sex;
	}

	public String getTelepone() {
		return this.telepone;
	}

	public void setTelepone(String telepone) {
		this.telepone = telepone;
	}

	public AlamatPersonData getAlamat() {
		return alamat;
	}

	public void setAlamat(AlamatPersonData alamat) {
		this.alamat = alamat;
	}
	
}