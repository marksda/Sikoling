package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class PenanggungJawab implements Serializable {

	private static final long serialVersionUID = -9008266978579909965L;
	private final String id;
	private final Person person;
	private final Jabatan jabatan;
	
	public PenanggungJawab(String id, Person person, Jabatan jabatan) {
		super();
		this.id = id;
		this.person = person;
		this.jabatan = jabatan;
	}
	
	public String getId() {
		return id;
	}

	public Person getPerson() {
		return person;
	}
	
	public Jabatan getJabatan() {
		return jabatan;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 79;
		hash = 117 * hash + Objects.hashCode(this.id);
		hash = 117 * hash + Objects.hashCode(this.person.toString());
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
        
        final PenanggungJawab other = (PenanggungJawab) obj;
        
        if (!this.id.equalsIgnoreCase(id)) {
            return false;
        }
        if (!Objects.equals(this.person, other.person)) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "PenanggungJawab{" + "nik=" + person.getNik() + ", nama=" + person.getNama() + ", jabatan=" 
				+ jabatan.getNama() + ", noHandphone=" + person.getKontak().getTelepone() + "}";
	}
                                                                                                                                   				
}
