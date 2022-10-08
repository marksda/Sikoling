package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Permohonan implements Serializable {

	private static final long serialVersionUID = -2507712010024456804L;	
	private final Date tanggalPendaftaran;	
	private final String nomor;
	private final Perusahaan pemrakarsa;
	private final JenisPermohonan jenisPermohonan;
	private final Wali wali;
	
	public Permohonan(Date tanggalPendaftaran, String nomor, Perusahaan pemrakarsa,
			JenisPermohonan jenisPermohonan, Wali wali) {
		super();
		this.tanggalPendaftaran = tanggalPendaftaran;
		this.nomor = nomor;
		this.pemrakarsa = pemrakarsa;
		this.jenisPermohonan = jenisPermohonan;
		this.wali = wali;
	}

	public String getNomor() {
		return nomor;
	}

	public Date getTanggalPendaftaran() {
		return tanggalPendaftaran;
	}

	public Wali getWali() {
		return wali;
	}
	
	public Perusahaan getPemrakarsa() {
		return pemrakarsa;
	}

	public JenisPermohonan getJenisPermohonan() {
		return jenisPermohonan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int hashCode() {
		int hash = 31;
		hash = 7 * hash + Objects.hashCode(this.getNomor());
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
        
        final Permohonan other = (Permohonan) obj;
        
        if (!Objects.equals(this.nomor, other)) {
            return false;
        }
        
        if (!this.nomor.equals(other.getNomor())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "Permohonan{tanggalPendaftaran=" 
				.concat(this.tanggalPendaftaran.toString())
				.concat(", nomor=")
				.concat(this.nomor)
				.concat(", pemrakarsa=")
				.concat(this.pemrakarsa.getNama());				
	}

}
