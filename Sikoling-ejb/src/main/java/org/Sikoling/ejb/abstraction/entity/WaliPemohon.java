package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;

public class WaliPemohon implements Serializable {

	private static final long serialVersionUID = 2775584601268906714L;
	private User wali;
	private StatusWali status;
	
	public WaliPemohon(User wali, StatusWali status) {
		super();
		this.wali = wali;
		this.status = status;
	}	

}
