package org.Sikoling.ejb.abstraction.entity.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class LampiranSuratArahan extends Dokumen implements Serializable {

	private static final long serialVersionUID = -2283805623827974300L;
	private final String noSuratArahan;
	private final LocalDate tanggalSuratArahan;
	
	public LampiranSuratArahan(String id, String nama, KategoriDokumen kategoriDokumen, String noSuratArahan,
			LocalDate tanggalSuratArahan) {
		super(id, nama, kategoriDokumen);
		this.noSuratArahan = noSuratArahan;
		this.tanggalSuratArahan = tanggalSuratArahan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNoSuratArahan() {
		return noSuratArahan;
	}

	public LocalDate getTanggalSuratArahan() {
		return tanggalSuratArahan;
	}
	
	@Override
	public int hashCode() {
		int hash = 117;
        hash = 121 * hash + Objects.hashCode(this.getId());
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
        
        final LampiranSuratArahan other = (LampiranSuratArahan) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
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
				.concat(this.noSuratArahan)
				.concat(", tanggal=")
				.concat(this.tanggalSuratArahan.toString())
				.concat("}");
	}
	
}
