package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;

public class BidangUsaha implements Serializable {

	private static final long serialVersionUID = -5728047210096243435L;
	private int id;
	private String keterangan;
	
	public BidangUsaha(int id, String keterangan) {
		super();
		this.id = id;
		this.keterangan = keterangan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
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
