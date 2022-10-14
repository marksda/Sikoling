package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Perusahaan implements Serializable {

	private static final long serialVersionUID = 1008634190691153214L;
	private final String id;	
	private final String nama;
	private final ModelPerizinan modelPerizinan;
	private final SkalaUsaha skalaUsaha;	
	private final KategoriPelakuUsaha jenisPelakuUsaha;
	private final PelakuUsaha detailPelakuUsaha;
	private final Alamat alamat;
	private final Kontak kontak;
	private final List<Dokumen> daftarDokumen;
		
	public Perusahaan(String id, String nama, ModelPerizinan modelPerizinan, SkalaUsaha skalaUsaha,
			KategoriPelakuUsaha jenisPelakuUsaha, PelakuUsaha detailPelakuUsaha, Alamat alamat, Kontak kontak,
			List<Dokumen> daftarDokumen) {
		super();
		this.id = id;
		this.nama = nama;
		this.modelPerizinan = modelPerizinan;
		this.skalaUsaha = skalaUsaha;
		this.jenisPelakuUsaha = jenisPelakuUsaha;
		this.detailPelakuUsaha = detailPelakuUsaha;
		this.alamat = alamat;
		this.kontak = kontak;
		this.daftarDokumen = daftarDokumen;
	}

	public ModelPerizinan getModelPerizinan() {
		return modelPerizinan;
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
	
	public Kontak getKontak() {
		return kontak;
	}
	
	public KategoriPelakuUsaha getJenisPelakuUsaha() {
		return jenisPelakuUsaha;
	}

	public List<Dokumen> getDaftarDokumen() {
		return daftarDokumen;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
	public SkalaUsaha getSkalaUsaha() {
		return skalaUsaha;
	}
	
	public PelakuUsaha getDetailPelakuUsaha() {
		return detailPelakuUsaha;
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
