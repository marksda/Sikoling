package org.Sikoling.main.restful.perusahaan;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Perusahaan;
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
	
	public PerusahaanDTO() {		
	}
	
	public PerusahaanDTO(Perusahaan p) {
		this.id = p.getId();
		this.nama = p.getNama();
		this.modelPerizinan = new ModelPerizinanDTO(p.getModelPerizinan());
		this.skalaUsaha = new SkalaUsahaDTO(p.getSkalaUsaha());
		this.pelakuUsaha = new PelakuUsahaDTO(p.getPelakuUsaha());
		this.alamat = new AlamatPerusahaanDTO(p.getAlamat());
		this.kontak = new KontakPerusahaanDTO(p.getKontak());
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
	
	public ModelPerizinanDTO getModePerizinan() {
		return modelPerizinan;
	}
	
	public void setModelPerizinan(ModelPerizinanDTO modePerizinan) {
		this.modelPerizinan = modePerizinan;
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

	public void setPelakuUsaha(PelakuUsahaDTO detailPelakuUsaha) {
		this.pelakuUsaha = detailPelakuUsaha;
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

	public void setKontak(KontakPerusahaanDTO kontakPerusahaan) {
		this.kontak = kontakPerusahaan;
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
				id, nama, modelPerizinan.toModelPerizinan(), 
				skalaUsaha.toSkalaUsaha(), pelakuUsaha.toPelakuUsaha(),
				alamat.toAlamat(), kontak.toKontakPerusahaan());
	}
}
