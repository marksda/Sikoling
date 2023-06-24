package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Otoritas implements Serializable {

	private static final long serialVersionUID = 1406083948068577996L;
	private final String id;
	private final LocalDate tanggal;
	private final Person person;
	private final HakAkses hakAkses;
	private final Boolean statusInternal;
	private final Boolean isVerified;
	private final String userName;
	
	public Otoritas(String id, LocalDate tanggal, Person person, HakAkses hakAkses, Boolean statusInternal,
			Boolean isVerified, String userName) {
		super();
		this.id = id;
		this.tanggal = tanggal;
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
	
	public LocalDate getTanggal() {
		return tanggal;
	}

	@Override
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
        
        final Otoritas other = (Otoritas) obj;
        
        if (!this.person.getNik().equals(other.person.getNik())) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "Otoriras {"
				.concat("id=")
				.concat(this.person.getNik())
				.concat(", ")
				.concat("hakAKses=")
				.concat(this.hakAkses.getNama())
				.concat("}");
	}

}
