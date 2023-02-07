package org.Sikoling.main.restful.person;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.main.restful.alamat.AlamatDTO;
import org.Sikoling.main.restful.alamat.KontakDTO;
import org.Sikoling.main.restful.sex.JenisKelaminDTO;

public class PersonDTO implements Serializable {

	private static final long serialVersionUID = -7836863513864841849L;
	private String nik;
	private String nama;
	private JenisKelaminDTO jenisKelamin;
	private AlamatDTO alamat;
	private KontakDTO kontak;
	private String scanKTP;
	
	public PersonDTO() {		
	}
	
	public PersonDTO(Person person) {		
		if(person != null) {
			this.nik = person.getNik();
			this.nama = person.getNama();
			this.jenisKelamin = person.getSex() != null ? new JenisKelaminDTO(person.getSex()) : null;
			this.alamat = person.getAlamat() != null ? new AlamatDTO(person.getAlamat()) : null;
			this.kontak = person.getKontak() != null ? new KontakDTO(person.getKontak()) : null;
			this.scanKTP = person.getScanKTP();
		}
		else {
			this.nik = null;
			this.nama = null;
			this.jenisKelamin = null;
			this.alamat = null;
			this.kontak = null;
			this.scanKTP = null;
		}
	}
	
	public PersonDTO(String nik, String nama, JenisKelaminDTO sex, AlamatDTO alamat, KontakDTO kontak,
			String scanKTP) {
		this.nik = nik;
		this.nama = nama;
		this.jenisKelamin = sex;
		this.alamat = alamat;
		this.kontak = kontak;
		this.scanKTP = scanKTP;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public JenisKelaminDTO getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(JenisKelaminDTO sex) {
		this.jenisKelamin = sex;
	}

	public AlamatDTO getAlamat() {
		return alamat;
	}

	public void setAlamat(AlamatDTO alamat) {
		this.alamat = alamat;
	}

	public KontakDTO getKontak() {
		return kontak;
	}

	public void setKontak(KontakDTO kontak) {
		this.kontak = kontak;
	}

	public String getScanKTP() {
		return scanKTP;
	}

	public void setScanKTP(String scanKTP) {
		this.scanKTP = scanKTP;
	}	

	public int hashCode() {
		int hash = 29;
        hash = 217 * hash + Objects.hashCode(this.nik);
        hash = 19 * hash + Objects.hashCode(this.nama);
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
        
        final PersonDTO other = (PersonDTO) obj;
        
        if (this.nik != other.nik) {
            return false;
        }
        
        if (this.nama != other.nama) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "PersonDTO{" + "nik=" + nik + ", nama=" + nama + ", alamat=" + alamat.toString() + '}';	  
	}

	public Person toPerson() {
		if(nik != null) {
			return new Person(nik, nama, jenisKelamin.toJenisKelamin(), alamat.toAlamat(), scanKTP, kontak.toKontak());
		}
		else {
			return null;
		}
	}
}
