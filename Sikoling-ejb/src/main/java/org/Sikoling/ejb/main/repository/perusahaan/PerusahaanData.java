package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.Serializable;

import jakarta.persistence.*;

import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;


@Entity
@Table(name="master.tbl_perusahaan")
@NamedQueries({
	@NamedQuery(name="PerusahaanData.findAll", query="SELECT p FROM PerusahaanData p"),
	@NamedQuery(name="PerusahaanData.findByQueryNama", query="SELECT p FROM PerusahaanData p WHERE p.nama LIKE :nama")
})
public class PerusahaanData implements Serializable {
	private static final long serialVersionUID = 5667247303637293789L;

	@Id
	private String id;
	
	private String nama;
	
	@Embedded
	private AlamatPerusahaanData alamatPerusahaanData;
	
	@JoinColumn(name="model_perizinan", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private ModelPerizinanData modelPerizinanData;
	
	@JoinColumn(name="skala_usaha", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private SkalaUsahaData SkalaUsaha;
		
	@JoinColumn(name="pelaku_usaha", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private PelakuUsahaData pelakuUsahaData; 
	
	@Column(name="status_verifikasi")
	private boolean statusVerifikasi;
	
	@Embedded
	private KontakPerusahaanData kontakPerusahaanData;
		
	public PerusahaanData() {
	}
	
	public boolean getStatusVerifikasi() {
		return statusVerifikasi;
	}

	public void setStatusVerifikasi(boolean statusVerifikasi) {
		this.statusVerifikasi = statusVerifikasi;
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
	
	public ModelPerizinanData getModelPerizinanData() {
		return modelPerizinanData;
	}
	
	public void setModelPerizinanData(ModelPerizinanData modelPerizinanData) {
		this.modelPerizinanData = modelPerizinanData;
	}
	
	public SkalaUsahaData getSkalaUsaha() {
		return SkalaUsaha;
	}
	
	public void setSkalaUsaha(SkalaUsahaData skalaUsaha) {
		SkalaUsaha = skalaUsaha;
	}
	
	public PelakuUsahaData getPelakuUsahaData() {
		return pelakuUsahaData;
	}
	
	public void setPelakuUsahaData(PelakuUsahaData pelakuUsahaData) {
		this.pelakuUsahaData = pelakuUsahaData;
	}
	
	public AlamatPerusahaanData getAlamatPerusahaanData() {
		return alamatPerusahaanData;
	}
	
	public void setAlamatPerusahaanData(AlamatPerusahaanData alamatPerusahaanData) {
		this.alamatPerusahaanData = alamatPerusahaanData;
	}
	
	public KontakPerusahaanData getKontakPerusahaanData() {
		return kontakPerusahaanData;
	}
	
	public void setKontakPerusahaanData(KontakPerusahaanData kontakPerusahaanData) {
		this.kontakPerusahaanData = kontakPerusahaanData;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}