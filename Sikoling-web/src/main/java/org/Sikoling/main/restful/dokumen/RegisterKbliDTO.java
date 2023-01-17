package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.dokumen.RegisterKbli;

public class RegisterKbliDTO implements Serializable {

	private static final long serialVersionUID = -4442022463611044537L;
	private String idNib;
	private String idKbli;
	private String nama;
	
	public RegisterKbliDTO() {
	}
	
	public RegisterKbliDTO(RegisterKbli t) {
		if(t!= null) {
			this.idNib = t.getIdNib();
			this.idKbli = t.getIdKbli();
			this.nama = t.getNama();
		}
	}	
	
	public String getIdNib() {
		return idNib;
	}

	public void setIdNib(String idNib) {
		this.idNib = idNib;
	}

	public String getIdKbli() {
		return idKbli;
	}

	public void setIdKbli(String idKbli) {
		this.idKbli = idKbli;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 137;
		hash = 171 * hash + Objects.hashCode(idNib);
        hash = 171 * hash + Objects.hashCode(idKbli);
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
        
        final RegisterKbliDTO other = (RegisterKbliDTO) obj;
        
        if ( !(this.idNib.equalsIgnoreCase(other.getIdNib()) 
        		&& this.idKbli.equalsIgnoreCase(other.getIdKbli())) ) {
            return false;
        }

        return true;
	}
	
	@Override
	public String toString() {
		return "RegisterKbliDTO{idNib="
				.concat(idNib)
				.concat(", idKbli = ")
				.concat(idKbli)
				.concat("}");	  
	}

	public RegisterKbli toRegisterKbli() {
		return new RegisterKbli(idNib, idKbli, nama);
	}	

}
