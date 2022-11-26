package org.Sikoling.main.restful.perusahaan;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.main.restful.dokumen.RegisterDokumenDTO;
import org.Sikoling.main.restful.modelperizinan.ModelPerizinanDTO;
import org.Sikoling.main.restful.pelakuusaha.PelakuUsahaDTO;
import org.Sikoling.main.restful.skalausaha.SkalaUsahaDTO;

public class PerusahaanDTO implements Serializable {

	private static final long serialVersionUID = 739451306385730136L;
	private String id;
	private String nama;
	private ModelPerizinanDTO modelPerizinanDTO;
	private SkalaUsahaDTO skalaUsahaDTO;
	private PelakuUsahaDTO pelakuUsahaDTO;
	private AlamatPerusahaanDTO alamatPerusahaanDTO;
	private KontakPerusahaanDTO kontakPerusahaanDTO;
	private List<RegisterDokumenDTO> daftarRegisterDokumenDTO;
	private boolean statusVerifikasi;
	
	public PerusahaanDTO() {		
	}
	
	public PerusahaanDTO(Perusahaan p) {
		this.id = p.getId();
		this.nama = p.getNama();
		this.modelPerizinanDTO = new ModelPerizinanDTO(p.getModelPerizinan());
		this.skalaUsahaDTO = new SkalaUsahaDTO(p.getSkalaUsaha());
		this.pelakuUsahaDTO = new PelakuUsahaDTO(p.getPelakuUsaha());
		this.alamatPerusahaanDTO = new AlamatPerusahaanDTO(p.getAlamat());
		this.kontakPerusahaanDTO = new KontakPerusahaanDTO(p.getKontak());
		this.daftarRegisterDokumenDTO = p.getDaftarRegisterDokumen().stream()
				.map(t -> new RegisterDokumenDTO(t)).collect(Collectors.toList());
		this.statusVerifikasi = p.isStatusVerifikasi();
	}	

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNama() {
		return nama;
	}
	
	public void setNama(String nama) {
		this.nama = nama;
	}
		
	public ModelPerizinanDTO getModelPerizinanDTO() {
		return modelPerizinanDTO;
	}

	public void setModelPerizinanDTO(ModelPerizinanDTO modelPerizinanDTO) {
		this.modelPerizinanDTO = modelPerizinanDTO;
	}

	public SkalaUsahaDTO getSkalaUsahaDTO() {
		return skalaUsahaDTO;
	}
	
	public void setSkalaUsahaDTO(SkalaUsahaDTO skalaUsahaDTO) {
		this.skalaUsahaDTO = skalaUsahaDTO;
	}

	public PelakuUsahaDTO getPelakuUsahaDTO() {
		return pelakuUsahaDTO;
	}

	public void setPelakuUsahaDTO(PelakuUsahaDTO detailPelakuUsahaDTO) {
		this.pelakuUsahaDTO = detailPelakuUsahaDTO;
	}

	public AlamatPerusahaanDTO getAlamatPerusahaanDTO() {
		return alamatPerusahaanDTO;
	}

	public void setAlamatPerusahaanDTO(AlamatPerusahaanDTO alamatPerusahaanDTO) {
		this.alamatPerusahaanDTO = alamatPerusahaanDTO;
	}

	public KontakPerusahaanDTO getKontakPerusahaanDTO() {
		return kontakPerusahaanDTO;
	}

	public void setKontakPerusahaanDTO(KontakPerusahaanDTO kontakPerusahaanPerusahaanDTO) {
		this.kontakPerusahaanDTO = kontakPerusahaanPerusahaanDTO;
	}
	
	public List<RegisterDokumenDTO> getDaftarRegisterDokumenDTO() {
		return daftarRegisterDokumenDTO;
	}

	public void setDaftarRegisterDokumenDTO(List<RegisterDokumenDTO> daftarRegisterDokumenDTO) {
		this.daftarRegisterDokumenDTO = daftarRegisterDokumenDTO;
	}

	public boolean isStatusVerifikasi() {
		return statusVerifikasi;
	}

	public void setStatusVerifikasi(boolean statusVerifikasi) {
		this.statusVerifikasi = statusVerifikasi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
	public int hashCode() {
		int hash = 29;
        hash = 171 * hash + Objects.hashCode(this.id);
        hash = 171 * hash + Objects.hashCode(this.nama);
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
        
        final PerusahaanDTO other = (PerusahaanDTO) obj;
        
        if (!this.id.equalsIgnoreCase(other.id)) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "PerusahaanDTO{id="
				.concat(id)
				.concat(", nama=")
				.concat(nama)
				.concat("}");	  
	}

	public Perusahaan toPerusahaan() {
		return new Perusahaan(
				id, 
				nama, 
				modelPerizinanDTO.toModelPerizinan(), 
				skalaUsahaDTO.toSkalaUsaha(), 
				pelakuUsahaDTO.toPelakuUsaha(),
				alamatPerusahaanDTO.toAlamat(),
				kontakPerusahaanDTO.toKontakPerusahaan(),
				daftarRegisterDokumenDTO.stream().map(d -> d.toRegisterDokumen()).collect(Collectors.toList()), 
				statusVerifikasi);
	}
}
