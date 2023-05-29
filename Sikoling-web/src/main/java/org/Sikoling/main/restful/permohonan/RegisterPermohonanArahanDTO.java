package org.Sikoling.main.restful.permohonan;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonanArahan;
import org.Sikoling.main.restful.authority.AuthorityDTO;
import org.Sikoling.main.restful.dokumen.RegisterDokumenDTO;
import org.Sikoling.main.restful.log.StatusFlowLogDTO;
import org.Sikoling.main.restful.pegawai.PegawaiPerusahaanDTO;
import org.Sikoling.main.restful.perusahaan.RegisterPerusahaanDTO;

public class RegisterPermohonanArahanDTO extends RegisterPermohonanDTO implements Serializable {

	private static final long serialVersionUID = -150450427979362608L;
	private JenisPermohonanSuratArahanDTO jenisPermohonanSuratArahan;
	private String uraianKegiatan;
	
	public RegisterPermohonanArahanDTO() {
	}
	
	public RegisterPermohonanArahanDTO(RegisterPermohonanArahan t) {
		super();
		if(t != null) {
			this.setId(t.getId());
			KategoriPermohonanDTO kategoriPermohonanDTO = t.getKategoriPermohonan() != null ?
					new KategoriPermohonanDTO(t.getKategoriPermohonan()) : null;
			this.setKategoriPermohonan(kategoriPermohonanDTO);
			this.setTanggalRegistrasi(t.getTanggalRegistrasi());
			
			RegisterPerusahaanDTO registerPerusahaanDTO = t.getPerusahaan() != null ?
					new RegisterPerusahaanDTO(t.getPerusahaan()) : null;
			this.setRegisterPerusahaan(registerPerusahaanDTO);
			
			AuthorityDTO pengurusPermohonan = t.getPengurusPermohonan() != null ?
					new AuthorityDTO(t.getPengurusPermohonan()) : null;
			this.setPengurusPermohonan(pengurusPermohonan);
			
			StatusPengurusPermohonanDTO statusWaliDTO = t.getStatusPengurusPermohonan() != null ?
					new StatusPengurusPermohonanDTO(t.getStatusPengurusPermohonan()) : null;
			this.setStatusPengurusPermohonan(statusWaliDTO);
			
			PegawaiPerusahaanDTO pegawaiPerusahaanDTO = t.getPenanggungJawabPermohonan() != null ?
					new PegawaiPerusahaanDTO(t.getPenanggungJawabPermohonan()) : null;
			this.setPenanggungJawabPermohonan(pegawaiPerusahaanDTO);
			
			PosisiTahapPemberkasanDTO pengirimBerkas = t.getPengirimBerkas() != null ?
					new PosisiTahapPemberkasanDTO(t.getPengirimBerkas()) : null;
			this.setPengirimBerkas(pengirimBerkas);
			
			PosisiTahapPemberkasanDTO penerimaBerkas = t.getPenerimaBerkas() != null ?
					new PosisiTahapPemberkasanDTO(t.getPenerimaBerkas()) : null;
			this.setPenerimaBerkas(penerimaBerkas);
			
			StatusFlowLogDTO statusFlowLog = t.getStatusFlowLog() != null ?
					new StatusFlowLogDTO(t.getStatusFlowLog()) : null;
			this.setStatusFlowLog(statusFlowLog);
			
			List<RegisterDokumenDTO> daftarDokumenSyarat = t.getDaftarDokumenSyarat() != null ?
					t.getDaftarDokumenSyarat()
					.stream()
					.map(i -> new RegisterDokumenDTO(i))
					.collect(Collectors.toList()) : null;
			this.setDaftarDokumenSyarat(daftarDokumenSyarat);
			
			List<RegisterDokumenDTO> daftarDokumenHasil = t.getDaftarDokumenHasil() != null ?
					t.getDaftarDokumenHasil()
					.stream()
					.map(i -> new RegisterDokumenDTO(i))
					.collect(Collectors.toList()) : null;
			this.setDaftarDokumenHasil(daftarDokumenHasil);
			
			this.jenisPermohonanSuratArahan = t.getJenisPermohonanSuratArahan() != null ?
					new JenisPermohonanSuratArahanDTO(t.getJenisPermohonanSuratArahan()) : null;
			this.uraianKegiatan = t.getUraianKegiatan();
		}
	}
	
	public JenisPermohonanSuratArahanDTO getJenisPermohonanSuratArahan() {
		return jenisPermohonanSuratArahan;
	}
	
	public void setJenisPermohonanSuratArahan(JenisPermohonanSuratArahanDTO jenisPermohonanSuratArahan) {
		this.jenisPermohonanSuratArahan = jenisPermohonanSuratArahan;
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
				this.getKategoriPermohonan() != null ?
						this.getKategoriPermohonan().toKategoriPermohonan() : null, 
				this.getTanggalRegistrasi()	, 
				this.getRegisterPerusahaan() != null ?
						this.getRegisterPerusahaan().toRegisterPerusahaan() : null,
				this.getPengurusPermohonan() != null ?
						this.getPengurusPermohonan().toAuthority() : null, 
				this.getStatusPengurusPermohonan() != null ?
						this.getStatusPengurusPermohonan().toStatusPengurusPermohonan() : null, 
				this.getPenanggungJawabPermohonan() != null ?
						this.getPenanggungJawabPermohonan().toPegawaiPerusahaan() : null, 
				this.getPengirimBerkas() != null ?
						this.getPengirimBerkas().toPosisiTahapPemberkasan() : null, 
				this.getPenerimaBerkas() != null ?
						this.getPenerimaBerkas().toPosisiTahapPemberkasan() : null,	
				this.getStatusFlowLog() != null ?
								this.getStatusFlowLog().toStatusFlowLog() : null, 
				this.getDaftarDokumenSyarat() != null ?
						this.getDaftarDokumenSyarat()
							.stream()
							.map(i -> i.toRegisterDokumen())
							.collect(Collectors.toList()) : null, 
				this.getDaftarDokumenHasil() != null ?
						this.getDaftarDokumenHasil()
							.stream()
							.map(i -> i.toRegisterDokumen())
							.collect(Collectors.toList()): null, 
				this.jenisPermohonanSuratArahan != null ?
						this.jenisPermohonanSuratArahan.toJenisPermohonanSuratArahan() : null, 
				this.uraianKegiatan
				);
	}
}
