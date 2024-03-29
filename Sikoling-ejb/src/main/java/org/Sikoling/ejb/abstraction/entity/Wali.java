package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.permohonan.StatuswaliPermohonan;

public class Wali implements Serializable {

	private static final long serialVersionUID = 2775584601268906714L;
	private final User user;
	private final StatuswaliPermohonan status;
	
	public Wali(User user, StatuswaliPermohonan status) {
		super();
		this.user = user;
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public StatuswaliPermohonan getStatus() {
		return status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + Objects.hashCode(this.user.getPerson().getNik());
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
        
        if (!this.user.getPerson().getNik().equals(other.getUser().getPerson().getNik())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "Wali{id=" 
				.concat(this.user.getPerson().getNik())
				.concat(", nama=")
				.concat(this.user.getPerson().getNama())
				.concat(", statusWali=")
				.concat(this.status.getNama())
				.concat("}");
	}

}
