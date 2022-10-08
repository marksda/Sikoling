package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Perusahaan implements Serializable {

	private static final long serialVersionUID = 1008634190691153214L;
	private final String id;	
	private final String nama;
	private final JenisPelakuUsaha jenisPelakuUsaha;
	private final BentukUsaha bentukUsaha;
	private final Alamat alamat;
	private final Kontak kontak;
	private final PenanggungJawab penanggungJawab;
	private final List<Dokumen> daftarDokumen;
	
	public Perusahaan(String id, JenisPelakuUsaha jenisPelakuUsaha, BentukUsaha bentukUsaha, Alamat alamat, Kontak kontakPemrakarsa,
			String nama, String npwp, PenanggungJawab penanggungJawab, List<Dokumen> daftarDokumen
	) {
		super();
		this.id = id;
		this.jenisPelakuUsaha = jenisPelakuUsaha;
		this.bentukUsaha = bentukUsaha;
		this.alamat = alamat;
		this.kontak = kontakPemrakarsa;
		this.nama = nama;
		this.penanggungJawab = penanggungJawab;
		this.daftarDokumen = daftarDokumen;
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

	public PenanggungJawab getPenanggungJawab() {
		return penanggungJawab;
	}

	public Kontak getKontak() {
		return kontak;
	}
	
	public JenisPelakuUsaha getJenisPelakuUsaha() {
		return jenisPelakuUsaha;
	}

	public List<Dokumen> getDaftarDokumen() {
		return daftarDokumen;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 91;
		hash = 11 * hash + Objects.hashCode(this.id);
		hash = 11 * hash + Objects.hashCode(this.nama);
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
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        if (!this.nama.equals(other.nama)) {
            return false;
        }
        
        if (!Objects.equals(this.alamat, other.alamat)) {
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
