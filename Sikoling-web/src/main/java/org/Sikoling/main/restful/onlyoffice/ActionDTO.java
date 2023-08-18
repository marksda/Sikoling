package org.Sikoling.main.restful.onlyoffice;

import java.io.Serializable;

public class ActionDTO implements Serializable {

	private static final long serialVersionUID = -6799789440511577308L;
	private int type;
	private String userid;
	
	public ActionDTO() { }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
