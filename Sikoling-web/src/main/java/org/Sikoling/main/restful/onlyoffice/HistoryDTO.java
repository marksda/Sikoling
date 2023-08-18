package org.Sikoling.main.restful.onlyoffice;

import java.io.Serializable;

public class HistoryDTO implements Serializable {

	private static final long serialVersionUID = -7953878227523180963L;
	private Object changes;
	private Object serverVersion;
	
	public HistoryDTO() { }

	public Object getChanges() {
		return changes;
	}

	public void setChanges(Object changes) {
		this.changes = changes;
	}

	public Object getServerVersion() {
		return serverVersion;
	}

	public void setServerVersion(Object serverVersion) {
		this.serverVersion = serverVersion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
