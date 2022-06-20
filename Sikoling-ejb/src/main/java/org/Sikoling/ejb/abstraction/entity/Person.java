package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {

	private static final long serialVersionUID = 2892571972544304592L;
	private final String nik;
	private final String nama;
	private final JenisKelamin sex;
	private final Alamat alamat;
	private final String telepone;
	private final String scanKTP;
	
	public Person(String nik, String nama, JenisKelamin sex, Alamat alamat, String telepone, String scanKTP) {
		super();
		this.nik = nik;
		this.nama = nama;
		this.sex = sex;
		this.alamat = alamat;
		this.telepone = telepone;
		this.scanKTP = scanKTP;
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

	public String getTelepone() {
		return telepone;
	}

	public String getScanKTP() {
		return scanKTP;
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
