package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.time.LocalDate;

class RegisterDokumen implements Serializable {
	
	private static final long serialVersionUID = 5607669072989245707L;
	private final String id;
	private final Dokumen dokumen;
	private final Perusahaan perusahaan;
	private final String lokasiFile;
	private final LocalDate tanggalRegistrasi;
	private final boolean statusBerlaku;
	private final Autorisasi autorisasi;

	public RegisterDokumen(Dokumen dokumen, Perusahaan perusahaan, String lokasiFile, LocalDate tanggalRegistrasi,
			boolean statusBerlaku, Autorisasi autorisasi) {
		this.id = perusahaan.getId().concat("*").concat(dokumen.getId());
		this.dokumen = dokumen;
		this.perusahaan = perusahaan;
		this.lokasiFile = lokasiFile;
		this.tanggalRegistrasi = tanggalRegistrasi;
		this.statusBerlaku = statusBerlaku;
		this.autorisasi = autorisasi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public Dokumen getDokumen() {
		return dokumen;
	}

	public Perusahaan getPerusahaan() {
		return perusahaan;
	}

	public String getLokasiFile() {
		return lokasiFile;
	}

	public LocalDate getTanggalRegistrasi() {
		return tanggalRegistrasi;
	}

	public boolean isStatusBerlaku() {
		return statusBerlaku;
	}

	public Autorisasi getAutorisasi() {
		return autorisasi;
	}
	
	
	
}
