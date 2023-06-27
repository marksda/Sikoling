package org.Sikoling.main.restful.permohonan;

import java.io.Serializable;
import java.util.Objects;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonanArahan;
import org.Sikoling.main.restful.dokumen.RegisterDokumenDTO;
import org.Sikoling.main.restful.log.StatusFlowLogDTO;
import org.Sikoling.main.restful.otoritas.OtoritasDTO;
import org.Sikoling.main.restful.pegawai.PegawaiDTO;
import org.Sikoling.main.restful.perusahaan.RegisterPerusahaanDTO;

public class RegisterPermohonanArahanDTO extends RegisterPermohonanDTO implements Serializable {

	private static final long serialVersionUID = -150450427979362608L;
	private KategoriPermohonanSuratArahanDTO kategoriPermohonanSuratArahan;
	private String uraianKegiatan;
	
	public RegisterPermohonanArahanDTO() {
	}
	
	public RegisterPermohonanArahanDTO(RegisterPermohonanArahan t) {
		if(t != null) {
			this.setId(t.getId());
			this.setKategoriPermohonan(t.getKategoriPermohonan() != null ?	new KategoriPermohonanDTO(t.getKategoriPermohonan()):null);
			this.setTanggalRegistrasi(t.getTanggalRegistrasi());			
			this.setRegisterPerusahaan(t.getPerusahaan() != null ? new RegisterPerusahaanDTO(t.getPerusahaan()):null);
			this.setPengurusPermohonan(t.getPengurusPermohonan() != null ? new OtoritasDTO(t.getPengurusPermohonan()):null);
			this.setStatusPengurusPermohonan(t.getStatusPengurusPermohonan() != null ? new StatusPengurusPermohonanDTO(t.getStatusPengurusPermohonan()):null);
			this.setPenanggungJawabPermohonan(t.getPenanggungJawabPermohonan() != null ? new PegawaiDTO(t.getPenanggungJawabPermohonan()):null);
			this.setPengirimBerkas(t.getPengirimBerkas() != null ? new PosisiTahapPemberkasanDTO(t.getPengirimBerkas()):null);
			this.setPenerimaBerkas(t.getPenerimaBerkas() != null ? new PosisiTahapPemberkasanDTO(t.getPenerimaBerkas()):null);
			this.setStatusFlowLog(t.getStatusFlowLog() != null ? new StatusFlowLogDTO(t.getStatusFlowLog()):null);			
			this.setDaftarDokumenSyarat(t.getDaftarDokumenSyarat() != null ?
					t.getDaftarDokumenSyarat().stream().map(i -> new RegisterDokumenDTO(i)).collect(Collectors.toList()) : null);			
			this.setDaftarDokumenHasil(t.getDaftarDokumenHasil() != null ?
					t.getDaftarDokumenHasil().stream().map(i -> new RegisterDokumenDTO(i)).collect(Collectors.toList()) : null);			
			this.kategoriPermohonanSuratArahan = t.getJenisPermohonanSuratArahan() != null ? 
					new KategoriPermohonanSuratArahanDTO(t.getJenisPermohonanSuratArahan()):null;
			this.uraianKegiatan = t.getUraianKegiatan();
		}
	}
	
	public KategoriPermohonanSuratArahanDTO getKategoriPermohonanSuratArahan() {
		return kategoriPermohonanSuratArahan;
	}
	
	public void setKategoriPermohonanSuratArahan(KategoriPermohonanSuratArahanDTO kategoriPermohonanSuratArahan) {
		this.kategoriPermohonanSuratArahan = kategoriPermohonanSuratArahan;
	}
	
	public String getUraianKegiatan() {
		return uraianKegiatan;
	}
	
	public void setUraianKegiatan(String uraianKegiatan) {
		this.uraianKegiatan = uraianKegiatan;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 7137;
		hash = 121 * hash + Objects.hashCode(this.getId());
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
        
        final RegisterPermohonanArahanDTO other = (RegisterPermohonanArahanDTO) obj;
        
        if (!this.getId().equals(other.getId())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "RegisterPermohonanArahanDTO {"
				.concat("id=")
				.concat(this.getId())
				.concat(", tanggal registrasi=")
				.concat(this.getTanggalRegistrasi().toString())
				.concat("}");
	}	

	public RegisterPermohonanArahan toRegisterPermohonanArahan() {
		return new RegisterPermohonanArahan(
				this.getId(), 
				this.getKategoriPermohonan() != null ? this.getKategoriPermohonan().toKategoriPermohonan() : null, 
				this.getTanggalRegistrasi(), 
				this.getRegisterPerusahaan() != null ? this.getRegisterPerusahaan().toRegisterPerusahaan() : null,
				this.getPengurusPermohonan() != null ? this.getPengurusPermohonan().toOtoritas() : null, 
				this.getStatusPengurusPermohonan() != null ? this.getStatusPengurusPermohonan().toStatusPengurusPermohonan() : null, 
				this.getPenanggungJawabPermohonan() != null ? this.getPenanggungJawabPermohonan().toPegawaiPerusahaan() : null, 
				this.getPengirimBerkas() != null ? this.getPengirimBerkas().toPosisiTahapPemberkasan() : null, 
				this.getPenerimaBerkas() != null ? this.getPenerimaBerkas().toPosisiTahapPemberkasan() : null,	
				this.getStatusFlowLog() != null ? this.getStatusFlowLog().toStatusFlowLog() : null, 
				this.getDaftarDokumenSyarat() != null ?
						this.getDaftarDokumenSyarat().stream().map(i -> i.toRegisterDokumen()).collect(Collectors.toList()) : null, 
				this.getDaftarDokumenHasil() != null ?
						this.getDaftarDokumenHasil().stream().map(i -> i.toRegisterDokumen()).collect(Collectors.toList()): null, 
				this.kategoriPermohonanSuratArahan != null ? this.kategoriPermohonanSuratArahan.toKategoriPermohonanSuratArahan() : null, 
				this.uraianKegiatan
				);
	}
}
