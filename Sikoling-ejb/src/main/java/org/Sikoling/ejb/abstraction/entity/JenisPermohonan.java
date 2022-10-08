package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class JenisPermohonan implements Serializable {

	private static final long serialVersionUID = -7418664472671865263L;
	private final String id;
	private final String nama;
	
	public JenisPermohonan(String id, String nama) {
		super();
		this.id = id;
		this.nama = nama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}
	
	public int hashCode() {
		int hash = 17;
		hash = 21 * hash + Objects.hashCode(this.id);
		hash = 21 * hash + Objects.hashCode(this.nama);
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
        
        final JenisPermohonan other = (JenisPermohonan) obj;
        
        if (!this.id.equals(other.id)) {
            return false;
        }
        
        if (!this.nama.equals(other.nama)) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "JenisPermohonan{"
				.concat("id=")
				.concat(this.id)
				.concat(", nama=")
				.concat(this.nama)
				.concat("}");
	}
	

}
