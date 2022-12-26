package org.Sikoling.ejb.abstraction.entity.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Alamat;

public class IzinLokasi extends Dokumen implements Serializable {

	private static final long serialVersionUID = -989821650490601749L;
	private final String nomor;
	private final LocalDate tanggal;
	private final Alamat lokasiKegiatan;	
	
	public IzinLokasi(String id, String nama, KategoriDokumen kategoriDokumen, String nomor, LocalDate tanggal,
			Alamat lokasiKegiatan) {
		super(id, nama, kategoriDokumen);
		this.nomor = nomor;
		this.tanggal = tanggal;
		this.lokasiKegiatan = lokasiKegiatan;
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

	public Alamat getLokasiKegiatan() {
		return lokasiKegiatan;
	}
	

	@Override
	public int hashCode() {
		int hash = 137;
        hash = 141 * hash + Objects.hashCode(this.nomor);
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
        
        final IzinLokasi other = (IzinLokasi) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
            return false;
        }
        
        if (!this.nomor.equalsIgnoreCase(other.nomor)) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "IzinLokasi{ id=" 
				.concat(this.getId())
				.concat(", nomor=")
				.concat(nomor)
				.concat("}");
	}
	
	
}
