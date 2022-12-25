package org.Sikoling.ejb.abstraction.entity.dokumen;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.json.JsonObject;

public class SuratArahan extends Dokumen implements Serializable {
	private static final long serialVersionUID = 751126801061108282L;
	private final String noSurat;
	private final LocalDate tanggalSurat;
	private final String perihalSurat;
	private String uraianKegiatan;	
	
	public SuratArahan(String id, String nama, KategoriDokumen kategoriDokumen, JsonObject detailAttributeDokumen,
			String noSurat, LocalDate tanggalSurat, String perihalSurat, String uraianKegiatan) {
		super(id, nama, kategoriDokumen, detailAttributeDokumen);
		this.noSurat = noSurat;
		this.tanggalSurat = tanggalSurat;
		this.perihalSurat = perihalSurat;
		this.uraianKegiatan = uraianKegiatan;
	}

	public String getUraianKegiatan() {
		return uraianKegiatan;
	}

	public void setUraianKegiatan(String uraianKegiatan) {
		this.uraianKegiatan = uraianKegiatan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNoSurat() {
		return noSurat;
	}

	public LocalDate getTanggalSurat() {
		return tanggalSurat;
	}

	public String getPerihalSurat() {
		return perihalSurat;
	}
	
	
}
