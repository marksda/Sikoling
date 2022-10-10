package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.Serializable;

import jakarta.persistence.*;

import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.DetailPelakuUsahaData;
import org.Sikoling.ejb.main.repository.pelakuusaha.JenisPelakuUsahaData;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;


@Entity
@Table(name="master.tbl_perusahaan")
@NamedQueries({
	@NamedQuery(name="PerusahaanData.findAll", query="SELECT p FROM PerusahaanData p"),
	@NamedQuery(name="PerusahaanData.findByQueryNama", query="SELECT p FROM PerusahaanData p WHERE p.nama LIKE :nama"),
	@NamedQuery(name="PerusahaanData.findByCreator", query="SELECT p FROM PerusahaanData p WHERE p.creator.id = :idCreator"),
	@NamedQuery(name="PerusahaanData.findByCreatorAndNama", query="SELECT p FROM PerusahaanData p WHERE p.nama LIKE :nama AND p.creator.id = :idCreator")
})
public class PerusahaanData implements Serializable {
	private static final long serialVersionUID = 5667247303637293789L;

	@Id
	private String id;
	
	private String nama;
	
	@JoinColumn(name="model_perizinan", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private ModelPerizinanData modelPerizinanData;
	
	@JoinColumn(name="skala_usaha", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private SkalaUsahaData SkalaUsaha;
	
	@JoinColumn(name="jenis_pelaku_usaha", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private JenisPelakuUsahaData jenisPelakuUsahaData; 
	
	@JoinColumn(name="detail_pelaku_usaha", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private DetailPelakuUsahaData detailPelakuUsahaData; 
	
	@Embedded
	private AlamatPerusahaanData alamatPerusahaanData;

	@Embedded
	private KontakData kontakPerusahaanData;
	
	@Column(columnDefinition = "json")
	private String dokumen;
	
	public PerusahaanData() {
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

	
	public JenisPelakuUsahaData getJenisPelakuUsahaData() {
		return jenisPelakuUsahaData;
	}

	
	public void setJenisPelakuUsahaData(JenisPelakuUsahaData jenisPelakuUsahaData) {
		this.jenisPelakuUsahaData = jenisPelakuUsahaData;
	}

	
	public DetailPelakuUsahaData getDetailPelakuUsahaData() {
		return detailPelakuUsahaData;
	}

	
	public void setDetailPelakuUsahaData(DetailPelakuUsahaData detailPelakuUsahaData) {
		this.detailPelakuUsahaData = detailPelakuUsahaData;
	}

	
	public AlamatPerusahaanData getAlamatPerusahaanData() {
		return alamatPerusahaanData;
	}

	
	public void setAlamatPerusahaanData(AlamatPerusahaanData alamatPerusahaanData) {
		this.alamatPerusahaanData = alamatPerusahaanData;
	}

	
	public KontakData getKontakPerusahaanData() {
		return kontakPerusahaanData;
	}

	
	public void setKontakPerusahaanData(KontakData kontakPerusahaanData) {
		this.kontakPerusahaanData = kontakPerusahaanData;
	}

	
	public String getDokumen() {
		return dokumen;
	}

	
	public void setDokumen(String dokumen) {
		this.dokumen = dokumen;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}