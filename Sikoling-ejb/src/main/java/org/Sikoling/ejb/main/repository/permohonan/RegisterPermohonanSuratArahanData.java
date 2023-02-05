package org.Sikoling.ejb.main.repository.permohonan;

import java.io.Serializable;

import org.Sikoling.ejb.main.repository.dokumen.SuratArahanData;

import jakarta.persistence.Entity;
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
public class PermohonanSuratArahanData implements Serializable {

	private static final long serialVersionUID = -674363634713953137L;
	
	@Id
	@JoinColumn(name="id", referencedColumnName = "id", insertable = true, updatable = true)
	@OneToOne
	private RegisterPermohonanData registerPermohonanData;
	
	@JoinColumn(name = "kategori_surat_arahan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private KategoriSuratArahanData kategoriSuratArahanData;
	
	@JoinColumn(name = "surat_arahan", referencedColumnName = "id")
	@OneToOne
	private SuratArahanData suratArahanData;

	public PermohonanSuratArahanData() {
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

	public SuratArahanData getSuratArahanData() {
		return suratArahanData;
	}

	public void setSuratArahanData(SuratArahanData suratArahanData) {
		this.suratArahanData = suratArahanData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

}
