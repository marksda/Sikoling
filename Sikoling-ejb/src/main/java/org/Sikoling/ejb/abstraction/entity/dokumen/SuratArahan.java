package org.Sikoling.ejb.abstraction.entity.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class SuratArahan extends Dokumen implements Serializable {
	private static final long serialVersionUID = 751126801061108282L;
	private final String noSurat;
	private final LocalDate tanggalSurat;
	private final String perihalSurat;
	private String uraianKegiatan;	
	
	public SuratArahan(String id, String nama, KategoriDokumen kategoriDokumen, 
			String noSurat, LocalDate tanggalSurat, String perihalSurat, String uraianKegiatan) {
		super(id, nama, kategoriDokumen);
		this.noSurat = noSurat;
		this.tanggalSurat = tanggalSurat;
		this.perihalSurat = perihalSurat;
		this.uraianKegiatan = uraianKegiatan;
	}

	public String getUraianKegiatan() {
		return uraianKegiatan;
	}

	public void setUraianKegiatan(String uraianKegiatan) {
		this.uraianKegiatan = uraianKegiatan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNoSurat() {
		return noSurat;
	}

	public LocalDate getTanggalSurat() {
		return tanggalSurat;
	}

	public String getPerihalSurat() {
		return perihalSurat;
	}	

	@Override
	public int hashCode() {
		int hash = 117;
        hash = 121 * hash + Objects.hashCode(this.noSurat);
        hash = 121 * hash + Objects.hashCode(this.tanggalSurat.toString());
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
        
        final SuratArahan other = (SuratArahan) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
            return false;
        }
        
        if (!this.noSurat.equalsIgnoreCase(other.getNoSurat())) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "SuratArahan{"
				.concat("id=")
				.concat(this.getId())
				.concat(", nomor=")
				.concat(this.noSurat)
				.concat(", tanggal=")
				.concat(tanggalSurat.toString())
				.concat("}");
	}
		
}
