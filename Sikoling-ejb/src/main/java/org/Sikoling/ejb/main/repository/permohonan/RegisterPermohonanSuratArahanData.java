package org.Sikoling.ejb.main.repository.permohonan;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_permohonan_surat_arahan")
@NamedQueries({
	@NamedQuery(name="PermohonanSuratArahanData.findAll", query="SELECT p FROM RegisterPermohonanData p")
})
public class RegisterPermohonanSuratArahanData implements Serializable {

	private static final long serialVersionUID = -674363634713953137L;
	
	@Id
	@JoinColumn(name="id", referencedColumnName = "id", insertable = true, updatable = true)
	@OneToOne(fetch = FetchType.EAGER)
	private RegisterPermohonanData registerPermohonanData;
	
	@JoinColumn(name = "kategori_surat_arahan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private KategoriSuratArahanData kategoriSuratArahanData;
	
	@Column(name = "uraian_kegiatan")
	private String uraianKegiatan;
	
	public RegisterPermohonanSuratArahanData() {
	}

	public RegisterPermohonanData getRegisterPermohonanData() {
		return registerPermohonanData;
	}

	public void setRegisterPermohonanData(RegisterPermohonanData registerPermohonanData) {
		this.registerPermohonanData = registerPermohonanData;
	}

	public KategoriSuratArahanData getKategoriSuratArahanData() {
		return kategoriSuratArahanData;
	}

	public void setKategoriSuratArahanData(KategoriSuratArahanData kategoriSuratArahanData) {
		this.kategoriSuratArahanData = kategoriSuratArahanData;
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

}
