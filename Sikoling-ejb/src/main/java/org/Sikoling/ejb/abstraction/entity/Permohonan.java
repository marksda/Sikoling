package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;

public class Permohonan implements Serializable {

	private static final long serialVersionUID = -2507712010024456804L;	
	private String noPendaftaran;
	private Date tanggalPendaftaran;
	private SuratPermohonan suratPermohonan;
	private BidangUsaha bidangUsaha;
	private WaliPemohon wali;
	private Produk produk;
	//dasdasd
	
	public Permohonan(String noPendaftaran, Date tanggalPendaftaran, SuratPermohonan suratPermohonan,
			BidangUsaha bidangUsaha, WaliPemohon wali, Produk produk) {
		super();
		this.noPendaftaran = noPendaftaran;
		this.tanggalPendaftaran = tanggalPendaftaran;
		this.suratPermohonan = suratPermohonan;
		this.bidangUsaha = bidangUsaha;
		this.wali = wali;
		this.produk = produk;
	}

	public String getNoPendaftaran() {
		return noPendaftaran;
	}

	public void setNoPendaftaran(String noPendaftaran) {
		this.noPendaftaran = noPendaftaran;
	}

	public Date getTanggalPendaftaran() {
		return tanggalPendaftaran;
	}

	public void setTanggalPendaftaran(Date tanggalPendaftaran) {
		this.tanggalPendaftaran = tanggalPendaftaran;
	}

	public SuratPermohonan getSuratPermohonan() {
		return suratPermohonan;
	}

	public void setSuratPermohonan(SuratPermohonan suratPermohonan) {
		this.suratPermohonan = suratPermohonan;
	}

	public BidangUsaha getBidangUsaha() {
		return bidangUsaha;
	}

	public void setBidangUsaha(BidangUsaha bidangUsaha) {
		this.bidangUsaha = bidangUsaha;
	}

	public WaliPemohon getWali() {
		return wali;
	}

	public void setWali(WaliPemohon wali) {
		this.wali = wali;
	}

	public Produk getProduk() {
		return produk;
	}

	public void setProduk(Produk produk) {
		this.produk = produk;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
