package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Autorisasi implements Serializable {

	private static final long serialVersionUID = 1406083948068577996L;
	private final Person person;
	private final String idLama;
	private final HakAkses hakAkses;
	private final boolean statusInternal;
	private final boolean isVerified;
	private final String userName;
	
	public Autorisasi(Person person, String idLama, HakAkses hakAkses, boolean statusInternal, boolean isVerified,
			String userName) {
		super();
		this.person = person;
		this.idLama = idLama;
		this.hakAkses = hakAkses;
		this.statusInternal = statusInternal;
		this.isVerified = isVerified;
		this.userName = userName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Person getPerson() {
		return person;
	}

	public String getIdLama() {
		return idLama;
	}

	public HakAkses getHakAkses() {
		return hakAkses;
	}

	public boolean isStatusInternal() {
		return statusInternal;
	}

	public boolean isVerified() {
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
        
        final Autorisasi other = (Autorisasi) obj;
        
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
