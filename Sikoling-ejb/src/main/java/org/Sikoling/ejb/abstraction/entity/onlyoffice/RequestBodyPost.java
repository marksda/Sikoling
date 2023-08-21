package org.Sikoling.ejb.abstraction.entity.onlyoffice;

import java.io.Serializable;
import java.util.List;

public class RequestBodyPost implements Serializable {

	private static final long serialVersionUID = 7542730746904860845L;
	private final List<Action> actions;
	private final String changesurl;
	private final String filetype;
	private final int forcesavetype;
	private final History history;
	private final String key;
	private final int status;
	private final String url;
	private final String userdata;
	private final List<String> users;
	
	public RequestBodyPost(List<Action> actions, String changesurl, String filetype, int forcesavetype, History history,
			String key, int status, String url, String userdata, List<String> users) {
		this.actions = actions;
		this.changesurl = changesurl;
		this.filetype = filetype;
		this.forcesavetype = forcesavetype;
		this.history = history;
		this.key = key;
		this.status = status;
		this.url = url;
		this.userdata = userdata;
		this.users = users;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Action> getActions() {
		return actions;
	}

	public String getChangesurl() {
		return changesurl;
	}

	public String getFiletype() {
		return filetype;
	}

	public int getForcesavetype() {
		return forcesavetype;
	}

	public History getHistory() {
		return history;
	}

	public String getKey() {
		return key;
	}

	public int getStatus() {
		return status;
	}

	public String getUrl() {
		return url;
	}

	public String getUserdata() {
		return userdata;
	}

	public List<String> getUsers() {
		return users;
	}

}
