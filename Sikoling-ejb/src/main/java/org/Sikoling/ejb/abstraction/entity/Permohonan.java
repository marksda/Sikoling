package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Permohonan implements Serializable {

	private static final long serialVersionUID = -2507712010024456804L;	
	private final PaketPermohonan jenisPaketPermohonan;
	private final String noPendaftaran;
	private final Date tanggalPendaftaran;
	private final SuratPermohonan suratPermohonan;
	private final BidangUsaha bidangUsaha;
	private final Wali wali;
	private final List<Produk> daftarProdukDLH;
	
	public Permohonan(String noPendaftaran, Date tanggalPendaftaran, SuratPermohonan suratPermohonan,
			BidangUsaha bidangUsaha, Wali wali, List<Produk> daftarProdukDLH, PaketPermohonan jenisPaketPermohonan) {
		super();
		this.noPendaftaran = noPendaftaran;
		this.tanggalPendaftaran = tanggalPendaftaran;
		this.suratPermohonan = suratPermohonan;
		this.bidangUsaha = bidangUsaha;
		this.wali = wali;
		this.daftarProdukDLH = daftarProdukDLH;
		this.jenisPaketPermohonan = jenisPaketPermohonan;
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

	public List<Produk> getDaftarProdukDLH() {
		return daftarProdukDLH;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public PaketPermohonan getJenisPaketPermohonan() {
		return jenisPaketPermohonan;
	}
	
}
