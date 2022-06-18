package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
** Sistem Online Single Submission (OSS)
*/
public class OSS implements Serializable {

	private static final long serialVersionUID = -2123829934784643942L;
	private final String nib;
	private final Date tanggal;
	private final List<KBLI> kblis;	

	public OSS(String nib, Date tanggal, List<KBLI> kblis) {
		super();
		this.nib = nib;
		this.tanggal = tanggal;
		this.kblis = kblis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNib() {
		return nib;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public List<KBLI> getKblis() {
		return kblis;
	}
	
	

}
