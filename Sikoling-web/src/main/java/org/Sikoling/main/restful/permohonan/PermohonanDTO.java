package org.Sikoling.main.restful.permohonan;

import java.io.Serializable;
import java.util.Date;

import org.Sikoling.ejb.abstraction.entity.Permohonan;
import org.Sikoling.main.restful.bidangUsaha.BidangUsahaDTO;
import org.Sikoling.main.restful.produk.ProdukDTO;

public class PermohonanDTO implements Serializable {

	private static final long serialVersionUID = -1560638940620295624L;
	private String noPendaftaran;
	private Date tanggalPendaftaran;
	private SuratPermohonanDTO suratPermohonan;
	private BidangUsahaDTO bidangUsaha;
	private WaliDTO wali;
	private ProdukDTO produk;
	
	public PermohonanDTO() {
		
	}
	
	public PermohonanDTO(String noPendaftaran, Date tanggalPendaftaran, SuratPermohonanDTO suratPermohonan,
			BidangUsahaDTO bidangUsaha, WaliDTO wali, ProdukDTO produk) {
		super();
		this.noPendaftaran = noPendaftaran;
		this.tanggalPendaftaran = tanggalPendaftaran;
		this.suratPermohonan = suratPermohonan;
		this.bidangUsaha = bidangUsaha;
		this.wali = wali;
		this.produk = produk;
	}
	
	public PermohonanDTO(Permohonan permohonan) {
		this.noPendaftaran = permohonan.getNoPendaftaran();
		this.tanggalPendaftaran = permohonan.getTanggalPendaftaran();
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

	public SuratPermohonanDTO getSuratPermohonan() {
		return suratPermohonan;
	}

	public void setSuratPermohonan(SuratPermohonanDTO suratPermohonan) {
		this.suratPermohonan = suratPermohonan;
	}

	public BidangUsahaDTO getBidangUsaha() {
		return bidangUsaha;
	}

	public void setBidangUsaha(BidangUsahaDTO bidangUsaha) {
		this.bidangUsaha = bidangUsaha;
	}

	public WaliDTO getWali() {
		return wali;
	}

	public void setWali(WaliDTO wali) {
		this.wali = wali;
	}

	public ProdukDTO getProduk() {
		return produk;
	}

	public void setProduk(ProdukDTO produk) {
		this.produk = produk;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
