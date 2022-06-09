package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;

import org.Sikoling.ejb.main.repository.bentukusaha.BentukUsahaData;

import java.util.Date;


/**
 * The persistent class for the tbl_pemrakarsa database table.
 * 
 */
@Entity
@Table(name="tbl_pemrakarsa")
@NamedQuery(name="PemrakarsaData.findAll", query="SELECT p FROM PemrakarsaData p")
public class PemrakarsaData implements Serializable {
	private static final long serialVersionUID = 5667247303637293789L;

	@Id
	private String id;

	@Column(name="alamat_email")
	private String alamatEmail;

	@Column(name="bentuk_usaha")
	@JoinColumn(name = "bentuk_usaha", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private BentukUsahaData bentukUsaha;

	private String nama;

	@Column(name="nama_notaris")
	private String namaNotaris;

	@Column(name="pj_pemrakarsa")
	@JoinColumn(name = "pj_pemrakarsa", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private RelasiPenanggungJawabData penanggungJawab;

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

	public PemrakarsaData() {
	}		

	public PemrakarsaData(String id, String alamatEmail, BentukUsahaData bentukUsaha, String nama, String namaNotaris,
			RelasiPenanggungJawabData penanggungJawab, String noNibOss, String noNpwp, Date tanggalNotaris, Date tanggalOss) {
		super();
		this.id = id;
		this.alamatEmail = alamatEmail;
		this.bentukUsaha = bentukUsaha;
		this.nama = nama;
		this.namaNotaris = namaNotaris;
		this.penanggungJawab = penanggungJawab;
		this.noNibOss = noNibOss;
		this.noNpwp = noNpwp;
		this.tanggalNotaris = tanggalNotaris;
		this.tanggalOss = tanggalOss;
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

	public RelasiPenanggungJawabData getPenanggungJawab() {
		return this.penanggungJawab;
	}

	public void setPenanggungJawab(RelasiPenanggungJawabData penanggungJawab) {
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
	
}