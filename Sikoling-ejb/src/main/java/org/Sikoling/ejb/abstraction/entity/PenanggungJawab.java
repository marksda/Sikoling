package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class PenanggungJawab implements Serializable {

	private static final long serialVersionUID = -9008266978579909965L;
	private final String id;
	private final String nama;
	private final Alamat alamat;
	private final Jabatan jabatan;
	private final JenisKelamin jenisKelamin;
	private final String noIdentitas;
	private final String noHandphone;
	private final String idPemilik;
	
	public PenanggungJawab(String id, String nama, Alamat alamat, Jabatan jabatan, JenisKelamin jenisKelamin, 
			String noIdentitas,	String noHandphone, String idPemilik) {
		super();
		this.id = id;
		this.nama = nama;
		this.alamat = alamat;
		this.jabatan = jabatan;
		this.jenisKelamin = jenisKelamin;
		this.noIdentitas = noIdentitas;
		this.noHandphone = noHandphone;
		this.idPemilik = idPemilik;
	}
	
	public String getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}
	
	public Alamat getAlamat() {
		return alamat;
	}
	
	public Jabatan getJabatan() {
		return jabatan;
	}
	
	public JenisKelamin getJenisKelamin() {
		return jenisKelamin;
	}
	
	public String getNoIdentitas() {
		return noIdentitas;
	}
	
	public String getNoHandphone() {
		return noHandphone;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + Objects.hashCode(this.nama);
		hash = 13 * hash + Objects.hashCode(this.alamat);
		hash = 13 * hash + Objects.hashCode(this.jabatan);
		hash = 13 * hash + Objects.hashCode(this.jenisKelamin);
		hash = 13 * hash + Objects.hashCode(this.noIdentitas);
		hash = 13 * hash + Objects.hashCode(this.noHandphone);
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
        
        final PenanggungJawab other = (PenanggungJawab) obj;
        
        if (!this.nama.equals(other.nama)) {
            return false;
        }
        if (!Objects.equals(this.alamat, other.alamat)) {
            return false;
        }
        if (!this.jabatan.equals(other.jabatan)) {
            return false;
        }
        if (!this.jenisKelamin.equals(other.jenisKelamin)) {
            return false;
        }
        if (!this.noIdentitas.equals(other.noIdentitas)) {
            return false;
        }
        if (!this.noHandphone.equals(other.noHandphone)) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "PenanggungJawab{" + "nama=" + nama + ", alamat=" + alamat.toString() + ", jabatan=" 
				+ jabatan + ", sex=" + jenisKelamin + ", noIdentitas=" + noIdentitas + ", noHandphone=" + noHandphone + "}";
	}

	public String getIdPemilik() {
		return idPemilik;
	}
				
}
