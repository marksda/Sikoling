package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Wali implements Serializable {

	private static final long serialVersionUID = 2775584601268906714L;
	private final User wali;
	private final StatusWali status;
	
	public Wali(User wali, StatusWali status) {
		super();
		this.wali = wali;
		this.status = status;
	}

	public User getWali() {
		return wali;
	}

	public StatusWali getStatus() {
		return status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + Objects.hashCode(this.wali.getEmail());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
		
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Wali other = (Wali) obj;
        
        if (!Objects.equals(this.wali, other.wali)) {
            return false;
        }
        
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "WaliPemohon{" + "wali=" + wali.toString() + ", status=" + status.toString() + "}";
	}

}
