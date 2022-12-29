package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Pegawai implements Serializable {

	private static final long serialVersionUID = -7780214246997693171L;
	private final String id;
	private final Perusahaan perusahaan;
	private final Person person;
	private final Jabatan jabatan;
	
	public Pegawai(String id, Perusahaan perusahaan, Person person, Jabatan jabatan) {
		this.id = id;
		this.perusahaan = perusahaan;
		this.person = person;
		this.jabatan = jabatan;
	}
	
	public Person getPerson() {
		return person;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public Perusahaan getPerusahaan() {
		return perusahaan;
	}

	public Jabatan getJabatan() {
		return jabatan;
	}
	
	public int hashCode() {
		int hash = 137;
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
        
        final Pegawai other = (Pegawai) obj;
        
        if (!this.id.equalsIgnoreCase(other.getId())) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "Pegawai{"
				.concat("id=")
				.concat(this.getId())
				.concat("}");
	}

}
