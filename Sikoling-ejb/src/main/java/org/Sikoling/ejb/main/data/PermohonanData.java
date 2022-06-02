package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the tbl_permohonan database table.
 * 
 */
@Entity
@Table(name="tbl_permohonan")
@NamedQuery(name="TblPermohonan.findAll", query="SELECT t FROM TblPermohonan t")
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

	private Timestamp tanggal;

	@Column(name="wali")
	@JoinColumn(name = "wali", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private UserData wali;

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

}