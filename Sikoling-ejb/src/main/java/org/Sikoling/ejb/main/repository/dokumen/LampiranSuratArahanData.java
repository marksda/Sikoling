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
@Table(name="transaksi.tbl_lampiran_surat_arahan")
@NamedQueries({
	@NamedQuery(name="LampiranSuratArahanData.findAll", query="SELECT d FROM LampiranSuratArahanData d")
})
public class LampiranSuratArahanData implements Serializable {

	private static final long serialVersionUID = -3360026058786209938L;
	
	@Id
	private String id;
	
	@Column(name = "nomor_surat_keterangan")
	private String noSurat;
	
	@Column(name = "tanggal_surat_keterangan")
	private LocalDate tanggalSurat;
	
	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", referencedColumnName = "id")
	private RegisterDokumenData registerDokumenData;
	
	public LampiranSuratArahanData() {
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
