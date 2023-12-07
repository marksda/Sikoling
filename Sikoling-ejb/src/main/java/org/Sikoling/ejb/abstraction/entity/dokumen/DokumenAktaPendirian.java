package org.Sikoling.ejb.abstraction.entity.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Pegawai;

public class DokumenAktaPendirian extends Dokumen implements Serializable {	
	
	private static final long serialVersionUID = -3186081818421591782L;	
	private final String nomor;
	private final LocalDate tanggal;
	private final String namaNotaris;
	private final Pegawai penanggungJawab;
	
	public DokumenAktaPendirian(String id, String nama, String nomor, LocalDate tanggal, String namaNotaris, Pegawai penanggungJawab) {
		super(id, nama);
		this.nomor = nomor;
		this.tanggal = tanggal;
		this.namaNotaris = namaNotaris;
		this.penanggungJawab = penanggungJawab;
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

	public String getNamaNotaris() {
		return namaNotaris;
	}

	public Pegawai getPenanggungJawab() {
		return penanggungJawab;
	}

	@Override
	public int hashCode() {
		int hash = 173;
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
        
        final DokumenAktaPendirian other = (DokumenAktaPendirian) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
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
