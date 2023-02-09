package org.Sikoling.main.restful.permohonan;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;
import org.Sikoling.main.restful.authority.AuthorityDTO;
import org.Sikoling.main.restful.dokumen.RegisterDokumenDTO;
import org.Sikoling.main.restful.person.PersonDTO;
import org.Sikoling.main.restful.perusahaan.RegisterPerusahaanDTO;

public class RegisterPermohonanDTO implements Serializable {

	private static final long serialVersionUID = 9038084331474645491L;
	private String id;
	private KategoriPermohonanDTO kategoriPermohonan;
	private LocalDate tanggalRegistrasi;
	private RegisterPerusahaanDTO registerPerusahaan;
	private AuthorityDTO pengurusPermohonan;
	private StatusWaliDTO statusWali;
	private PersonDTO penanggungJawabPermohonan;
	private PosisiTahapPemberkasanDTO statusTahapPemberkasan;
	private List<RegisterDokumenDTO> daftarDokumenSyarat;
	private List<RegisterDokumenDTO> daftarDokumenHasil;
	
	public RegisterPermohonanDTO() {
	}
	
	public RegisterPermohonanDTO(RegisterPermohonan t) {
		if(t != null) {
			this.id = t.getId();
			this.kategoriPermohonan = t.getKategoriPermohonan() != null ?
					new KategoriPermohonanDTO(t.getKategoriPermohonan()) : null;
			this.tanggalRegistrasi = t.getTanggalRegistrasi();
			this.registerPerusahaan = t.getPerusahaan() != null ?
					new RegisterPerusahaanDTO(t.getPerusahaan()) : null;			
			this.pengurusPermohonan = t.getPengurusPermohonan() != null ?
					new AuthorityDTO(t.getPengurusPermohonan()) : null;
			this.statusWali = t.getStatusWaliPengurusPermohonan() != null ?
					new StatusWaliDTO(t.getStatusWaliPengurusPermohonan()) : null;
			this.penanggungJawabPermohonan = t.getPenanggungJawabPermohonan() != null ?
					new PersonDTO(t.getPenanggungJawabPermohonan()) : null;
			this.statusTahapPemberkasan = t.getPosisiBerkas() != null ?
					new PosisiTahapPemberkasanDTO(t.getPosisiBerkas()) : null;
			this.daftarDokumenSyarat = t.getDaftarDokumenSyarat() != null ?
					t.getDaftarDokumenSyarat()
					.stream()
					.map(i -> new RegisterDokumenDTO(i))
					.collect(Collectors.toList()) : null;
			this.daftarDokumenHasil = t.getDaftarDokumenHasil() != null ?
					t.getDaftarDokumenHasil()
					.stream()
					.map(i -> new RegisterDokumenDTO(i))
					.collect(Collectors.toList()) : null;
		}
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public KategoriPermohonanDTO getKategoriPermohonan() {
		return kategoriPermohonan;
	}
	
	public void setKategoriPermohonan(KategoriPermohonanDTO kategoriPermohonan) {
		this.kategoriPermohonan = kategoriPermohonan;
	}
	
	public LocalDate getTanggalRegistrasi() {
		return tanggalRegistrasi;
	}
	
	public void setTanggalRegistrasi(LocalDate tanggalRegistrasi) {
		this.tanggalRegistrasi = tanggalRegistrasi;
	}
	
	public RegisterPerusahaanDTO getRegisterPerusahaan() {
		return registerPerusahaan;
	}
	
	public void setRegisterPerusahaan(RegisterPerusahaanDTO registerPerusahaan) {
		this.registerPerusahaan = registerPerusahaan;
	}
	
	public AuthorityDTO getPengurusPermohonan() {
		return pengurusPermohonan;
	}
	
	public void setPengurusPermohonan(AuthorityDTO pengurusPermohonan) {
		this.pengurusPermohonan = pengurusPermohonan;
	}
	
	public PosisiTahapPemberkasanDTO getStatusTahapPemberkasan() {
		return statusTahapPemberkasan;
	}
	
	public void setStatusTahapPemberkasan(PosisiTahapPemberkasanDTO statusTahapPemberkasan) {
		this.statusTahapPemberkasan = statusTahapPemberkasan;
	}
	
	public List<RegisterDokumenDTO> getDaftarDokumenSyarat() {
		return daftarDokumenSyarat;
	}
	
	public void setDaftarDokumenSyarat(List<RegisterDokumenDTO> daftarDokumenSyarat) {
		this.daftarDokumenSyarat = daftarDokumenSyarat;
	}
	
	public List<RegisterDokumenDTO> getDaftarDokumenHasil() {
		return daftarDokumenHasil;
	}
	
	public void setDaftarDokumenHasil(List<RegisterDokumenDTO> daftarDokumenHasil) {
		this.daftarDokumenHasil = daftarDokumenHasil;
	}
	
	public StatusWaliDTO getStatusWali() {
		return statusWali;
	}

	public void setStatusWali(StatusWaliDTO statusWali) {
		this.statusWali = statusWali;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

	@Override
	public int hashCode() {
		int hash = 737;
		hash = 121 * hash + Objects.hashCode(id);
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
        
        final RegisterPermohonanDTO other = (RegisterPermohonanDTO) obj;
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "RegisterPermohonanDTO {"
				.concat("id=")
				.concat(id)
				.concat(", tanggal registrasi=")
				.concat(tanggalRegistrasi.toString())
				.concat("}");
	}	

	public RegisterPermohonan toRegisterPermohonan() {
		return new RegisterPermohonan(
				id, 
				kategoriPermohonan != null ?
						kategoriPermohonan.toKategoriPermohonan() : null, 
				tanggalRegistrasi, 
				registerPerusahaan != null ?
						registerPerusahaan.toRegisterPerusahaan() : null, 
				pengurusPermohonan != null ?
						pengurusPermohonan.toAuthority() : null, 
				statusWali != null ?
						statusWali.toStatusWali() : null,
				penanggungJawabPermohonan != null ?
						penanggungJawabPermohonan.toPerson() : null,
				statusTahapPemberkasan != null ?
						statusTahapPemberkasan.toPosisiTahapPemberkasan() : null, 
				daftarDokumenSyarat != null ?
						daftarDokumenSyarat.stream()
						.map(i -> i.toRegisterDokumen())
						.collect(Collectors.toList()) : null, 
				daftarDokumenHasil != null ?
						daftarDokumenHasil.stream()
						.map(i -> i.toRegisterDokumen())
						.collect(Collectors.toList()): null
				);
	}
	
}
