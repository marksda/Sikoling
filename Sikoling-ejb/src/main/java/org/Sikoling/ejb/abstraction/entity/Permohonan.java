package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;

public class Permohonan implements Serializable {

	private static final long serialVersionUID = -2507712010024456804L;	
	private final String noPendaftaran;
	private final Date tanggalPendaftaran;
	private final SuratPermohonan suratPermohonan;
	private final BidangUsaha bidangUsaha;
	private final Wali wali;
	private final Produk produk;
	
	public Permohonan(String noPendaftaran, Date tanggalPendaftaran, SuratPermohonan suratPermohonan,
			BidangUsaha bidangUsaha, Wali wali, Produk produk) {
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

	public Date getTanggalPendaftaran() {
		return tanggalPendaftaran;
	}

	public SuratPermohonan getSuratPermohonan() {
		return suratPermohonan;
	}

	public BidangUsaha getBidangUsaha() {
		return bidangUsaha;
	}

	public Wali getWali() {
		return wali;
	}

	public Produk getProduk() {
		return produk;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
