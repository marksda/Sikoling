package org.Sikoling.ejb.main.repository.person;

import java.io.Serializable;

import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminData;
import jakarta.persistence.*;


@Entity
@Table(name="master.tbl_person")
@NamedQueries({
@NamedQuery(name="PersonData.findAll", query="SELECT p FROM PersonData p"),
@NamedQuery(name="PersonData.findByNama", query="SELECT p FROM PersonData p WHERE p.nama LIKE :nama")
})
public class PersonData implements Serializable {
	
	private static final long serialVersionUID = 3940657094951097521L;

	@Id
	private String id;

	private String nama;
	
	@Embedded
	private AlamatPersonData alamat;

	@Column(name="scan_ktp")
	private String scanKtp;

	@JoinColumn(name = "sex", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne(optional = false)
	private JenisKelaminData sex;

	@Embedded
	private KontakPersonData kontak;
	
	@OneToOne(mappedBy = "personData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private AutorisasiData autorisasiData;

	public PersonData() {
	}

	public AutorisasiData getAutorisasiData() {
		return autorisasiData;
	}

	public void setAutorisasiData(AutorisasiData autorisasiData) {
		this.autorisasiData = autorisasiData;
		autorisasiData.setPersonData(this);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public KontakPersonData getKontak() {
		return this.kontak;
	}

	public void setKontak(KontakPersonData kontak) {
		this.kontak = kontak;
	}

	public AlamatPersonData getAlamat() {
		return alamat;
	}

	public void setAlamat(AlamatPersonData alamat) {
		this.alamat = alamat;
	}
	
}