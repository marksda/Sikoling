package org.Sikoling.ejb.abstraction.entity.permohonan;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.BatasLokasi;
import org.Sikoling.ejb.abstraction.entity.BidangUsaha;
import org.Sikoling.ejb.abstraction.entity.KordinatGeografis;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.entity.StatusWali;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;


public class RegisterPermohonanUKLUPL extends RegisterPermohonan implements Serializable {	
	
	private static final long serialVersionUID = -2043357764984768483L;
	private final StatusPermohonanUKLUPL status;
	private final BidangUsaha bidangUsaha;
	private final String namaUsaha;
	private final Alamat lokasiUsaha;
	private final int jumlahTenagaKerjaPria;
	private final int jumlahTenagaKerjaWanita;
	private final Double luasTanah;
	private final KordinatGeografis kordinatGeografis;
	private final BatasLokasi batasLokasi;
	
	public RegisterPermohonanUKLUPL(String id, KategoriPermohonan kategoriPermohonan, LocalDate tanggalRegistrasi,
			RegisterPerusahaan perusahaan, Authority pengurusPermohonan, StatusWali statusWaliPengurusPermohonan,
			PosisiTahapPemberkasan posisiBerkas, List<Dokumen> daftarDokumenSyarat, List<Dokumen> daftarDokumenHasil,
			StatusPermohonanUKLUPL status, BidangUsaha bidangUsaha, String namaUsaha, Alamat lokasiUsaha,
			int jumlahTenagaKerjaPria, int jumlahTenagaKerjaWanita, Double luasTanah,
			KordinatGeografis kordinatGeografis, BatasLokasi batasLokasi) {
		super(id, kategoriPermohonan, tanggalRegistrasi, perusahaan, pengurusPermohonan, statusWaliPengurusPermohonan,
				posisiBerkas, daftarDokumenSyarat, daftarDokumenHasil);
		this.status = status;
		this.bidangUsaha = bidangUsaha;
		this.namaUsaha = namaUsaha;
		this.lokasiUsaha = lokasiUsaha;
		this.jumlahTenagaKerjaPria = jumlahTenagaKerjaPria;
		this.jumlahTenagaKerjaWanita = jumlahTenagaKerjaWanita;
		this.luasTanah = luasTanah;
		this.kordinatGeografis = kordinatGeografis;
		this.batasLokasi = batasLokasi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public StatusPermohonanUKLUPL getStatus() {
		return status;
	}

	public BidangUsaha getBidangUsaha() {
		return bidangUsaha;
	}

	public String getNamaUsaha() {
		return namaUsaha;
	}

	public Alamat getLokasiUsaha() {
		return lokasiUsaha;
	}

	public int getJumlahTenagaKerjaPria() {
		return jumlahTenagaKerjaPria;
	}

	public int getJumlahTenagaKerjaWanita() {
		return jumlahTenagaKerjaWanita;
	}

	public Double getLuasTanah() {
		return luasTanah;
	}

	public KordinatGeografis getKordinatGeografis() {
		return kordinatGeografis;
	}

	public BatasLokasi getBatasLokasi() {
		return batasLokasi;
	}
	
	@Override
	public int hashCode() {
		int hash = 1017;
		hash = 131 * hash + Objects.hashCode(this.getId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
		
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final RegisterPermohonanUKLUPL other = (RegisterPermohonanUKLUPL) obj;
        
        if (!this.getId().equals(other.getId())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "RegisterPermohonanUKLUPL{id=" 
				.concat(this.getId())
				.concat("namaUsah=")
				.concat(this.getNamaUsaha())
				.concat("}");
	}	

}
