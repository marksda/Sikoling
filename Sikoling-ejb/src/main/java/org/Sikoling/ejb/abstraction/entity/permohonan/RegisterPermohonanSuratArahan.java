package org.Sikoling.ejb.abstraction.entity.permohonan;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.Pegawai;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;

public class RegisterPermohonanSuratArahan extends RegisterPermohonan implements Serializable {
	
	private static final long serialVersionUID = -5728955961609566242L;
	private final JenisPermohonanSuratArahan jenisPermohonanSuratArahan;
	private final String uraianKegiatan;
	
	public RegisterPermohonanSuratArahan(String id, KategoriPermohonan kategoriPermohonan, LocalDate tanggalRegistrasi,
			RegisterPerusahaan perusahaan, Authority pengurusPermohonan, StatusWali statusWaliPengurusPermohonan,
			Pegawai penanggungJawabPermohonan, PosisiTahapPemberkasan posisiBerkas,
			List<RegisterDokumen> daftarDokumenSyarat, List<RegisterDokumen> daftarDokumenHasil,
			JenisPermohonanSuratArahan jenisPermohonanSuratArahan, String uraianKegiatan) {
		super(id, kategoriPermohonan, tanggalRegistrasi, perusahaan, pengurusPermohonan, statusWaliPengurusPermohonan,
				penanggungJawabPermohonan, posisiBerkas, daftarDokumenSyarat, daftarDokumenHasil);
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
        
        final RegisterPermohonanSuratArahan other = (RegisterPermohonanSuratArahan) obj;
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
