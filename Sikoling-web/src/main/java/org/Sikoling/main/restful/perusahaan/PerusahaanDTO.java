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
	private ModelPerizinanDTO modelPerizinan;
	private SkalaUsahaDTO skalaUsaha;
	private PelakuUsahaDTO pelakuUsaha;
	private AlamatPerusahaanDTO alamat;
	private KontakPerusahaanDTO kontak;
	private List<RegisterDokumenDTO> daftarRegisterDokumen;
	private Boolean statusVerifikasi;
	
	public PerusahaanDTO() {		
	}
	
	public PerusahaanDTO(Perusahaan t) {
		if( t != null) {
			this.id = t.getId();
			this.nama = t.getNama();
			this.modelPerizinan = t.getModelPerizinan() != null ? new ModelPerizinanDTO(t.getModelPerizinan()) : null;
			this.skalaUsaha = t.getSkalaUsaha() != null ? new SkalaUsahaDTO(t.getSkalaUsaha()) : null;
			this.pelakuUsaha = t.getPelakuUsaha() != null ? new PelakuUsahaDTO(t.getPelakuUsaha()) : null;
			this.alamat = t.getAlamat() != null ? new AlamatPerusahaanDTO(t.getAlamat()) : null;
			this.kontak = t.getKontak() != null ? new KontakPerusahaanDTO(t.getKontak()) : null;
			this.daftarRegisterDokumen = t.getDaftarRegisterDokumen() != null? t.getDaftarRegisterDokumen()
									.stream()
									.map(item -> new RegisterDokumenDTO(item))
									.collect(Collectors.toList()) : null;
			this.statusVerifikasi = t.isStatusVerifikasi() != null ? t.isStatusVerifikasi() : null;
		}
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

	public ModelPerizinanDTO getModelPerizinan() {
		return modelPerizinan;
	}

	public void setModelPerizinan(ModelPerizinanDTO modelPerizinan) {
		this.modelPerizinan = modelPerizinan;
	}

	public SkalaUsahaDTO getSkalaUsaha() {
		return skalaUsaha;
	}

	public void setSkalaUsaha(SkalaUsahaDTO skalaUsaha) {
		this.skalaUsaha = skalaUsaha;
	}

	public PelakuUsahaDTO getPelakuUsaha() {
		return pelakuUsaha;
	}

	public void setPelakuUsaha(PelakuUsahaDTO pelakuUsaha) {
		this.pelakuUsaha = pelakuUsaha;
	}

	public AlamatPerusahaanDTO getAlamat() {
		return alamat;
	}

	public void setAlamat(AlamatPerusahaanDTO alamat) {
		this.alamat = alamat;
	}

	public KontakPerusahaanDTO getKontak() {
		return kontak;
	}

	public void setKontak(KontakPerusahaanDTO kontak) {
		this.kontak = kontak;
	}

	public List<RegisterDokumenDTO> getDaftarRegisterDokumen() {
		return daftarRegisterDokumen;
	}

	public void setDaftarRegisterDokumen(List<RegisterDokumenDTO> daftarRegisterDokumen) {
		this.daftarRegisterDokumen = daftarRegisterDokumen;
	}

	public Boolean isStatusVerifikasi() {
		return statusVerifikasi;
	}

	public void setStatusVerifikasi(Boolean statusVerifikasi) {
		this.statusVerifikasi = statusVerifikasi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 113;
        hash = 171 * hash + Objects.hashCode(this.id);
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
        
        if (!this.id.equalsIgnoreCase(other.getId())) {
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
				modelPerizinan != null ? modelPerizinan.toModelPerizinan() : null,
				skalaUsaha != null ? skalaUsaha.toSkalaUsaha() : null,
						pelakuUsaha != null ? pelakuUsaha.toPelakuUsaha() : null,
				alamat != null ? alamat.toAlamat() : null,
				kontak != null ? kontak.toKontak() : null,
				daftarRegisterDokumen != null ? 
						daftarRegisterDokumen.stream()
						.map(item -> item.toRegisterDokumen())
						.collect(Collectors.toList())
						: null, 
				statusVerifikasi
				);
	}	
}
