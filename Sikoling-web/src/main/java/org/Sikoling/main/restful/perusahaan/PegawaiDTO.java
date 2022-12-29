package org.Sikoling.main.restful.perusahaan;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Pegawai;
import org.Sikoling.main.restful.jabatan.JabatanDTO;
import org.Sikoling.main.restful.person.PersonDTO;

public class PegawaiDTO implements Serializable {

	private static final long serialVersionUID = -7179063872744229313L;
	private String id;
	private PerusahaanDTO perusahaan;
	private PersonDTO person;
	private JabatanDTO jabatan;
	
	public PegawaiDTO() {
	}
	
	public PegawaiDTO(Pegawai t) {
		if(t != null) {
			this.id = t.getId();
			this.perusahaan = t.getPerusahaan() != null ? 
					new PerusahaanDTO(t.getPerusahaan()) : null;
			this.person = t.getPerson() != null ?
					new PersonDTO(t.getPerson()) : null;
			this.jabatan = t.getJabatan() != null ?
					new JabatanDTO(t.getJabatan()) : null;
		}
		
	}

	
	public PersonDTO getPerson() {
		return person;
	}

	public void setPerson(PersonDTO person) {
		this.person = person;
	}

	public String getId() {
		return id;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	
	public PerusahaanDTO getPerusahaan() {
		return perusahaan;
	}

	
	public void setPerusahaan(PerusahaanDTO perusahaan) {
		this.perusahaan = perusahaan;
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
	
	@Override
	public int hashCode() {
		int hash = 713;
        hash = 171 * hash + Objects.hashCode(this.getId());
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
        
        final PegawaiDTO other = (PegawaiDTO) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "PegawaiDTO{id="
				.concat(this.getId())
				.concat("}");	  
	}

	public Pegawai toPegawai() {
		return new Pegawai(
				id, 
				perusahaan != null ? perusahaan.toPerusahaan() : null, 
				person != null ? person.toPerson() : null,
				jabatan != null ? jabatan.toJabatan() : null
				);
	}
}
