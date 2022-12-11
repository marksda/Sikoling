package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

import org.Sikoling.ejb.main.repository.dokumen.RegisterDokumenData;
import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.person.PersonData;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;


@Entity
@Table(name="master.tbl_perusahaan")
@NamedQueries({
	@NamedQuery(name="RegisterPerusahaanData.findAll", query="SELECT p FROM RegisterPerusahaanData p"),
	@NamedQuery(name="RegisterPerusahaanData.findByQueryNama", query="SELECT p FROM RegisterPerusahaanData p WHERE p.nama LIKE :nama"),
	@NamedQuery(name="RegisterPerusahaanData.findByIdKreator", query="SELECT p FROM RegisterPerusahaanData p WHERE p.kreator.id = :idKreator")
})
public class RegisterPerusahaanData implements Serializable {
	private static final long serialVersionUID = 5667247303637293789L;

	@Id
	private String id;
	
	private String nama;
	
	@Embedded
	private AlamatPerusahaanData alamatPerusahaanData;
	
	@JoinColumn(name="model_perizinan", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne(optional = false)
	private ModelPerizinanData modelPerizinanData;
	
	@JoinColumn(name="skala_usaha", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne(optional = false)
	private SkalaUsahaData skalaUsahaData;
		
	@JoinColumn(name="pelaku_usaha", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne(optional = false)
	private PelakuUsahaData pelakuUsahaData; 
	
	@Column(name="status_verifikasi")
	private boolean statusVerifikasi;
	
	@Embedded
	private KontakPerusahaanData kontakPerusahaanData;
	
	@JoinColumn(name="kreator", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private PersonData kreator;
	
	@JoinColumn(name="verifikator", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private PersonData verifikator;
	
	@Column(name="tanggal_registrasi")
	private LocalDate tanggalRegistrasi;	
	
	@OneToMany(mappedBy="perusahaanData")
	private List<RegisterDokumenData> daftarRegisterDokumenData;	

	@OneToMany(mappedBy = "perusahaan", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<PersonPerusahaanData> daftarPersonPerusahaanData;
	
	public RegisterPerusahaanData() {
	}
	
	public PersonData getVerifikator() {
		return verifikator;
	}

	public void setVerifikator(PersonData verifikator) {
		this.verifikator = verifikator;
	}

	public LocalDate getTanggalRegistrasi() {
		return tanggalRegistrasi;
	}
	
	public void setTanggalRegistrasi(LocalDate tanggalRegistrasi) {
		this.tanggalRegistrasi = tanggalRegistrasi;
	}

	public PersonData getKreator() {
		return kreator;
	}

	public void setKreator(PersonData kreator) {
		this.kreator = kreator;
	}

	public List<PersonPerusahaanData> getDaftarPersonPerusahaanData() {
		return daftarPersonPerusahaanData;
	}

	public void setDaftarPersonPerusahaanData(List<PersonPerusahaanData> daftarPersonPerusahaanData) {
		this.daftarPersonPerusahaanData = daftarPersonPerusahaanData;
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

	public AlamatPerusahaanData getAlamatPerusahaanData() {
		return alamatPerusahaanData;
	}

	public void setAlamatPerusahaanData(AlamatPerusahaanData alamatPerusahaanData) {
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

	public PelakuUsahaData getPelakuUsahaData() {
		return pelakuUsahaData;
	}

	public void setPelakuUsahaData(PelakuUsahaData pelakuUsahaData) {
		this.pelakuUsahaData = pelakuUsahaData;
	}

	public boolean isStatusVerifikasi() {
		return statusVerifikasi;
	}

	public void setStatusVerifikasi(boolean statusVerifikasi) {
		this.statusVerifikasi = statusVerifikasi;
	}

	public KontakPerusahaanData getKontakPerusahaanData() {
		return kontakPerusahaanData;
	}

	public void setKontakPerusahaanData(KontakPerusahaanData kontakPerusahaanData) {
		this.kontakPerusahaanData = kontakPerusahaanData;
	}

	public List<RegisterDokumenData> getDaftarRegisterDokumenData() {
		return daftarRegisterDokumenData;
	}

	public void setDaftarRegisterDokumenData(List<RegisterDokumenData> daftarRegisterDokumenData) {
		this.daftarRegisterDokumenData = daftarRegisterDokumenData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}