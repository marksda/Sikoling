package org.Sikoling.ejb.abstraction.entity.onlyoffice;

import java.io.Serializable;

public class History implements Serializable {

	private static final long serialVersionUID = -7863939895617810799L;
	private final Object changes;
	private final Object serverVersion;
	
	public History(Object changes, Object serverVersion) {
		this.changes = changes;
		this.serverVersion = serverVersion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Object getChanges() {
		return changes;
	}

	public Object getServerVersion() {
		return serverVersion;
	}

}
