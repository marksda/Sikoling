package org.Sikoling.main.restful.files;

import java.util.List;

public class OnlyOfficeBodyRequestDTO {
	private List<ActionOnlyOffice> actions;
	private List<Object> history;
	private String changeUrl;
	private String fileType;
	private int forcesavetype;
	private String key;
	private int status;
	private String url;
	private String userData;
	private List<String> users;
	private int error;
	
	public OnlyOfficeBodyRequestDTO() { }

	public List<ActionOnlyOffice> getActions() {
		return actions;
	}

	public void setActions(List<ActionOnlyOffice> actions) {
		this.actions = actions;
	}

	public List<Object> getHistory() {
		return history;
	}

	public void setHistory(List<Object> history) {
		this.history = history;
	}

	public String getChangeUrl() {
		return changeUrl;
	}

	public void setChangeUrl(String changeUrl) {
		this.changeUrl = changeUrl;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getForcesavetype() {
		return forcesavetype;
	}

	public void setForcesavetype(int forcesavetype) {
		this.forcesavetype = forcesavetype;
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

	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	} 
	
}
