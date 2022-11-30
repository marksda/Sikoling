package org.Sikoling.main.restful.perusahaan;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.main.restful.dokumen.DokumenDTO;
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
	private List<DokumenDTO> daftarDokumen;
	private boolean statusVerifikasi;
	
	public PerusahaanDTO() {		
	}
	
	public PerusahaanDTO(Perusahaan t) {
		this.id = t.getId();
		this.nama = t.getNama();
		this.modelPerizinan = new ModelPerizinanDTO(t.getModelPerizinan());
		this.skalaUsaha = new SkalaUsahaDTO(t.getSkalaUsaha());
		this.pelakuUsaha = new PelakuUsahaDTO(t.getPelakuUsaha());
		this.alamat = new AlamatPerusahaanDTO(t.getAlamat());
		this.kontak = new KontakPerusahaanDTO(t.getKontak());
		this.daftarDokumen = t.getDaftarDokumen()
								.stream()
								.map(item -> new DokumenDTO(item))
								.collect(Collectors.toList());
		this.statusVerifikasi = t.isStatusVerifikasi();
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

	public List<DokumenDTO> getDaftarDokumen() {
		return daftarDokumen;
	}

	public void setDaftarDokumen(List<DokumenDTO> daftarDokumen) {
		this.daftarDokumen = daftarDokumen;
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
				modelPerizinan.toModelPerizinan(),
				skalaUsaha.toSkalaUsaha(),
				pelakuUsaha.toPelakuUsaha(),
				alamat.toAlamat(),
				kontak.toKontak(),
				daftarDokumen.stream().map(item -> item.toDokumen()).collect(Collectors.toList()), 
				statusVerifikasi
				);
	}	
}
