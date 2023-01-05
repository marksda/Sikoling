package org.Sikoling.ejb.main.repository.permohonan;

import java.io.Serializable;
import java.time.LocalDate;
import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.perusahaan.RegisterPerusahaanData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_register_permohonan")
@NamedQueries({
	@NamedQuery(name="RegisterPermohonanData.findAll", query="SELECT p FROM RegisterPermohonanData p")
})
public class RegisterPermohonanData implements Serializable {

	private static final long serialVersionUID = 2002625779202189639L;	
	
	@Id
	private String id;
	
	@JoinColumn(name="kategori_permohonan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private KategoriPermohonanData kategoriPermohonanData;	
	
	@Column(name="tanggal_registrasi", insertable = true, updatable = true)
	private LocalDate tanggalRegistrasi;	
	
	@JoinColumn(name="perusahaan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private RegisterPerusahaanData perusahaanData;
	
	@JoinColumn(name="pengakses", referencedColumnName = "user_name", insertable = true, updatable = true)
	@ManyToOne
	private AutorisasiData autorisasiData;
	
	@JoinColumn(name="kategori_pengurus_permohonan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private KategoriPengurusPermohonanData kategoriPengurusPermohonanData;
	
	@JoinColumn(name="posisi_tahap_pemberkasan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private PosisiTahapPemberkasanData posisiTahapPemberkasanData;
	
	@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
	private PermohonanSuratArahanData permohonanSuratArahanData;
	
//	@OneToMany(mappedBy = "registerPermohonan", fetch = FetchType.LAZY)
//	private List<FlowLogPermohonanData> daftarFlowLogPermohonanData;

	public RegisterPermohonanData() {
	}
	
	public PermohonanSuratArahanData getPermohonanSuratArahanData() {
		return permohonanSuratArahanData;
	}

	public void setPermohonanSuratArahanData(PermohonanSuratArahanData permohonanSuratArahanData) {
		this.permohonanSuratArahanData = permohonanSuratArahanData;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public KategoriPermohonanData getKategoriPermohonanData() {
		return kategoriPermohonanData;
	}

	public void setKategoriPermohonanData(KategoriPermohonanData kategoriPermohonanData) {
		this.kategoriPermohonanData = kategoriPermohonanData;
	}

	public LocalDate getTanggalRegistrasi() {
		return tanggalRegistrasi;
	}

	public void setTanggalRegistrasi(LocalDate tanggalRegistrasi) {
		this.tanggalRegistrasi = tanggalRegistrasi;
	}

	public RegisterPerusahaanData getPerusahaanData() {
		return perusahaanData;
	}

	public void setPerusahaanData(RegisterPerusahaanData perusahaanData) {
		this.perusahaanData = perusahaanData;
	}

	public AutorisasiData getAutorisasiData() {
		return autorisasiData;
	}

	public void setAutorisasiData(AutorisasiData autorisasiData) {
		this.autorisasiData = autorisasiData;
	}

	public KategoriPengurusPermohonanData getKategoriPengurusPermohonanData() {
		return kategoriPengurusPermohonanData;
	}

	public void setKategoriPengurusPermohonanData(KategoriPengurusPermohonanData kategoriPengurusPermohonanData) {
		this.kategoriPengurusPermohonanData = kategoriPengurusPermohonanData;
	}

	public PosisiTahapPemberkasanData getPosisiTahapPemberkasanData() {
		return posisiTahapPemberkasanData;
	}

	public void setPosisiTahapPemberkasanData(PosisiTahapPemberkasanData posisiTahapPemberkasanData) {
		this.posisiTahapPemberkasanData = posisiTahapPemberkasanData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	 
}
