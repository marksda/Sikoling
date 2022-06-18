package org.Sikoling.ejb.main.repository.pemrakarsa;

import java.io.Serializable;
import jakarta.persistence.*;

import org.Sikoling.ejb.main.repository.bentukusaha.BentukUsahaData;
import org.Sikoling.ejb.main.repository.penanggungjawab.PenanggungJawabData;
import org.Sikoling.ejb.main.repository.user.UserData;

import java.util.Date;
import java.util.List;


@Entity
@Table(name="master.tbl_pemrakarsa")
@NamedQueries({
@NamedQuery(name="PemrakarsaData.findAll", query="SELECT p FROM PemrakarsaData p"),
@NamedQuery(name="PemrakarsaData.findByQueryNama", query="SELECT p FROM PemrakarsaData p WHERE p.nama ILIKE :nama"),
@NamedQuery(name="PemrakarsaData.findByCreator", query="SELECT p FROM PemrakarsaData p WHERE p.creator.id = :idCreator"),
@NamedQuery(name="PemrakarsaData.findByCreatorAndNama", query="SELECT p FROM PemrakarsaData p WHERE p.nama ILIKE :nama AND p.creator.id = :idCreator")
})
public class PemrakarsaData implements Serializable {
	private static final long serialVersionUID = 5667247303637293789L;

	@Id
	@Column(name="id")
	private String id;

	@Column(name="alamat_email")
	private String alamatEmail;

	@JoinColumn(name = "bentuk_usaha", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
	private BentukUsahaData bentukUsaha;

	@Column(name="nama")
	private String nama;

	@Column(name="nama_notaris")
	private String namaNotaris;

	@JoinColumn(name = "penanggung_jawab", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private PenanggungJawabData penanggungJawab;

	@Column(name="no_nib_oss")
	private String noNibOss;

	@Column(name="no_npwp")
	private String noNpwp;

	@Temporal(TemporalType.DATE)
	@Column(name="tanggal_notaris")
	private Date tanggalNotaris;

	@Temporal(TemporalType.DATE)
	@Column(name="tanggal_oss")
	private Date tanggalOss;
	
	@Column(name="telepone")
	private String telepone;
	
	@Column(name="fax")
	private String fax;

	@JoinColumn(name = "creator", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private UserData creator;	
	
	@ElementCollection
	private List<AlamatPemrakarsaData> alamatPemrakarsaDatas;
			
	public PemrakarsaData() {
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getAlamatEmail() {
		return this.alamatEmail;
	}
	
	public void setAlamatEmail(String alamatEmail) {
		this.alamatEmail = alamatEmail;
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
	
	public String getNamaNotaris() {
		return this.namaNotaris;
	}
	
	public void setNamaNotaris(String namaNotaris) {
		this.namaNotaris = namaNotaris;
	}	

	public PenanggungJawabData getPenanggungJawab() {
		return this.penanggungJawab;
	}
	
	public void setPenanggungJawab(PenanggungJawabData penanggungJawab) {
		this.penanggungJawab = penanggungJawab;
	}
	
	public String getNoNibOss() {
		return this.noNibOss;
	}
	
	public void setNoNibOss(String noNibOss) {
		this.noNibOss = noNibOss;
	}
	
	public String getNoNpwp() {
		return this.noNpwp;
	}
	
	public void setNoNpwp(String noNpwp) {
		this.noNpwp = noNpwp;
	}
	
	public Date getTanggalNotaris() {
		return this.tanggalNotaris;
	}
	
	public void setTanggalNotaris(Date tanggalNotaris) {
		this.tanggalNotaris = tanggalNotaris;
	}
	
	public Date getTanggalOss() {
		return this.tanggalOss;
	}
	
	public void setTanggalOss(Date tanggalOss) {
		this.tanggalOss = tanggalOss;
	}
	
	public String getTelepone() {
		return telepone;
	}
	
	public void setTelepone(String telepone) {
		this.telepone = telepone;
	}
	
	public String getFax() {
		return fax;
	}
	
	public void setFax(String fax) {
		this.fax = fax;
	}
		
	public UserData getCreator() {
		return creator;
	}
		
	public void setCreator(UserData creator) {
		this.creator = creator;
	}

	public List<AlamatPemrakarsaData> getAlamatPemrakarsaDatas() {
		return alamatPemrakarsaDatas;
	}

	public void setAlamatPemrakarsaDatas(List<AlamatPemrakarsaData> alamatPemrakarsaDatas) {
		this.alamatPemrakarsaDatas = alamatPemrakarsaDatas;
	}
	
	
	
}