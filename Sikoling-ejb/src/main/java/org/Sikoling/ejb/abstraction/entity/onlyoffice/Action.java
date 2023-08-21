package org.Sikoling.ejb.abstraction.entity.onlyoffice;

import java.io.Serializable;

public class Action implements Serializable {

	private static final long serialVersionUID = -6256804789607656143L;
	private final int type;
	private final String userid;
	
	public Action(int type, String userid) {
		this.type = type;
		this.userid = userid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getType() {
		return type;
	}

	public String getUserid() {
		return userid;
	}

}
