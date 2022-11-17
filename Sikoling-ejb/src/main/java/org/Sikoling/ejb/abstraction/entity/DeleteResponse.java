package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class DeleteResponse implements Serializable {
	
	private static final long serialVersionUID = -9180274295830781446L;
	private final boolean succsess;
	private final String id;
	
	public DeleteResponse(boolean succsess, String id) {
		this.succsess = succsess;
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isSuccsess() {
		return succsess;
	}

	public String getId() {
		return id;
	}
	

	public int hashCode() {
		int hash = 197;
		hash = 13 * hash + Objects.hashCode(this.id);
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
        
        final DeleteResponse other = (DeleteResponse) obj;
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "DeleteResponse{" + "id=" + id + ", success=" + Boolean.toString(succsess) + "}";
	}

}
