package org.Sikoling.ejb.main.repository.permohonan;

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
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="bidang_usaha")
	private Integer bidangUsaha;

	@Column(name="nomor_pendaftaran")
	private String nomorPendaftaran;

	private String pemrakarsa;

	private String produk;

	@Column(name="status_wali")
	private String statusWali;

	private Timestamp tanggal;

	private String wali;

	public PermohonanData() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getBidangUsaha() {
		return this.bidangUsaha;
	}

	public void setBidangUsaha(Integer bidangUsaha) {
		this.bidangUsaha = bidangUsaha;
	}

	public String getNomorPendaftaran() {
		return this.nomorPendaftaran;
	}

	public void setNomorPendaftaran(String nomorPendaftaran) {
		this.nomorPendaftaran = nomorPendaftaran;
	}

	public String getPemrakarsa() {
		return this.pemrakarsa;
	}

	public void setPemrakarsa(String pemrakarsa) {
		this.pemrakarsa = pemrakarsa;
	}

	public String getProduk() {
		return this.produk;
	}

	public void setProduk(String produk) {
		this.produk = produk;
	}

	public String getStatusWali() {
		return this.statusWali;
	}

	public void setStatusWali(String statusWali) {
		this.statusWali = statusWali;
	}

	public Timestamp getTanggal() {
		return this.tanggal;
	}

	public void setTanggal(Timestamp tanggal) {
		this.tanggal = tanggal;
	}

	public String getWali() {
		return this.wali;
	}

	public void setWali(String wali) {
		this.wali = wali;
	}

}