package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.dokumen.DokumenNibOss;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli;

public class RegisterKbli implements Serializable {

	private static final long serialVersionUID = 2998858268709853490L;
	private final DokumenNibOss dokumenNibOss;
	private final Kbli kbli;
	
	public RegisterKbli(DokumenNibOss dokumenNibOss, Kbli kbli) {
		this.dokumenNibOss = dokumenNibOss;
		this.kbli = kbli;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public DokumenNibOss getDokumenNibOss() {
		return dokumenNibOss;
	}

	public Kbli getKbli() {
		return kbli;
	}

	public int hashCode() {
		int hash = 17;
		hash = 131 * hash + Objects.hashCode(dokumenNibOss.getId());
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
        
        if (!this.dokumenNibOss.getId().equalsIgnoreCase(other.getDokumenNibOss().getId())) {
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
				.concat(this.dokumenNibOss.getId())
				.concat(", idKbli=")
				.concat(this.kbli.getKode())
				.concat("}");	  
	}


}
