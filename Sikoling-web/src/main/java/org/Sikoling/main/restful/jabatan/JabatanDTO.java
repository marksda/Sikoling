package org.Sikoling.main.restful.jabatan;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Jabatan;

public class JabatanDTO implements Serializable {

	private static final long serialVersionUID = -6217839017206272279L;
	private String id;
	private String nama;
	
	public JabatanDTO() {

	}
	
	public JabatanDTO(Jabatan jabatan) {
		this.id = jabatan.getId();
		this.nama = jabatan.getNama();
	}
	
	public JabatanDTO(String id, String nama) {
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
		int hash = 29;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.nama);
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
        
        final JabatanDTO other = (JabatanDTO) obj;
        
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
		return "JabatanDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}

	public Jabatan toJabatan() {
		return new Jabatan(id, nama);
	}
}
