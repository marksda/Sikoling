package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PenggunaEksternal extends User implements Serializable {

	private static final long serialVersionUID = -1157317116891452254L;
	
	private final String nama_lengkap;
	private final Alamat alamat;
	private final String nik;
	private final String no_telepon;
	
	public PenggunaEksternal(String id, String email, String password, Boolean loginStatus, Date registerDate, String nama_lengkap,
			Alamat alamat, String nik, String no_telepon, Boolean statusInternal) {
		super(id, email, password, loginStatus, registerDate, statusInternal);
		this.nama_lengkap = nama_lengkap;
		this.alamat = alamat;
		this.nik = nik;
		this.no_telepon = no_telepon;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNama_lengkap() {
		return nama_lengkap;
	}

	public Alamat getAlamat() {
		return alamat;
	}

	public String getNik() {
		return nik;
	}

	public String getNo_telepon() {
		return no_telepon;
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + Objects.hashCode(this.nama_lengkap);
		hash = 13 * hash + Objects.hashCode(this.nik);
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
        
        final PenggunaEksternal other = (PenggunaEksternal) obj;
        
        if (!this.nama_lengkap.equals(other.nama_lengkap) || !this.nik.equals(other.nik)) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "PenggunaEksternal{" + "nama_lengkap=" + nama_lengkap + ", nik=" + nik + "}";
	}
	
}
