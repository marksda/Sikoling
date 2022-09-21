package org.Sikoling.main.restful.message;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.SimpleResponse;

public class SimpleResponseDTO implements Serializable {

	private static final long serialVersionUID = -8293789320684703999L;
	private String status;
	private String pesan;
	
	public SimpleResponseDTO() {
	}
	
	public SimpleResponseDTO(SimpleResponse simpleResponse) {
		this.status = simpleResponse.getStatus();
		this.pesan = simpleResponse.getPesan();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPesan() {
		return pesan;
	}

	public void setPesan(String pesan) {
		this.pesan = pesan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 191;
		hash = 7 * hash + Objects.hashCode(this.status);
		hash = 7 * hash + Objects.hashCode(this.pesan);
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
        
        final SimpleResponseDTO other = (SimpleResponseDTO) obj;
        
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
		return "SimpleResponseDTO{" + "status=" + status + " pesan=" + pesan + "}";
	}


}
