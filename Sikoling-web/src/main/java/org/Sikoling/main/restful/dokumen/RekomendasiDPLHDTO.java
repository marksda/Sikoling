package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.time.LocalDate;

import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.RekomendasiDPLH;

public class RekomendasiDPLHDTO extends DokumenDTO implements Serializable {

	private static final long serialVersionUID = -3450087905995320522L;
	private String noSurat;
	private LocalDate tanggalSurat;
	private String perihalSurat;
	
	public RekomendasiDPLHDTO() {
	}
	
	public RekomendasiDPLHDTO(RekomendasiDPLH t) {
		super(t != null ? new Dokumen(
				t.getId(), 
				t.getNama(), 
				null
				) : null);

		if(t != null) {
			this.noSurat = t.getNomor();
			this.tanggalSurat = t.getTanggal();
			this.perihalSurat = t.getPerihal();
		}
	}
	

	public String getNoSurat() {
		return noSurat;
	}
	

	public void setNoSurat(String noSurat) {
		this.noSurat = noSurat;
	}
	

	public LocalDate getTanggalSurat() {
		return tanggalSurat;
	}
	

	public void setTanggalSurat(LocalDate tanggalSurat) {
		this.tanggalSurat = tanggalSurat;
	}
	

	public String getPerihalSurat() {
		return perihalSurat;
	}
	

	public void setPerihalSurat(String perihalSurat) {
		this.perihalSurat = perihalSurat;
	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public RekomendasiDPLH toRekomendasiDPLH() {
		KategoriDokumenDTO kategoriDokumenDTO = this.getKategoriDokumen();
		return new RekomendasiDPLH(
				this.getId(), 
				this.getNama(), 
				kategoriDokumenDTO != null ? new KategoriDokumen(
						kategoriDokumenDTO.getId(), 
						kategoriDokumenDTO.getNama(), 
						kategoriDokumenDTO.getParent()
						) : null, 
				noSurat, 
				tanggalSurat, 
				perihalSurat
				);
	}
}
