package org.Sikoling.main.restful.penanggungjawab;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;
import org.Sikoling.main.restful.jabatan.JabatanDTO;
import org.Sikoling.main.restful.person.PersonDTO;
import org.Sikoling.main.restful.perusahaan.RegisterPerusahaanDTO;

public class PenanggungJawabDTO implements Serializable {
	private static final long serialVersionUID = 3847984353247321174L;
	private String id;
	private PersonDTO person;
	private JabatanDTO jabatan;
	private RegisterPerusahaanDTO registerPerusahaan;
	
	public PenanggungJawabDTO() {		
	}
	
	public PenanggungJawabDTO(PenanggungJawab t) {
		if(t != null) {
			this.id = t.getId();
			this.person = t.getPerson() != null ? new PersonDTO(t.getPerson()):null;
			this.jabatan = t.getJabatan() != null ? new JabatanDTO(t.getJabatan()):null;
			this.registerPerusahaan = t.getRegisterPerusahaan() != null ? new RegisterPerusahaanDTO(t.getRegisterPerusahaan()):null;
		}		
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

	public RegisterPerusahaanDTO getRegisterPerusahaan() {
		return registerPerusahaan;
	}

	public void setRegisterPerusahaan(RegisterPerusahaanDTO registerPerusahaan) {
		this.registerPerusahaan = registerPerusahaan;
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
				+ jabatan.getNama() + ", noHandphone=" + person.getKontak().getTelepone() + '}';	  
	}

	public PenanggungJawab toPenanggungJawab() {		
		return new PenanggungJawab(
				id, 
				this.person != null ? this.person.toPerson():null, 
				this.jabatan != null ? this.jabatan.toJabatan():null, 
				this.registerPerusahaan != null ? this.registerPerusahaan.toRegisterPerusahaan():null
				);
	}

}
