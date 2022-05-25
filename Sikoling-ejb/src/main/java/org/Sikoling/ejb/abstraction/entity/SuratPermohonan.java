package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class SuratPermohonan implements Serializable {

	private static final long serialVersionUID = 3360094098987919834L;
	private Date tanggal;
	private String nomor;
	private PenanggungJawab penanggungJawab;
	private Pemrakarsa pemrakarsa;
	private Alamat alamatKegiatan;
	private String jenisKegiatan;
	
	public SuratPermohonan(Date tanggal, String nomor, PenanggungJawab penanggungJawab, Pemrakarsa pemrakarsa,
			Alamat alamatKegiatan, String jenisKegiatan) {
		super();
		this.tanggal = tanggal;
		this.nomor = nomor;
		this.penanggungJawab = penanggungJawab;
		this.pemrakarsa = pemrakarsa;
		this.alamatKegiatan = alamatKegiatan;
		this.jenisKegiatan = jenisKegiatan;
	}


	public Date getTanggal() {
		return tanggal;
	}

	
	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	
	public String getNomor() {
		return nomor;
	}

	
	public void setNomor(String nomor) {
		this.nomor = nomor;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PenanggungJawab getPenanggungJawab() {
		return penanggungJawab;
	}
	


	public void setPenanggungJawab(PenanggungJawab penanggungJawab) {
		this.penanggungJawab = penanggungJawab;
	}
	


	public Pemrakarsa getPemrakarsa() {
		return pemrakarsa;
	}
	


	public void setPemrakarsa(Pemrakarsa pemrakarsa) {
		this.pemrakarsa = pemrakarsa;
	}
	


	public Alamat getAlamatKegiatan() {
		return alamatKegiatan;
	}
	


	public void setAlamatKegiatan(Alamat alamatKegiatan) {
		this.alamatKegiatan = alamatKegiatan;
	}
	


	public String getJenisKegiatan() {
		return jenisKegiatan;
	}
	


	public void setJenisKegiatan(String jenisKegiatan) {
		this.jenisKegiatan = jenisKegiatan;
	}
	


	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + Objects.hashCode(this.tanggal.toString());
		hash = 13 * hash + Objects.hashCode(this.nomor);
		hash = 13 * hash + Objects.hashCode(this.penanggungJawab.toString());
		hash = 13 * hash + Objects.hashCode(this.pemrakarsa.toString());
		hash = 13 * hash + Objects.hashCode(this.alamatKegiatan.toString());
		hash = 13 * hash + Objects.hashCode(this.jenisKegiatan);
		
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
        
        final SuratPermohonan other = (SuratPermohonan) obj;
        
        if (!Objects.equals(this.tanggal, other.tanggal)) {
            return false;
        }
        
        if (!Objects.equals(this.nomor, other.nomor)) {
            return false;
        }
        
        if (!Objects.equals(this.penanggungJawab, other.penanggungJawab)) {
            return false;
        }
        
        if (!Objects.equals(this.pemrakarsa, other.pemrakarsa)) {
            return false;
        }
        
        if (!Objects.equals(this.alamatKegiatan, other.alamatKegiatan)) {
            return false;
        }
        
        if (!Objects.equals(this.jenisKegiatan, other.jenisKegiatan)) {
            return false;
        }
        
        return true;
	}


	@Override
	public String toString() {
		return "SuratPermohonan{" + "tanggal=" + tanggal.toString() + ", nomor=" + nomor + "}";
	}
	
	

}
