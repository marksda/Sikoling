package org.Sikoling.main.restful.onlyoffice;

import java.io.Serializable;
import java.util.List;

public class RequestBodyPostDTO implements Serializable {

	private static final long serialVersionUID = 6684267265334152191L;
	private List<ActionDTO> actions;
	private String changesurl;
	private String filetype;
	private int forcesavetype;
	private HistoryDTO history;
	private String key;
	private int status;
	private String url;
	private String userdata;
	private List<String> users;
	
	public RequestBodyPostDTO() { }

	public List<ActionDTO> getActions() {
		return actions;
	}

	public void setActions(List<ActionDTO> actions) {
		this.actions = actions;
	}

	public String getChangesurl() {
		return changesurl;
	}

	public void setChangesurl(String changesurl) {
		this.changesurl = changesurl;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public int getForcesavetype() {
		return forcesavetype;
	}

	public void setForcesavetype(int forcesavetype) {
		this.forcesavetype = forcesavetype;
	}

	public HistoryDTO getHistory() {
		return history;
	}

	public void setHistory(HistoryDTO history) {
		this.history = history;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserdata() {
		return userdata;
	}

	public void setUserdata(String userdata) {
		this.userdata = userdata;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
