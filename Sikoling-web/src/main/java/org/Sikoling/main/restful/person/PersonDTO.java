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
	private Boolean statusVerified;
	
	public PersonDTO() {		
	}
	
	public PersonDTO(Person t) {		
		if(t != null) {
			this.nik = t.getNik();
			this.nama = t.getNama();
			this.jenisKelamin = t.getSex() != null ? new JenisKelaminDTO(t.getSex()) : null;
			this.alamat = t.getAlamat() != null ? new AlamatDTO(t.getAlamat()) : null;
			this.kontak = t.getKontak() != null ? new KontakDTO(t.getKontak()) : null;
			this.scanKTP = t.getScanKTP();
			this.statusVerified = t.getStatusVerified() != null ? t.getStatusVerified() : null;
		}
		else {
			this.nik = null;
			this.nama = null;
			this.jenisKelamin = null;
			this.alamat = null;
			this.kontak = null;
			this.scanKTP = null;
			this.statusVerified = Boolean.FALSE;
		}
	}
	
//	public PersonDTO(String nik, String nama, JenisKelaminDTO sex, AlamatDTO alamat, KontakDTO kontak,
//			String scanKTP) {
//		this.nik = nik;
//		this.nama = nama;
//		this.jenisKelamin = sex;
//		this.alamat = alamat;
//		this.kontak = kontak;
//		this.scanKTP = scanKTP;
//	}

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

	public Boolean getStatusVerified() {
		return statusVerified;
	}

	public void setStatusVerified(Boolean statusVerified) {
		this.statusVerified = statusVerified;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
			return new Person(
					nik, 
					nama, 
					jenisKelamin != null ? jenisKelamin.toJenisKelamin():null, 
					alamat != null ? alamat.toAlamat():null, 
					scanKTP, 
					kontak != null ? kontak.toKontak():null,
					statusVerified != null ? statusVerified.booleanValue() : null	
					);
		}
		else {
			return null;
		}
	}
}
