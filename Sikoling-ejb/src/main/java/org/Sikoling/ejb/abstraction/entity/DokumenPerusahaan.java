package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class DokumenPerusahaan implements Serializable {

	private static final long serialVersionUID = -1869365745189974891L;
	private final String id;
	private final Perusahaan perusahaan;
	private final DetailDokumenPerusahaan detailDokumenPerusahaan;
	private final Date tanggalUpload;
	private final boolean isBerlaku;
	private final Map<String, Object> atribute;
	private final String lokasiFile;	
	
	public DokumenPerusahaan(String id, Perusahaan perusahaan, DetailDokumenPerusahaan detailDokumenPerusahaan,
			Date tanggalUpload, boolean isBerlaku, Map<String, Object> atribute, String lokasiFile) {
		super();
		this.id = id;
		this.perusahaan = perusahaan;
		this.detailDokumenPerusahaan = detailDokumenPerusahaan;
		this.tanggalUpload = tanggalUpload;
		this.isBerlaku = isBerlaku;
		this.atribute = atribute;
		this.lokasiFile = lokasiFile;
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

	public DetailDokumenPerusahaan getDetailDokumenPerusahaan() {
		return detailDokumenPerusahaan;
	}

	public Date getTanggalUpload() {
		return tanggalUpload;
	}

	public boolean isBerlaku() {
		return isBerlaku;
	}

	public Map<String, Object> getAtribute() {
		return atribute;
	}

	public String getLokasiFile() {
		return lokasiFile;
	}

	public int hashCode() {
		int hash = 19;
		hash = 41 * hash + Objects.hashCode(this.id);
		hash = 41 * hash + Objects.hashCode(this.detailDokumenPerusahaan.getNama());
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
        
        final DokumenPerusahaan other = (DokumenPerusahaan) obj;
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "DokumenPerusahaan {"
				.concat("id=")
				.concat(this.id)
				.concat(", ")
				.concat("nama=")
				.concat(this.detailDokumenPerusahaan.getNama())
				.concat("}");
	}	
}
