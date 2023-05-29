package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.permohonan.StatusPengurusPermohonan;

public class Wali implements Serializable {

	private static final long serialVersionUID = 2775584601268906714L;
	private final User user;
	private final StatusPengurusPermohonan status;
	
	public Wali(User user, StatusPengurusPermohonan status) {
		super();
		this.user = user;
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public StatusPengurusPermohonan getStatus() {
		return status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + Objects.hashCode(this.user.getId());
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
        
        if (!this.user.getId().equals(other.getUser().getId())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "Wali{id=" 
				.concat(this.user.getId())
				.concat(", nama=")
				.concat(this.user.getUserName())
				.concat(", statusWali=")
				.concat(this.status.getNama())
				.concat("}");
	}

}
