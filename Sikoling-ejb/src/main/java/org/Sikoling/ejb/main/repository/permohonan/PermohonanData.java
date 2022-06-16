package org.Sikoling.ejb.main.repository.permohonan;

import java.io.Serializable;
import jakarta.persistence.*;

import org.Sikoling.ejb.main.data.StatusWaliData;
import org.Sikoling.ejb.main.repository.bidangusaha.BidangUsahaData;
import org.Sikoling.ejb.main.repository.pemrakarsa.PemrakarsaData;
import org.Sikoling.ejb.main.repository.penanggungjawab.PenanggungJawabData;
import org.Sikoling.ejb.main.repository.produk.ProdukData;
import org.Sikoling.ejb.main.repository.user.UserData;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the tbl_permohonan database table.
 * 
 */
@Entity
@Table(name="transaksi.tbl_permohonan")
@NamedQuery(name="PermohonanData.findAll", query="SELECT t FROM PermohonanData t")
public class PermohonanData implements Serializable {
	private static final long serialVersionUID = -8440556805186981704L;

	@Id
	private String id;

	@Column(name="bidang_usaha")
	@JoinColumn(name = "bidang_usaha", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private BidangUsahaData bidangUsaha;

	@Column(name="nomor_pendaftaran")
	private String nomorPendaftaran;

	@Column(name="pemrakarsa")
	@JoinColumn(name = "pemrakarsa", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private PemrakarsaData pemrakarsa;

	@Column(name="produk")
	@JoinColumn(name = "produk", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private ProdukData produk;

	@Column(name="status_wali")
	@JoinColumn(name = "status_wali", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private StatusWaliData statusWali;

	@Column(name="tanggal")
	private Timestamp tanggal;

	@Column(name="wali")
	@JoinColumn(name = "wali", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private UserData wali;
	
	@Temporal(TemporalType.DATE)
	@Column(name="tanggal_surat_permohonan")
	private Date tangalSuratPermohonan;
	
	@Column(name="nomor_surat_permohonan")
	private String nomorSuratPermohonan;

	@Column(name="penanggung_jawab")
	@JoinColumn(name = "penanggung_jawab", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
	private PenanggungJawabData penanggungJawab;
	
	public Date getTangalSuratPermohonan() {
		return tangalSuratPermohonan;
	}

	public void setTangalSuratPermohonan(Date tangalSuratPermohonan) {
		this.tangalSuratPermohonan = tangalSuratPermohonan;
	}

	public String getNomorSuratPermohonan() {
		return nomorSuratPermohonan;
	}

	public void setNomorSuratPermohonan(String nomorSuratPermohonan) {
		this.nomorSuratPermohonan = nomorSuratPermohonan;
	}

	public PermohonanData() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BidangUsahaData getBidangUsaha() {
		return this.bidangUsaha;
	}

	public void setBidangUsaha(BidangUsahaData bidangUsaha) {
		this.bidangUsaha = bidangUsaha;
	}

	public String getNomorPendaftaran() {
		return this.nomorPendaftaran;
	}

	public void setNomorPendaftaran(String nomorPendaftaran) {
		this.nomorPendaftaran = nomorPendaftaran;
	}

	public PemrakarsaData getPemrakarsa() {
		return this.pemrakarsa;
	}

	public void setPemrakarsa(PemrakarsaData pemrakarsa) {
		this.pemrakarsa = pemrakarsa;
	}

	public ProdukData getProduk() {
		return this.produk;
	}

	public void setProduk(ProdukData produk) {
		this.produk = produk;
	}

	public StatusWaliData getStatusWali() {
		return this.statusWali;
	}

	public void setStatusWali(StatusWaliData statusWali) {
		this.statusWali = statusWali;
	}

	public Timestamp getTanggal() {
		return this.tanggal;
	}

	public void setTanggal(Timestamp tanggal) {
		this.tanggal = tanggal;
	}

	public UserData getWali() {
		return this.wali;
	}

	public void setWali(UserData wali) {
		this.wali = wali;
	}
	
	public PenanggungJawabData getPenanggungJawab() {
		return penanggungJawab;
	}
	
	public void setPenanggungJawab(PenanggungJawabData penanggungJawab) {
		this.penanggungJawab = penanggungJawab;
	}

}