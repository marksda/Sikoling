package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Authority implements Serializable {

	private static final long serialVersionUID = 1406083948068577996L;
	private final String id;
	private final Person person;
	private final HakAkses hakAkses;
	private final Boolean statusInternal;
	private final Boolean isVerified;
	private final String userName;
	
	public Authority(
			String id, Person person, HakAkses hakAkses, 
			Boolean statusInternal, Boolean isVerified,	String userName) {
		this.id = id;
		this.person = person;
		this.hakAkses = hakAkses;
		this.statusInternal = statusInternal;
		this.isVerified = isVerified;
		this.userName = userName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getId() {
		return id;
	}

	public Person getPerson() {
		return person;
	}

	public HakAkses getHakAkses() {
		return hakAkses;
	}

	public Boolean isStatusInternal() {
		return statusInternal;
	}

	public Boolean isVerified() {
		return isVerified;
	}

	public String getUserName() {
		return userName;
	}
	
	public int hashCode() {
		int hash = 71;
		hash = 171 * hash + Objects.hashCode(this.person.getNik());
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
        
        final Authority other = (Authority) obj;
        
        if (!this.person.getNik().equals(other.person.getNik())) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "Autorisasi {"
				.concat("id=")
				.concat(this.person.getNik())
				.concat(", ")
				.concat("hakAKses=")
				.concat(this.hakAkses.getNama())
				.concat("}");
	}	

	

}
