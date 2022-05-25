package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class PenanggungJawab implements Serializable {

	private static final long serialVersionUID = -9008266978579909965L;
	private String id;
	private String nama;
	private Alamat alamat;
	private String jabatan;
	private String jenisKelamin;
	private String noIdentitas;
	private String noHandphone;
	
	public PenanggungJawab(String id, String nama, Alamat alamat, String jabatan, String jenisKelamin, String noIdentitas,
			String noHandphone) {
		super();
		this.id = id;
		this.nama = nama;
		this.alamat = alamat;
		this.jabatan = jabatan;
		this.jenisKelamin = jenisKelamin;
		this.noIdentitas = noIdentitas;
		this.noHandphone = noHandphone;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}
	
	public void setNama(String nama) {
		this.nama = nama;
	}
	
	public Alamat getAlamat() {
		return alamat;
	}
	
	public void setAlamat(Alamat alamat) {
		this.alamat = alamat;
	}
	
	public String getJabatan() {
		return jabatan;
	}
	
	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}
	
	public String getJenisKelamin() {
		return jenisKelamin;
	}
	
	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}
	
	public String getNoIdentitas() {
		return noIdentitas;
	}
	
	public void setNoIdentitas(String noIdentitas) {
		this.noIdentitas = noIdentitas;
	}
	
	public String getNoHandphone() {
		return noHandphone;
	}
	
	public void setNoHandphone(String noHandphone) {
		this.noHandphone = noHandphone;
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
	
	

}
