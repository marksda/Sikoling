package org.Sikoling.ejb.abstraction.entity.permohonan;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.entity.StatusWali;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;

public class RegisterPermohonan implements Serializable {

	private static final long serialVersionUID = 6817006082859089585L;
	
	private final String id;
	private final KategoriPermohonan kategoriPermohonan;
	private final LocalDate tanggalRegistrasi;
	private final RegisterPerusahaan perusahaan;
	private final Authority pengurusPermohonan;
	private final StatusWali statusWaliPengurusPermohonan;
	private final PosisiTahapPemberkasan posisiBerkas;
	private final List<Dokumen> daftarDokumenSyarat;
	private final List<Dokumen> daftarDokumenHasil;	
	
	public RegisterPermohonan(String id, KategoriPermohonan kategoriPermohonan, LocalDate tanggalRegistrasi,
			RegisterPerusahaan perusahaan, Authority pengurusPermohonan,
			StatusWali statusWaliPengurusPermohonan, PosisiTahapPemberkasan posisiBerkas,
			List<Dokumen> daftarDokumenSyarat, List<Dokumen> daftarDokumenHasil) {
		super();
		this.id = id;
		this.kategoriPermohonan = kategoriPermohonan;
		this.tanggalRegistrasi = tanggalRegistrasi;
		this.perusahaan = perusahaan;
		this.pengurusPermohonan = pengurusPermohonan;
		this.statusWaliPengurusPermohonan = statusWaliPengurusPermohonan;
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
	
	public PosisiTahapPemberkasan getPosisiBerkas() {
		return posisiBerkas;
	}
	
	public List<Dokumen> getDaftarDokumenSyarat() {
		return daftarDokumenSyarat;
	}
	
	public List<Dokumen> getDaftarDokumenHasil() {
		return daftarDokumenHasil;
	}
}
