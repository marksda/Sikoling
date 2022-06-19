package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;

public class AktaPemrakarsa implements Serializable {

	private static final long serialVersionUID = -3186081818421591782L;
	private final String nomor;
	private final Date tanggal;
	private final String namaNotaris;
	
	public AktaPemrakarsa(String nomor, Date tanggal, String namaNotaris) {
		super();
		this.nomor = nomor;
		this.tanggal = tanggal;
		this.namaNotaris = namaNotaris;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNomor() {
		return nomor;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public String getNamaNotaris() {
		return namaNotaris;
	}

}
