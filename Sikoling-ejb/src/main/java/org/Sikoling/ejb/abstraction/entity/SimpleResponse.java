package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class SimpleResponse implements Serializable {

	private static final long serialVersionUID = 4926752084417228657L;
	private final String status;
	private final String pesan;
	
	public SimpleResponse(String status, String pesan) {
		super();
		this.status = status;
		this.pesan = pesan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStatus() {
		return status;
	}

	public String getPesan() {
		return pesan;
	}
	
	public int hashCode() {
		int hash = 197;
		hash = 13 * hash + Objects.hashCode(this.status);
		hash = 13 * hash + Objects.hashCode(this.pesan);
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
        
        final SimpleResponse other = (SimpleResponse) obj;
        
        if (!this.status.equals(other.status)) {
            return false;
        }
        
        if (!this.pesan.equals(other.pesan)) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "SimpleResponse{" + "status=" + status + " pesan=" + pesan + "}";
	}


}
