package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Pemrakarsa implements Serializable {

	private static final long serialVersionUID = 1008634190691153214L;
	private BentukUsaha bentukUsaha;
	private String nama;
	private Alamat alamat;
	private String telepone;
	private String fax;
	private String npwp;
	private String email;
	private PenanggungJawab penanggungJawab;
	
	public Pemrakarsa(BentukUsaha bentukUsaha, String nama, Alamat alamat, String telepone, String fax, String npwp,
			String email, PenanggungJawab penanggungJawab) {
		super();
		this.bentukUsaha = bentukUsaha;
		this.nama = nama;
		this.alamat = alamat;
		this.telepone = telepone;
		this.fax = fax;
		this.npwp = npwp;
		this.email = email;
		this.penanggungJawab = penanggungJawab;
	}

	public BentukUsaha getBentukUsaha() {
		return bentukUsaha;
	}

	public void setBentukUsaha(BentukUsaha bentukUsaha) {
		this.bentukUsaha = bentukUsaha;
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

	public String getTelepone() {
		return telepone;
	}

	public void setTelepone(String telepone) {
		this.telepone = telepone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getNpwp() {
		return npwp;
	}

	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PenanggungJawab getPenanggungJawab() {
		return penanggungJawab;
	}

	public void setPenanggungJawab(PenanggungJawab penanggungJawab) {
		this.penanggungJawab = penanggungJawab;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + Objects.hashCode(this.bentukUsaha.toString());
		hash = 13 * hash + Objects.hashCode(this.nama);
		hash = 13 * hash + Objects.hashCode(this.alamat.toString());
		hash = 13 * hash + Objects.hashCode(this.telepone);
		hash = 13 * hash + Objects.hashCode(this.fax);
		hash = 13 * hash + Objects.hashCode(this.npwp);
		hash = 13 * hash + Objects.hashCode(this.email);
		hash = 13 * hash + Objects.hashCode(this.penanggungJawab.toString());
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
        
        final Pemrakarsa other = (Pemrakarsa) obj;
        
        if (!this.bentukUsaha.getNama().equals(other.bentukUsaha.getNama())) {
            return false;
        }
        
        if (!this.nama.equals(other.nama)) {
            return false;
        }
        
        if (!Objects.equals(this.alamat, other.alamat)) {
            return false;
        }
        
        if (!this.telepone.equals(other.telepone)) {
            return false;
        }
        
        if (!this.fax.equals(other.fax)) {
            return false;
        }
        
        if (!this.npwp.equals(other.npwp)) {
            return false;
        }
        
        if (!this.email.equals(other.email)) {
            return false;
        }
        
        if (!Objects.equals(this.penanggungJawab, other.penanggungJawab)) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "PenanggungJawab{" + "bentukUsaha=" + bentukUsaha.toString() + ", nama=" + nama
				+ ", alamat=" + alamat.toString() + ", telepone=" + telepone + ", fax=" + fax
				+ ", npwp=" + npwp + ", email=" + email + ", penanggungJawab=" + penanggungJawab.toString() + "}";
	}
	
	
}
