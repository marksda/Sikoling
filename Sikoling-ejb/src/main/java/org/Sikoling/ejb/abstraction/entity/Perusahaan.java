package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Perusahaan implements Serializable {

	private static final long serialVersionUID = 1008634190691153214L;
	private final String id;
	private final String npwp;	
	private final String nama;		
	private final BentukUsaha bentukUsaha;
	private final Alamat alamat;
	private final Kontak kontakPemrakarsa;
	private final PenanggungJawab penanggungJawab;
	
//	private final AktaPemrakarsa aktaPendirian;	
//	private final OSS oss;

	public Perusahaan(
		String id, BentukUsaha bentukUsaha, Alamat alamat, Kontak kontakPemrakarsa, 
		String nama, String npwp, PenanggungJawab penanggungJawab
	) {
		super();
		this.id = id;
		this.bentukUsaha = bentukUsaha;
//		this.aktaPendirian = aktaPendirian;
		this.alamat = alamat;
		this.kontakPemrakarsa = kontakPemrakarsa;
//		this.oss = oss;
		this.nama = nama;
		this.npwp = npwp;
		this.penanggungJawab = penanggungJawab;
	}

	public String getId() {
		return id;
	}

	public BentukUsaha getBentukUsaha() {
		return bentukUsaha;
	}
	
//	public AktaPemrakarsa getAktaPendirian() {
//		return aktaPendirian;
//	}

	public String getNama() {
		return nama;
	}

	public Alamat getAlamat() {
		return alamat;
	}

	public PenanggungJawab getPenanggungJawab() {
		return penanggungJawab;
	}
	
	public String getNpwp() {
		return npwp;
	}
	
//	public OSS getOss() {
//		return oss;
//	}

	public Kontak getKontakPemrakarsa() {
		return kontakPemrakarsa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 73 * hash + Objects.hashCode(this.bentukUsaha.toString());
		hash = 73 * hash + Objects.hashCode(this.nama);
		hash = 73 * hash + Objects.hashCode(this.npwp);
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
        
        final Perusahaan other = (Perusahaan) obj;
        
        if (!this.bentukUsaha.getNama().equals(other.bentukUsaha.getNama())) {
            return false;
        }
        
        if (!this.nama.equals(other.nama)) {
            return false;
        }
        
        if (!Objects.equals(this.alamat, other.alamat)) {
            return false;
        }
        
        if (!this.npwp.equals(other.npwp)) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
//		return "Pemrakarsa{" + "bentukUsaha=" + bentukUsaha.toString() + ", NIB=" + oss.getNib()  + ", nama=" + nama
//				+ ", alamat=" + alamat.toString() + ", npwp=" + npwp + "}";
		return null;
	}	
	
}
