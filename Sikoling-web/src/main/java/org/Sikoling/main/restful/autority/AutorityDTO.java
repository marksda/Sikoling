package org.Sikoling.main.restful.autority;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Autority;
import org.Sikoling.main.restful.person.PersonDTO;

public class AutorityDTO implements Serializable {

	private static final long serialVersionUID = 6026401650478903435L;
	private String id;
	private LocalDate tanggal;
	private PersonDTO person;
	private HakAksesDTO hakAkses;
	private Boolean statusInternal;
	private Boolean isVerified;
	private String userName;
	
	public AutorityDTO() {
	}
	
	public AutorityDTO(Autority t) {
		if(t != null) {
			this.id = t.getId();
			this.tanggal = t.getTanggal();
			this.person = t.getPerson() != null ? new PersonDTO(t.getPerson()) : null;
			this.hakAkses = t.getHakAkses() != null ? new HakAksesDTO(t.getHakAkses()) : null;
			this.statusInternal = t.isStatusInternal() != null ? t.isStatusInternal() : false;
			this.isVerified = t.isVerified() != null ? t.isVerified() : false;
			this.userName = t.getUserName();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getTanggal() {
		return tanggal;
	}

	public void setTanggal(LocalDate tanggal) {
		this.tanggal = tanggal;
	}

	public PersonDTO getPerson() {
		return person;
	}

	public void setPerson(PersonDTO person) {
		this.person = person;
	}

	public HakAksesDTO getHakAkses() {
		return hakAkses;
	}

	public void setHakAkses(HakAksesDTO hakAkses) {
		this.hakAkses = hakAkses;
	}

	public Boolean getStatusInternal() {
		return statusInternal;
	}

	public void setStatusInternal(Boolean statusInternal) {
		this.statusInternal = statusInternal;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 37;
		hash = 121 * hash + Objects.hashCode(userName);
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
        
        final AutorityDTO other = (AutorityDTO) obj;
        
        if (!this.userName.equals(other.getUserName())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "AuthorityDTO {"
				.concat("userName=")
				.concat(userName)
				.concat("}");
	}
	
	public Autority toAuthority() {
		return new Autority(
				id != null ? id: null,
				tanggal,
				person != null ? person.toPerson():null, 
				hakAkses != null ? hakAkses.toHakAkses():null, 
				statusInternal !=null ? statusInternal:null, 
				isVerified != null ? isVerified:null, 
				userName
				);
	}

}
