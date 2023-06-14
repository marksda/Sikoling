package org.Sikoling.ejb.abstraction.entity.permohonan;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Autority;
import org.Sikoling.ejb.abstraction.entity.Pegawai;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.entity.log.StatusFlowLog;

public class RegisterPermohonanArahan extends RegisterPermohonan implements Serializable {
	
	private static final long serialVersionUID = -5728955961609566242L;
	private final JenisPermohonanSuratArahan jenisPermohonanSuratArahan;
	private final String uraianKegiatan;
	
	public RegisterPermohonanArahan(String id, KategoriPermohonan kategoriPermohonan, LocalDate tanggalRegistrasi,
			RegisterPerusahaan perusahaan, Autority pengurusPermohonan, StatusPengurusPermohonan statusPengurusPermohonan,
			Pegawai penanggungJawabPermohonan, PosisiTahapPemberkasan pengirimBerkas,
			PosisiTahapPemberkasan penerimaBerkas, StatusFlowLog statusFlowLog, List<RegisterDokumen> daftarDokumenSyarat, List<RegisterDokumen> daftarDokumenHasil,
			JenisPermohonanSuratArahan jenisPermohonanSuratArahan, String uraianKegiatan) {
		super(id, kategoriPermohonan, tanggalRegistrasi, perusahaan, pengurusPermohonan, statusPengurusPermohonan,
				penanggungJawabPermohonan, pengirimBerkas, penerimaBerkas, statusFlowLog, daftarDokumenSyarat, daftarDokumenHasil);
		this.jenisPermohonanSuratArahan = jenisPermohonanSuratArahan;
		this.uraianKegiatan = uraianKegiatan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JenisPermohonanSuratArahan getJenisPermohonanSuratArahan() {
		return jenisPermohonanSuratArahan;
	}

	public String getUraianKegiatan() {
		return uraianKegiatan;
	}

	@Override
	public int hashCode() {
		int hash = 1831;
		hash = 141 * hash + Objects.hashCode(this.getId());
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
        
        final RegisterPermohonanArahan other = (RegisterPermohonanArahan) obj;
        if ( !this.getId().equals(other.getId()) ) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "RegisterPermohonanSuratArahan{" + "id=" + this.getId() + "}";
	}
	
	

}
