package org.Sikoling.ejb.abstraction.entity.dokumen;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.entity.Person;

public class AktaPendirian extends Dokumen implements Serializable {
	private static final long serialVersionUID = -3186081818421591782L;	
	private final String nomor;
	private final Date tanggal;
	private final String namaNotaris;
	private final Person penanggungJawab;
	private final Jabatan jabatanPenanggungJawab;
	
	public AktaPendirian(String id, String nama, KategoriDokumen kategoriDokumen, String nomor, Date tanggal,
			String namaNotaris, Person penanggungJawab, Jabatan jabatanPenanggungJawab) {
		super(id, nama, kategoriDokumen);
		this.nomor = nomor;
		this.tanggal = tanggal;
		this.namaNotaris = namaNotaris;
		this.penanggungJawab = penanggungJawab;
		this.jabatanPenanggungJawab = jabatanPenanggungJawab;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNomor() {
		return nomor;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public String getNamaNotaris() {
		return namaNotaris;
	}

	public Person getPenanggungJawab() {
		return penanggungJawab;
	}

	public Jabatan getJabatanPenanggungJawab() {
		return jabatanPenanggungJawab;
	}

	public int hashCode() {
		int hash = 17;
        hash = 121 * hash + Objects.hashCode(this.nomor);
        hash = 121 * hash + Objects.hashCode(this.tanggal.toString());
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
        
        final AktaPendirian other = (AktaPendirian) obj;
        
        if (!this.nomor.equalsIgnoreCase(other.nomor)) {
            return false;
        }
        
        if (!Objects.equals(this.tanggal, other.tanggal)) {
            return false;
        }
        
        if (!this.namaNotaris.equalsIgnoreCase(other.namaNotaris)) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "AktaPemrakarsa{"
				.concat("id=")
				.concat(this.getId())
				.concat(", nomor=")
				.concat(this.nomor)
				.concat(", tanggal=")
				.concat(tanggal.toString())
				.concat("}");
	}
		
}
