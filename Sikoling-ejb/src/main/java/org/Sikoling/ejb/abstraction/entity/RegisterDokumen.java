package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class RegisterDokumen implements Serializable {

	private static final long serialVersionUID = 2847248198419126532L;
	private final String id;
	private final Perusahaan perusahaan;
	private final Dokumen dokumen;
	private final Date tanggalTransaksi;
	private final boolean isBerlaku;
	private final Autorisasi autorisasi;
	
	public RegisterDokumen(String idTransaksi, Dokumen dokumen, Date tanggalTransaksi, boolean isBerlaku, 
			Perusahaan perusahaan, Autorisasi autorisasi) {
		super();
		this.id = idTransaksi;
		this.dokumen = dokumen;
		this.tanggalTransaksi = tanggalTransaksi;
		this.isBerlaku = isBerlaku;
		this.perusahaan = perusahaan;
		this.autorisasi = autorisasi;
	}

	public String getId() {
		return id;
	}

	public Autorisasi getAutorisasi() {
		return autorisasi;
	}

	public Perusahaan getPerusahaan() {
		return perusahaan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Dokumen getDokumen() {
		return dokumen;
	}

	public Date getTanggalTransaksi() {
		return tanggalTransaksi;
	}

	public boolean getIsBerlaku() {
		return isBerlaku;
	}

	public int hashCode() {
		int hash = 19;
		hash = 41 * hash + Objects.hashCode(this.id);
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
        
        final RegisterDokumen other = (RegisterDokumen) obj;
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "ItemTransaksiDokumen {"
				.concat("id=")
				.concat(this.id)
				.concat(", ")
				.concat("nama dokumen=")
				.concat(this.dokumen.getNama())
				.concat("}");
	}	

}
