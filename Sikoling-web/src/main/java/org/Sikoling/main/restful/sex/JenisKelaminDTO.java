package org.Sikoling.main.restful.sex;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.JenisKelamin;

public class JenisKelaminDTO implements Serializable {

	private static final long serialVersionUID = -4894237329970283198L;
	private String id;
	private String nama;
	
	public JenisKelaminDTO() {
	}
	
	public JenisKelaminDTO(JenisKelamin jenisKelamin) {
		super();
		this.id = jenisKelamin.getId();
		this.nama = jenisKelamin.getNama();
	}
	
	public JenisKelaminDTO(String id, String nama) {
		super();
		this.id = id;
		this.nama = nama;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.nama);
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
        
        final JenisKelaminDTO other = (JenisKelaminDTO) obj;
        
        if (this.id != other.id) {
            return false;
        }
        
        if (this.nama != other.nama) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "JenisKelaminDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}

	public JenisKelamin toJenisKelamin() {
		return new JenisKelamin(id, nama);
	}


}
