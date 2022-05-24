package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;

public class SuratPermohonan implements Serializable {

	private static final long serialVersionUID = 3360094098987919834L;
	private Date tanggal;
	private String nomor;
	
	public SuratPermohonan(Date tanggal, String nomor) {
		super();
		this.tanggal = tanggal;
		this.nomor = nomor;
	}

	
	public Date getTanggal() {
		return tanggal;
	}

	
	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	
	public String getNomor() {
		return nomor;
	}

	
	public void setNomor(String nomor) {
		this.nomor = nomor;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	

}
