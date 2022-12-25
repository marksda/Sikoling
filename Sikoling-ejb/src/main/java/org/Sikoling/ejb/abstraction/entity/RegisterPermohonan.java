package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;

public class RegisterPermohonan implements Serializable {

	private static final long serialVersionUID = 6817006082859089585L;
	
	private final String id;
	private final KategoriPermohonan kategoriPermohonan;
	private final LocalDate tanggalRegistrasi;
	private final Perusahaan perusahaan;
	private final Authority pengakses;
	private final StatusWali statusWaliPengurusPermohonan;
	private final StatusTahapPemberkasan statusPermohonan;
	private final List<Dokumen> daftarDokumen;
	
	public RegisterPermohonan(String id, KategoriPermohonan kategoriPermohonan, LocalDate tanggalRegistrasi,
			Perusahaan perusahaan, Authority pengakses, StatusWali statusWaliPengurusPermohonan,
			StatusTahapPemberkasan statusPermohonan, List<Dokumen> daftarDokumen) {
		super();
		this.id = id;
		this.kategoriPermohonan = kategoriPermohonan;
		this.tanggalRegistrasi = tanggalRegistrasi;
		this.perusahaan = perusahaan;
		this.pengakses = pengakses;
		this.statusWaliPengurusPermohonan = statusWaliPengurusPermohonan;
		this.statusPermohonan = statusPermohonan;
		this.daftarDokumen = daftarDokumen;
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

	public Perusahaan getPerusahaan() {
		return perusahaan;
	}

	public Authority getPengakses() {
		return pengakses;
	}

	public StatusWali getStatusWaliPengurusPermohonan() {
		return statusWaliPengurusPermohonan;
	}

	public StatusTahapPemberkasan getStatusPermohonan() {
		return statusPermohonan;
	}

	public List<Dokumen> getDaftarDokumen() {
		return daftarDokumen;
	}
	
	
 

}
