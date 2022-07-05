package org.Sikoling.main.restful.pemrakarsa;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;
import org.Sikoling.main.restful.jabatan.JabatanDTO;
import org.Sikoling.main.restful.person.PersonDTO;

public class PenanggungJawabDTO implements Serializable {
	private static final long serialVersionUID = 3847984353247321174L;
	private String id;
	private PersonDTO person;
	private JabatanDTO jabatan;
	
	public PenanggungJawabDTO() {		
	}
	
	public PenanggungJawabDTO(PenanggungJawab penanggungJawab) {
		this.id = penanggungJawab.getId();
		this.person = new PersonDTO(penanggungJawab.getPerson());
		this.jabatan = new JabatanDTO(penanggungJawab.getJabatan());
	}
	
	public PenanggungJawabDTO(String id, PersonDTO person, JabatanDTO jabatan) {
		super();
		this.id = id;
		this.person = person;
		this.jabatan = jabatan;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PersonDTO getPerson() {
		return person;
	}

	public void setPerson(PersonDTO person) {
		this.person = person;
	}

	public JabatanDTO getJabatan() {
		return jabatan;
	}

	public void setJabatan(JabatanDTO jabatan) {
		this.jabatan = jabatan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 23;
        hash = 113 * hash + Objects.hashCode(this.id);
        hash = 113 * hash + Objects.hashCode(this.person.toString());
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
        
        final PenanggungJawabDTO other = (PenanggungJawabDTO) obj;
        
        if (!this.id.equalsIgnoreCase(other.id)) {
            return false;
        }
        if (!Objects.equals(this.person, other.person)) {
            return false;
        }
        

        return true;
	}

	@Override
	public String toString() {
		return "PenanggungJawabDTO{" + "nik=" + person.getNik() + ", nama=" + person.getNama() + ", jabatan=" 
				+ jabatan.getNama() + ", noHandphone=" + person.getTelepone() + '}';	  
	}

	public PenanggungJawab toPenanggungJawab() {		
		return new PenanggungJawab(id, person.toPerson(), jabatan.toJabatan());
	}

}
