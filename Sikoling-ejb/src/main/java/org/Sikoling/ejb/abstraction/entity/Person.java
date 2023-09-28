package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {

	private static final long serialVersionUID = 2892571972544304592L;
	private final String nik;
	private final String nama;
	private final JenisKelamin sex;
	private final Alamat alamat;
	private final String scanKTP;
	private final Kontak kontak;
	private final Boolean statusVerified;
	
	public Person(String nik, String nama, JenisKelamin sex, Alamat alamat, String scanKTP,
			Kontak kontak, Boolean statusVerified) {
		super();
		this.nik = nik;
		this.nama = nama;
		this.sex = sex;
		this.alamat = alamat;
		this.scanKTP = scanKTP;
		this.kontak = kontak;
		this.statusVerified = statusVerified;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNik() {
		return nik;
	}

	public String getNama() {
		return nama;
	}

	public JenisKelamin getSex() {
		return sex;
	}

	public Alamat getAlamat() {
		return alamat;
	}

	public String getScanKTP() {
		return scanKTP;
	}

	public Kontak getKontak() {
		return kontak;
	}

	public Boolean getStatusVerified() {
		return statusVerified;
	}

	public int hashCode() {
		int hash = 83;
		hash = 103 * hash + Objects.hashCode(this.nik);
		hash = 103 * hash + Objects.hashCode(this.nama);
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
        
        final Person other = (Person) obj;
        
        if (!this.nik.equals(other.nik)) {
            return false;
        }
        
        if (!Objects.equals(this.nama, other.nama)) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "Person{" + "nik=" + nik + ", nama=" + nama + ", alamat=" + alamat.toString() + "}";
	}
                                       
}
