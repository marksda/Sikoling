package org.Sikoling.main.restful.pegawai;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Pegawai;
import org.Sikoling.main.restful.jabatan.JabatanDTO;
import org.Sikoling.main.restful.person.PersonDTO;
import org.Sikoling.main.restful.perusahaan.RegisterPerusahaanDTO;

public class PegawaiDTO implements Serializable {

	private static final long serialVersionUID = 4408097239880314226L;
	private String id;
	private RegisterPerusahaanDTO registerPerusahaan;
	private PersonDTO person;
	private JabatanDTO jabatan;
	
	public PegawaiDTO() {
	}
	
	public PegawaiDTO(Pegawai t) {
		if(t != null) {
			this.id = t.getId();
			this.registerPerusahaan = t.getRegisterPerusahaan() != null ? new RegisterPerusahaanDTO(t.getRegisterPerusahaan()) : null;
			this.person = t.getPerson() != null ? new PersonDTO(t.getPerson()) : null;
			this.jabatan = t.getJabatan() != null ? new JabatanDTO(t.getJabatan()) : null;
		}
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public RegisterPerusahaanDTO getRegisterPerusahaan() {
		return registerPerusahaan;
	}
	
	public void setRegisterPerusahaan(RegisterPerusahaanDTO registerPerusahaan) {
		this.registerPerusahaan = registerPerusahaan;
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

	@Override
	public int hashCode() {
		int hash = 737;
		hash = 1211 * hash + Objects.hashCode(id);
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
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "PegawaiPerusahaanDTO {"
				.concat("id=")
				.concat(id)
				.concat("}");
	}	

	public Pegawai toPegawai() {
		return new Pegawai(
				id, 
				registerPerusahaan != null ? registerPerusahaan.toRegisterPerusahaan() : null, 
				person != null ? person.toPerson() : null, 
				jabatan != null ? jabatan.toJabatan() : null
				);
	}

}
