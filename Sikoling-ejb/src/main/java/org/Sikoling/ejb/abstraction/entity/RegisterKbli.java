package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli;

public class RegisterKbli implements Serializable {

	private static final long serialVersionUID = 2998858268709853490L;
	private final String nib;
	private final Kbli kbli;
	
	public RegisterKbli(String nib, Kbli kbli) {
		this.nib = nib;
		this.kbli = kbli;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNib() {
		return nib;
	}

	public Kbli getKbli() {
		return kbli;
	}

	public int hashCode() {
		int hash = 17;
		hash = 131 * hash + Objects.hashCode(nib);
        hash = 131 * hash + Objects.hashCode(kbli.getKode());
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
        
        final RegisterKbli other = (RegisterKbli) obj;
        
        if (!this.nib.equalsIgnoreCase(other.getNib())) {
            return false;
        }
        
        if (!this.kbli.getKode().equalsIgnoreCase(other.kbli.getKode())) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "RegisterKbli{ idNib=" 
				.concat(this.nib)
				.concat(", idKbli=")
				.concat(this.kbli.getKode())
				.concat("}");	  
	}


}
