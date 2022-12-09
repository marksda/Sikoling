package org.Sikoling.main.restful.authority;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.main.restful.person.PersonDTO;

public class AuthorityDTO implements Serializable {

	private static final long serialVersionUID = 6026401650478903435L;
	private String id;
	private PersonDTO person;
	private String idLama;
	private HakAksesDTO hakAkses;
	private boolean statusInternal;
	private boolean isVerified;
	private String userName;
	
	public AuthorityDTO() {
	}
	
	public AuthorityDTO(Authority t) {
		this.id = t.getId();
		this.person = new PersonDTO(t.getPerson());
		this.idLama = t.getIdLama();
		this.hakAkses = new HakAksesDTO(t.getHakAkses());
		this.statusInternal = t.isStatusInternal();
		this.isVerified = t.isVerified();
		this.userName = t.getUserName();
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

	public String getIdLama() {
		return idLama;
	}

	public void setIdLama(String idLama) {
		this.idLama = idLama;
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

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
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
		hash = 121 * hash + Objects.hashCode(this.id);
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
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "AuthorityDTO {"
				.concat("id=")
				.concat(this.id)
				.concat(", person=")
				.concat(this.person.getNama())
				.concat(", userName=")
				.concat(this.userName)
				.concat("}");
	}
	
	public Authority toAuthority() {
		return new Authority(
				id, 
				person.toPerson(), 
				idLama, 
				hakAkses.toHakAkses(), 
				statusInternal, 
				isVerified, 
				userName
				);
	}

}
