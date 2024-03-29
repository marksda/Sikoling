package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Perusahaan implements Serializable {

	private static final long serialVersionUID = 1008634190691153214L;
	private final String id;	
	private final String nama;
	private final ModelPerizinan modelPerizinan;
	private final SkalaUsaha skalaUsaha;	
	private final PelakuUsaha pelakuUsaha;
	private final Alamat alamat;
	private final Kontak kontak;
	
	public Perusahaan(String id, String nama, ModelPerizinan modelPerizinan, SkalaUsaha skalaUsaha,
			PelakuUsaha pelakuUsaha, Alamat alamat, Kontak kontak) {
		this.id = id;
		this.nama = nama;
		this.modelPerizinan = modelPerizinan;
		this.skalaUsaha = skalaUsaha;
		this.pelakuUsaha = pelakuUsaha;
		this.alamat = alamat;
		this.kontak = kontak;
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
	public SkalaUsaha getSkalaUsaha() {
		return skalaUsaha;
	}
	
	public PelakuUsaha getPelakuUsaha() {
		return pelakuUsaha;
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
		return "Perusahaan{ npwp="
				.concat(id)
				.concat(", nama=")
				.concat(pelakuUsaha.getSingkatan())
				.concat(". ")
				.concat(nama)
				.concat("}");
	}	
	
}
