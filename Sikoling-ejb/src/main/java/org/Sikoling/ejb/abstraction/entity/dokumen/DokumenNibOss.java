package org.Sikoling.ejb.abstraction.entity.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class DokumenNibOss extends Dokumen implements Serializable {

	private static final long serialVersionUID = 5827527013544422359L;
	private final String nomor;
	private final LocalDate tanggal;
	private final List<Kbli> daftarKbli;	
	
	public DokumenNibOss(String id, String nama, String nomor, LocalDate tanggal, List<Kbli> daftarKbli) {
		super(id, nama);
		this.nomor = nomor;
		this.tanggal = tanggal;
		this.daftarKbli = daftarKbli;
	}	
	
	public List<Kbli> getDaftarKbli() {
		return daftarKbli;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNomor() {
		return nomor;
	}

	public LocalDate getTanggal() {
		return tanggal;
	}
	
	@Override
	public int hashCode() {
		int hash = 237;
        hash = 141 * hash + Objects.hashCode(this.getId());
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
        
        final DokumenNibOss other = (DokumenNibOss) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "NibOss{ id=" 
				.concat(this.getId())
				.concat(", nomor=")
				.concat(nomor)
				.concat("}");
	}
}
