package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_surat_arahan")
@NamedQueries({
	@NamedQuery(name="SuratArahanData.findAll", query="SELECT d FROM SuratArahanData d")
})
public class SuratArahanData implements Serializable {

	private static final long serialVersionUID = -3597035130639423972L;
	
	@Id
	private String id;
	
	@Column(name = "nomor")
	private String noSurat;
	
	@Column(name = "tanggal")
	private LocalDate tanggalSurat;
	
	@Column(name = "perihal")
	private String perihalSurat;
	
	@Column(name = "uraian_kegiatan")
	private String uraianKegiatan;
	
	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", referencedColumnName = "id")
	private RegisterDokumenData registerDokumenData;
	
	public SuratArahanData() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getUraianKegiatan() {
		return uraianKegiatan;
	}

	public void setUraianKegiatan(String uraianKegiatan) {
		this.uraianKegiatan = uraianKegiatan;
	}
	
	public RegisterDokumenData getRegisterDokumenData() {
		return registerDokumenData;
	}

	public void setRegisterDokumenData(RegisterDokumenData registerDokumenData) {
		this.registerDokumenData = registerDokumenData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
