package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.Serializable;
import jakarta.persistence.*;
import org.Sikoling.ejb.main.repository.bentukusaha.BentukUsahaData;
import org.Sikoling.ejb.main.repository.jenispelakuusaha.JenisPelakuUsahaData;
import org.Sikoling.ejb.main.repository.penanggungjawab.PenanggungJawabData;
import org.Sikoling.ejb.main.repository.user.UserData;


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
	
	@JoinColumn(name="jenis_pelaku_usaha", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private JenisPelakuUsahaData jenisPelakuUsahaData; 

	@JoinColumn(name = "bentuk_usaha", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
	private BentukUsahaData bentukUsaha;
	
	@Embedded
	private AktaPemrakarsaData aktaPemrakarsaData;
	
	@Embedded
	private AlamatPemrakarsaData alamatPemrakarsaData;

	@Embedded
	private KontakData kontakPemrakarsaData;
	
	@Embedded
	private OSSData ossData;
	
	private String nama;
	
	@Column(name="no_npwp")
	private String noNpwp;

	@JoinColumn(name = "penanggung_jawab", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private PenanggungJawabData penanggungJawab;	

	@JoinColumn(name = "creator", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private UserData creator;	
				
	public PerusahaanData() {
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public BentukUsahaData getBentukUsaha() {
		return this.bentukUsaha;
	}
	
	public void setBentukUsaha(BentukUsahaData bentukUsaha) {
		this.bentukUsaha = bentukUsaha;
	}
	
	public String getNama() {
		return this.nama;
	}
	
	public void setNama(String nama) {
		this.nama = nama;
	}
	
	public PenanggungJawabData getPenanggungJawab() {
		return this.penanggungJawab;
	}
	
	public void setPenanggungJawab(PenanggungJawabData penanggungJawab) {
		this.penanggungJawab = penanggungJawab;
	}
	
	public String getNoNpwp() {
		return this.noNpwp;
	}
	
	public void setNoNpwp(String noNpwp) {
		this.noNpwp = noNpwp;
	}
		
	public UserData getCreator() {
		return creator;
	}
		
	public void setCreator(UserData creator) {
		this.creator = creator;
	}

	public AlamatPemrakarsaData getAlamatPemrakarsaData() {
		return alamatPemrakarsaData;
	}

	public void setAlamatPemrakarsaData(AlamatPemrakarsaData alamatPemrakarsaData) {
		this.alamatPemrakarsaData = alamatPemrakarsaData;
	}

	public AktaPemrakarsaData getAktaPemrakarsaData() {
		return aktaPemrakarsaData;
	}

	public void setAktaPemrakarsaData(AktaPemrakarsaData aktaPemrakarsaData) {
		this.aktaPemrakarsaData = aktaPemrakarsaData;
	}

	
	public KontakData getKontakPemrakarsaData() {
		return kontakPemrakarsaData;
	}
	

	public void setKontakPemrakarsaData(KontakData kontakPemrakarsaData) {
		this.kontakPemrakarsaData = kontakPemrakarsaData;
	}

	
	public OSSData getOssData() {
		return ossData;
	}

	
	public void setOssData(OSSData ossData) {
		this.ossData = ossData;
	}
		
	
}