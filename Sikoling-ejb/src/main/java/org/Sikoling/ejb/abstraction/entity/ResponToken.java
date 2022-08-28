package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class ResponToken implements Serializable {

	private static final long serialVersionUID = -5734455727073928190L;
	private final String status;
	private final Token token;
	
	public ResponToken(String status, Token token) {
		super();
		this.status = status;
		this.token = token;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStatus() {
		return status;
	}

	public Token getToken() {
		return token;
	}
	
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + Objects.hashCode(this.status);
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
        
        final ResponToken other = (ResponToken) obj;
        
        if (!this.status.equals(other.status)) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "ResponToken{" + "status=" + this.status + "}";
	}


}
