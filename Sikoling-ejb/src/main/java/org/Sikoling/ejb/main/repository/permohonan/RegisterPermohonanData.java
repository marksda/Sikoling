package org.Sikoling.ejb.main.repository.permohonan;

import java.io.Serializable;
import java.time.LocalDate;

import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.perusahaan.PerusahaanData;
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
@Table(name="transaksi.tbl_permohonan")
@NamedQueries({
	@NamedQuery(name="RegisterPermohonanData.findAll", query="SELECT p FROM RegisterPermohonanData p"),
	@NamedQuery(name="RegisterPermohonanData.findByPengakses", query="SELECT p FROM RegisterPermohonanData p WHERE p.autorisasiData.id = :idPengakses")
})
public class RegisterPermohonanData implements Serializable {

	private static final long serialVersionUID = 2002625779202189639L;	
	
	@Id
	private String id;
	
	@JoinColumn(name="kategori_permohonan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private KategoriPermohonanData kategoriPermohonanData;	
	
	@Column(name="tanggal_registrasi")
	private LocalDate tanggalRegistrasi;	
	
	@JoinColumn(name="perusahaan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private PerusahaanData perusahaanData;
	
	@JoinColumn(name="pengakses", referencedColumnName = "user_name", insertable = true, updatable = true)
	@ManyToOne
	private AutorisasiData autorisasiData;
	
	@JoinColumn(name="kategori_pengurus_permohonan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private KategoriPengurusPermohonanData kategoriPengurusPermohonanData;
	
	@JoinColumn(name="status_tahap_pemberkasan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private StatusTahapPemberkasanData statusTahapPemberkasanData;
	
	@OneToOne(mappedBy = "registerPermohonanData", fetch = FetchType.LAZY)
	private PermohonanSuratArahanData permohonanSuratArahanData;

	public RegisterPermohonanData() {
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

	public PerusahaanData getPerusahaanData() {
		return perusahaanData;
	}

	public void setPerusahaanData(PerusahaanData perusahaanData) {
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

	public StatusTahapPemberkasanData getStatusTahapPemberkasanData() {
		return statusTahapPemberkasanData;
	}

	public void setStatusTahapPemberkasanData(StatusTahapPemberkasanData statusTahapPemberkasanData) {
		this.statusTahapPemberkasanData = statusTahapPemberkasanData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
