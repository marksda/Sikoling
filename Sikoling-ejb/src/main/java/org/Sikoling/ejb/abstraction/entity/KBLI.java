package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;

/*
** Klasifikasi Baku Lapangan Usaha Indonesia(KBLI)
*/
public class KBLI implements Serializable {

	private static final long serialVersionUID = 336395546827376279L;
	private final String kode;
	private final String nama;
	
	public KBLI(String kode, String nama) {
		super();
		this.kode = kode;
		this.nama = nama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getKode() {
		return kode;
	}

	public String getNama() {
		return nama;
	}
	
	

}
