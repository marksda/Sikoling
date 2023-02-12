package org.Sikoling.ejb.abstraction.entity.permohonan;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.Pegawai;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;

public class RegisterPermohonan implements Serializable {

	private static final long serialVersionUID = 6817006082859089585L;
	
	private final String id;
	private final KategoriPermohonan kategoriPermohonan;
	private final LocalDate tanggalRegistrasi;
	private final RegisterPerusahaan perusahaan;
	private final Authority pengurusPermohonan;
	private final StatusWali statusWaliPengurusPermohonan;
	private final Pegawai penanggungJawabPermohonan;
	private final PosisiTahapPemberkasan posisiBerkas;
	private final List<RegisterDokumen> daftarDokumenSyarat;
	private final List<RegisterDokumen> daftarDokumenHasil;		
	
	public RegisterPermohonan(String id, KategoriPermohonan kategoriPermohonan, LocalDate tanggalRegistrasi,
			RegisterPerusahaan perusahaan, Authority pengurusPermohonan, StatusWali statusWaliPengurusPermohonan,
			Pegawai penanggungJawabPermohonan, PosisiTahapPemberkasan posisiBerkas,
			List<RegisterDokumen> daftarDokumenSyarat, List<RegisterDokumen> daftarDokumenHasil) {
		this.id = id;
		this.kategoriPermohonan = kategoriPermohonan;
		this.tanggalRegistrasi = tanggalRegistrasi;
		this.perusahaan = perusahaan;
		this.pengurusPermohonan = pengurusPermohonan;
		this.statusWaliPengurusPermohonan = statusWaliPengurusPermohonan;
		this.penanggungJawabPermohonan = penanggungJawabPermohonan;
		this.posisiBerkas = posisiBerkas;
		this.daftarDokumenSyarat = daftarDokumenSyarat;
		this.daftarDokumenHasil = daftarDokumenHasil;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getId() {
		return id;
	}
	
	public KategoriPermohonan getKategoriPermohonan() {
		return kategoriPermohonan;
	}
	
	public LocalDate getTanggalRegistrasi() {
		return tanggalRegistrasi;
	}
	
	public RegisterPerusahaan getPerusahaan() {
		return perusahaan;
	}
	
	public Authority getPengurusPermohonan() {
		return pengurusPermohonan;
	}
	
	public StatusWali getStatusWaliPengurusPermohonan() {
		return statusWaliPengurusPermohonan;
	}
	
	public Pegawai getPenanggungJawabPermohonan() {
		return penanggungJawabPermohonan;
	}

	public PosisiTahapPemberkasan getPosisiBerkas() {
		return posisiBerkas;
	}
	
	public List<RegisterDokumen> getDaftarDokumenSyarat() {
		return daftarDokumenSyarat;
	}
	
	public List<RegisterDokumen> getDaftarDokumenHasil() {
		return daftarDokumenHasil;
	}

	@Override
	public int hashCode() {
		int hash = 1731;
		hash = 141 * hash + Objects.hashCode(id);
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
        
        final RegisterPermohonan other = (RegisterPermohonan) obj;
        if ( !this.id.equals(other.getId()) ) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "RegisterPermohonan{" + "id=" + id + "}";
	}
	
}
