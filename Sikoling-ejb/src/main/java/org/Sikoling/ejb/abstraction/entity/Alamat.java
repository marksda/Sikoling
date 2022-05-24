package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class Alamat implements Serializable {

	private static final long serialVersionUID = 8115464344624994983L;
	
	private final String propinsi;
	private final String kabupaten;
	private final String kecamatan;
	private final String desa;
	private final String keterangan;
	
	public Alamat(String propinsi, String kabupaten, String kecamatan, String desa, String keterangan) {
		super();
		this.propinsi = propinsi;
		this.kabupaten = kabupaten;
		this.kecamatan = kecamatan;
		this.desa = desa;
		this.keterangan = keterangan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPropinsi() {
		return propinsi;
	}

	public String getKabupaten() {
		return kabupaten;
	}

	public String getKecamatan() {
		return kecamatan;
	}

	public String getDesa() {
		return desa;
	}

	public String getKeterangan() {
		return keterangan;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + Objects.hashCode(this.propinsi);
		hash = 13 * hash + Objects.hashCode(this.kabupaten);
		hash = 13 * hash + Objects.hashCode(this.kecamatan);
		hash = 13 * hash + Objects.hashCode(this.desa);
		hash = 13 * hash + Objects.hashCode(this.keterangan);
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
        
        final Alamat other = (Alamat) obj;
        
        if (!this.propinsi.equals(other.propinsi)) {
            return false;
        }
        
        if (!this.kabupaten.equals(other.kabupaten)) {
            return false;
        }
        
        if (!this.kecamatan.equals(other.kecamatan)) {
            return false;
        }
        
        if (!this.desa.equals(other.desa)) {
            return false;
        }
        
        if (!this.keterangan.equals(other.keterangan)) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "Alamat{" + "propinsi=" + propinsi + "kabupaten=" + kabupaten + "propinsi=" + propinsi 
				+ "kecamatan=" + kecamatan + "desa=" + desa + ", keterangan=" + keterangan + "}";
	}

}
