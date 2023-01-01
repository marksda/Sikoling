package org.Sikoling.ejb.abstraction.entity.dokumen;

import java.io.Serializable;
import java.util.Objects;

public class RegisterKbli implements Serializable {

	private static final long serialVersionUID = 2998858268709853490L;
	private final String idNib;
	private final String idKbli;
	private final String nama;
	
	public RegisterKbli(String idNib, String idKbli, String nama) {
		super();
		this.idNib = idNib;
		this.idKbli = idKbli;
		this.nama = nama;
	}	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIdNib() {
		return idNib;
	}

	public String getIdKbli() {
		return idKbli;
	}

	public String getNama() {
		return nama;
	}

	public int hashCode() {
		int hash = 17;
		hash = 131 * hash + Objects.hashCode(idNib);
        hash = 131 * hash + Objects.hashCode(idKbli);
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
        
        if (!this.idNib.equalsIgnoreCase(other.getIdNib())) {
            return false;
        }
        
        if (!this.idKbli.equalsIgnoreCase(other.getIdKbli())) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "RegisterKbli{ idNib=" 
				.concat(idNib)
				.concat(", idKbli=")
				.concat(idKbli)
				.concat("}");	  
	}


}
