package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Pemrakarsa implements Serializable {

	private static final long serialVersionUID = 1008634190691153214L;
	private final String id;
	private final BentukUsaha bentukUsaha;
	private final String nomorIndukBerusaha;
	private final String nama;
	private final String namaNotaris;	
	private final Alamat alamat;
	private final String telepone;
	private final String fax;
	private final String npwp;
	private final String email;
	private final PenanggungJawab penanggungJawab;
	private final Date tanggalNotaris;
	private final Date tanggalOSS;
	private final String idCreator;
	
	public PenanggungJawab getPenanggungJawab() {
		return penanggungJawab;
	}

	public Pemrakarsa(String id, BentukUsaha bentukUsaha, String nomorIndukBerusaha, String nama, String namaNotaris,
			Alamat alamat, String telepone, String fax, String npwp, String email, PenanggungJawab penanggungJawab,
			Date tanggalNotaris, Date tanggalOSS, String idCreator) {
		super();
		this.id = id;
		this.bentukUsaha = bentukUsaha;
		this.nomorIndukBerusaha = nomorIndukBerusaha;
		this.nama = nama;
		this.namaNotaris = namaNotaris;
		this.alamat = alamat;
		this.telepone = telepone;
		this.fax = fax;
		this.npwp = npwp;
		this.email = email;
		this.penanggungJawab = penanggungJawab;
		this.tanggalNotaris = tanggalNotaris;
		this.idCreator = idCreator;
		this.tanggalOSS = tanggalOSS;
	}

	public String getId() {
		return id;
	}

	public BentukUsaha getBentukUsaha() {
		return bentukUsaha;
	}

	public String getNama() {
		return nama;
	}

	public Alamat getAlamat() {
		return alamat;
	}

	public String getTelepone() {
		return telepone;
	}

	public String getFax() {
		return fax;
	}

	public String getNpwp() {
		return npwp;
	}

	public String getEmail() {
		return email;
	}

	public String getNamaNotaris() {
		return namaNotaris;
	}

	public Date getTanggalNotaris() {
		return tanggalNotaris;
	}

	public Date getTanggalOSS() {
		return tanggalOSS;
	}

	public String getNomorIndukBerusaha() {
		return nomorIndukBerusaha;
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
        
        return true;
	}
	
	@Override
	public String toString() {
		return "PenanggungJawab{" + "bentukUsaha=" + bentukUsaha.toString() + ", NIB=" + nomorIndukBerusaha  + ", nama=" + nama
				+ ", alamat=" + alamat.toString() + ", telepone=" + telepone + ", fax=" + fax
				+ ", npwp=" + npwp + ", email=" + email + "}";
	}

	
	public String getIdCreator() {
		return idCreator;
	}
	
	
}
