package org.Sikoling.ejb.abstraction.entity.permohonan;

import java.io.Serializable;	
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.entity.BidangUsaha;
import org.Sikoling.ejb.abstraction.entity.KapasitasSkalaUsaha;
import org.Sikoling.ejb.abstraction.entity.Pegawai;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli;
import org.Sikoling.ejb.abstraction.entity.log.StatusFlowLog;

public class RegisterPermohonanSPPL extends RegisterPermohonan implements Serializable {

	private static final long serialVersionUID = -2273950873696532803L;
	private final KapasitasSkalaUsaha kapasitasSkalaUsaha;
	private final BidangUsaha bidangUsaha;
	private final Kbli jenisUsaha; //kode kbli dan nama kbli ex: 20116 - Industri Kimia Dasar Organik 
	private final Person penanggungJawab;
	private final String namaUsaha;
	private final String kriteria;
	private final String izinYangDimiliki;
	private final String keteranganKegiatan;
	private final Alamat lokasiUsaha;
	
	public RegisterPermohonanSPPL(String id, KategoriPermohonan kategoriPermohonan, LocalDate tanggalRegistrasi,
			RegisterPerusahaan perusahaan, Otoritas pengurusPermohonan, StatuswaliPermohonan statusWaliPengurusPermohonan,
			Pegawai penanggungJawabPermohonan, PosisiTahapPemberkasan pengirimBerkas,
			PosisiTahapPemberkasan penerimaBerkas, StatusFlowLog statusFlowLog, List<RegisterDokumen> daftarDokumenSyarat, List<RegisterDokumen> daftarDokumenHasil,
			KapasitasSkalaUsaha kapasitasSkalaUsaha, BidangUsaha bidangUsaha, Kbli jenisUsaha,
			Person penanggungJawab, String namaUsaha, String kriteria, String izinYangDimiliki,
			String keteranganKegiatan, Alamat lokasiUsaha) {
		super(id, kategoriPermohonan, tanggalRegistrasi, perusahaan, pengurusPermohonan, statusWaliPengurusPermohonan,
				penanggungJawabPermohonan, pengirimBerkas, penerimaBerkas, statusFlowLog, daftarDokumenSyarat, daftarDokumenHasil);
		this.kapasitasSkalaUsaha = kapasitasSkalaUsaha;
		this.bidangUsaha = bidangUsaha;
		this.jenisUsaha = jenisUsaha;
		this.penanggungJawab = penanggungJawab;
		this.namaUsaha = namaUsaha;
		this.kriteria = kriteria;
		this.izinYangDimiliki = izinYangDimiliki;
		this.keteranganKegiatan = keteranganKegiatan;
		this.lokasiUsaha = lokasiUsaha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public KapasitasSkalaUsaha getKapasitasSkalaUsaha() {
		return kapasitasSkalaUsaha;
	}

	public BidangUsaha getBidangUsaha() {
		return bidangUsaha;
	}

	public Kbli getJenisUsaha() {
		return jenisUsaha;
	}

	public Person getPenanggungJawab() {
		return penanggungJawab;
	}

	public String getNamaUsaha() {
		return namaUsaha;
	}

	public String getKriteria() {
		return kriteria;
	}

	public String getIzinYangDimiliki() {
		return izinYangDimiliki;
	}

	public String getKeteranganKegiatan() {
		return keteranganKegiatan;
	}

	public Alamat getLokasiUsaha() {
		return lokasiUsaha;
	}
	
	@Override
	public int hashCode() {
		int hash = 117;
		hash = 13 * hash + Objects.hashCode(this.getId());
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
        
        final RegisterPermohonanSPPL other = (RegisterPermohonanSPPL) obj;
        
        if (!this.getId().equals(other.getId())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "RegisterSPPL{id=" 
				.concat(this.getId())
				.concat("namaUsah=")
				.concat(this.getNamaUsaha())
				.concat("}");
	}
	
}
