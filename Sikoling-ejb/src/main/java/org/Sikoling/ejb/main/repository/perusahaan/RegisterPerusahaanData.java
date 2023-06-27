package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

import org.Sikoling.ejb.main.repository.alamat.AlamatData;
import org.Sikoling.ejb.main.repository.dokumen.RegisterDokumenData;
import org.Sikoling.ejb.main.repository.kontak.KontakData;
import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanData;
import org.Sikoling.ejb.main.repository.otoritas.OtoritasData;
import org.Sikoling.ejb.main.repository.otoritas.OtoritasPerusahaanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;


@Entity
@Table(name="master.tbl_perusahaan")
@NamedQueries({
	@NamedQuery(name="RegisterPerusahaanData.findAll", query="SELECT p FROM RegisterPerusahaanData p"),
	@NamedQuery(name="RegisterPerusahaanData.findByQueryNama", query="SELECT p FROM RegisterPerusahaanData p WHERE p.nama LIKE :nama"),
	@NamedQuery(name="RegisterPerusahaanData.findByIdKreator", query="SELECT p FROM RegisterPerusahaanData p WHERE p.kreator.id = :idKreator"),
	@NamedQuery(name="RegisterPerusahaanData.findByNpwp", query="SELECT p FROM RegisterPerusahaanData p WHERE p.npwp = :npwp")
})
public class RegisterPerusahaanData implements Serializable {
	private static final long serialVersionUID = 5667247303637293789L;

	@Id
	private String id;
	
	private String nama;
	
	@Embedded
	private AlamatData alamatPerusahaanData;
	
	@JoinColumn(name="model_perizinan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne(optional = false)
	private ModelPerizinanData modelPerizinanData;
	
	@JoinColumn(name="skala_usaha", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne(optional = false)
	private SkalaUsahaData skalaUsahaData;
		
	@JoinColumn(name="pelaku_usaha", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne(optional = false)
	private PelakuUsahaData pelakuUsaha; 
	
	@Column(name="status_verifikasi")
	private Boolean statusVerifikasi;
	
	@Embedded
	private KontakData kontakPerusahaanData;
	
	@JoinColumn(name="kreator", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private OtoritasData kreator;
	
	@JoinColumn(name="verifikator", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private OtoritasData verifikator;
	
	@Column(name="tanggal_registrasi")
	private LocalDate tanggalRegistrasi;	
	
	private String npwp;
	
	@OneToMany(mappedBy="perusahaanData", fetch = FetchType.LAZY)
	private List<RegisterDokumenData> daftarRegisterDokumenData;	

	@OneToMany(mappedBy = "perusahaan", fetch = FetchType.LAZY)
	private List<OtoritasPerusahaanData> daftarAutorityPerusahaanData;
	
	public RegisterPerusahaanData() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public AlamatData getAlamatPerusahaanData() {
		return alamatPerusahaanData;
	}

	public void setAlamatPerusahaanData(AlamatData alamatPerusahaanData) {
		this.alamatPerusahaanData = alamatPerusahaanData;
	}

	public ModelPerizinanData getModelPerizinanData() {
		return modelPerizinanData;
	}

	public void setModelPerizinanData(ModelPerizinanData modelPerizinanData) {
		this.modelPerizinanData = modelPerizinanData;
	}

	public SkalaUsahaData getSkalaUsahaData() {
		return skalaUsahaData;
	}

	public void setSkalaUsahaData(SkalaUsahaData skalaUsahaData) {
		this.skalaUsahaData = skalaUsahaData;
	}

	public PelakuUsahaData getPelakuUsaha() {
		return pelakuUsaha;
	}

	public void setPelakuUsaha(PelakuUsahaData pelakuUsaha) {
		this.pelakuUsaha = pelakuUsaha;
	}

	public Boolean getStatusVerifikasi() {
		return statusVerifikasi;
	}

	public void setStatusVerifikasi(Boolean statusVerifikasi) {
		this.statusVerifikasi = statusVerifikasi;
	}

	public KontakData getKontakPerusahaanData() {
		return kontakPerusahaanData;
	}

	public void setKontakPerusahaanData(KontakData kontakPerusahaanData) {
		this.kontakPerusahaanData = kontakPerusahaanData;
	}

	public OtoritasData getKreator() {
		return kreator;
	}

	public void setKreator(OtoritasData kreator) {
		this.kreator = kreator;
	}

	public OtoritasData getVerifikator() {
		return verifikator;
	}

	public void setVerifikator(OtoritasData verifikator) {
		this.verifikator = verifikator;
	}

	public LocalDate getTanggalRegistrasi() {
		return tanggalRegistrasi;
	}

	public void setTanggalRegistrasi(LocalDate tanggalRegistrasi) {
		this.tanggalRegistrasi = tanggalRegistrasi;
	}

	public String getNpwp() {
		return npwp;
	}

	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	public List<RegisterDokumenData> getDaftarRegisterDokumenData() {
		return daftarRegisterDokumenData;
	}

	public void setDaftarRegisterDokumenData(List<RegisterDokumenData> daftarRegisterDokumenData) {
		this.daftarRegisterDokumenData = daftarRegisterDokumenData;
	}

	public List<OtoritasPerusahaanData> getDaftarAutorityPerusahaanData() {
		return daftarAutorityPerusahaanData;
	}

	public void setDaftarAutorityPerusahaanData(List<OtoritasPerusahaanData> daftarAutorityPerusahaanData) {
		this.daftarAutorityPerusahaanData = daftarAutorityPerusahaanData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}