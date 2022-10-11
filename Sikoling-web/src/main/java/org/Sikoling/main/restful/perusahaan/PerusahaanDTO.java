package org.Sikoling.main.restful.perusahaan;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.main.restful.modelperizinan.ModelPerizinanDTO;
import org.Sikoling.main.restful.pelakuusaha.DetailPelakuUsahaDTO;
import org.Sikoling.main.restful.pelakuusaha.JenisPelakuUsahaDTO;
import org.Sikoling.main.restful.penanggungjawab.PenanggungJawabDTO;
import org.Sikoling.main.restful.skalausaha.SkalaUsahaDTO;

public class PerusahaanDTO implements Serializable {

	private static final long serialVersionUID = 739451306385730136L;
	private String id;
	private String nama;
	private ModelPerizinanDTO modePerizinan;
	private SkalaUsahaDTO skalaUsaha;
	private JenisPelakuUsahaDTO jenisPelakuUsaha;
	private DetailPelakuUsahaDTO detailPelakuUsaha;
	private AlamatPerusahaanDTO alamat;
	private KontakPerusahaanDTO kontakPerusahaan;
	private PenanggungJawabDTO penanggungJawab;	
	
	public PerusahaanDTO() {		
	}
	
	public PerusahaanDTO(Perusahaan p) {
		this.id = p.getId();
		this.nama = p.getNama();
		this.modePerizinan = new ModelPerizinanDTO(p.getModelPerizinan());
		this.skalaUsaha = new SkalaUsahaDTO(p.getSkalaUsaha());
		this.jenisPelakuUsaha = new JenisPelakuUsahaDTO(p.getJenisPelakuUsaha());
		this.detailPelakuUsaha = new DetailPelakuUsahaDTO(p.getDetailPelakuUsaha());
		this.alamat = new AlamatPerusahaanDTO(p.getAlamat());
		this.kontakPerusahaan = new KontakPerusahaanDTO(p.getKontak());
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
		return modePerizinan;
	}

	public void setModePerizinan(ModelPerizinanDTO modePerizinan) {
		this.modePerizinan = modePerizinan;
	}

	public SkalaUsahaDTO getSkalaUsaha() {
		return skalaUsaha;
	}

	public void setSkalaUsaha(SkalaUsahaDTO skalaUsaha) {
		this.skalaUsaha = skalaUsaha;
	}

	public JenisPelakuUsahaDTO getJenisPelakuUsaha() {
		return jenisPelakuUsaha;
	}

	public void setJenisPelakuUsaha(JenisPelakuUsahaDTO jenisPelakuUsaha) {
		this.jenisPelakuUsaha = jenisPelakuUsaha;
	}

	public DetailPelakuUsahaDTO getDetailPelakuUsaha() {
		return detailPelakuUsaha;
	}

	public void setDetailPelakuUsaha(DetailPelakuUsahaDTO detailPelakuUsaha) {
		this.detailPelakuUsaha = detailPelakuUsaha;
	}

	public AlamatPerusahaanDTO getAlamat() {
		return alamat;
	}

	public void setAlamat(AlamatPerusahaanDTO alamat) {
		this.alamat = alamat;
	}

	public KontakPerusahaanDTO getKontakPerusahaan() {
		return kontakPerusahaan;
	}

	public void setKontakPerusahaan(KontakPerusahaanDTO kontakPerusahaan) {
		this.kontakPerusahaan = kontakPerusahaan;
	}

	public PenanggungJawabDTO getPenanggungJawab() {
		return penanggungJawab;
	}

	public void setPenanggungJawab(PenanggungJawabDTO penanggungJawab) {
		this.penanggungJawab = penanggungJawab;
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

//	public Perusahaan toPemrakarsa() {
//		return new Perusahaan(
//				id, nama, modePerizinan.toModelPerizinan(),
//				skalaUsaha.toSkalaUsaha(), jenisPelakuUsaha.toJenisPelakuUsaha(),
//				alamat.toAlamat(), kontakPerusahaan.toKontakPerusahaan(),);
//	}
	
}
