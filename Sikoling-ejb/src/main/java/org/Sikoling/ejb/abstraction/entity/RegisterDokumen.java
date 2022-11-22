package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class RegisterDokumen implements Serializable {

	private static final long serialVersionUID = 2847248198419126532L;
	private final String id;
	private final Perusahaan perusahaan;
	private final DetailDokumen detailDokumen;
	private final LocalDate tanggalTransaksi;
	private final boolean isBerlaku;
	private final Autorisasi autorisasi;
	
	public RegisterDokumen(String idTransaksi, DetailDokumen detailDokumen, LocalDate tanggalTransaksi, boolean isBerlaku, 
			Perusahaan perusahaan, Autorisasi autorisasi) {
		super();
		this.id = idTransaksi;
		this.detailDokumen = detailDokumen;
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

	public DetailDokumen getDetailDokumen() {
		return detailDokumen;
	}

	public LocalDate getTanggalTransaksi() {
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
				.concat(this.detailDokumen.getDokumen().getNama())
				.concat("}");
	}	

}
