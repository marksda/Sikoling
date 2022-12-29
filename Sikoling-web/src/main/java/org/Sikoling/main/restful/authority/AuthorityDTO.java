package org.Sikoling.main.restful.authority;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.main.restful.person.PersonDTO;

public class AuthorityDTO implements Serializable {

	private static final long serialVersionUID = 6026401650478903435L;
	private String id;
	private PersonDTO person;
	private HakAksesDTO hakAkses;
	private Boolean statusInternal;
	private Boolean isVerified;
	private String userName;
	
	public AuthorityDTO() {
	}
	
	public AuthorityDTO(Authority t) {
		if(t != null) {
			this.id = t.getId();
			this.person = t.getPerson() != null ? new PersonDTO(t.getPerson()) : null;
			this.hakAkses = t.getHakAkses() != null ? new HakAksesDTO(t.getHakAkses()) : null;
			this.statusInternal = t.isStatusInternal() != null ? t.isStatusInternal() : false;
			this.isVerified = t.isVerified() != null ? t.isVerified() : false;
			this.userName = t.getUserName();
		}
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

	public boolean isStatusInternal() {
		return statusInternal;
	}

	public void setStatusInternal(boolean statusInternal) {
		this.statusInternal = statusInternal;
	}

	public Boolean isVerified() {
		return isVerified;
	}

	public void setVerified(Boolean isVerified) {
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
        
        final AuthorityDTO other = (AuthorityDTO) obj;
        
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
	
	public Authority toAuthority() {
		return new Authority(
				id != null ? id: null,
				person != null ? person.toPerson():null, 
				hakAkses != null ? hakAkses.toHakAkses():null, 
				statusInternal !=null ? statusInternal:null, 
				isVerified != null ? isVerified:null, 
				userName
				);
	}

}
