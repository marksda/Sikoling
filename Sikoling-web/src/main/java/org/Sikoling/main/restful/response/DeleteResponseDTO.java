package org.Sikoling.main.restful.response;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;

public class DeleteResponseDTO implements Serializable {

	private static final long serialVersionUID = 1452529585693415400L;
	private boolean succsess;
	private String id;
	
	public DeleteResponseDTO() {
	}
	
	public DeleteResponseDTO(DeleteResponse deleteResponse) {
		this.id = deleteResponse.getId();
		this.succsess = deleteResponse.isSuccsess();
	}

	public boolean isSuccsess() {
		return succsess;
	}

	public void setSuccsess(boolean succsess) {
		this.succsess = succsess;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 131;
        hash = 171 * hash + Objects.hashCode(this.id);
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
        
        final DeleteResponseDTO other = (DeleteResponseDTO) obj;
        
        if (!this.id.equalsIgnoreCase(other.getId())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "DeleteResponseDTO{id="
				.concat(id)
				.concat(", success=")
				.concat(Boolean.toString(succsess))
				.concat("}");	  
	}

	public DeleteResponse toDeleteResponse() {
		return new DeleteResponse(succsess, id);
	}
}
