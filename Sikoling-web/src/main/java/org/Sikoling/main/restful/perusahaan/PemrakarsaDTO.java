package org.Sikoling.main.restful.perusahaan;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.main.restful.bentukusaha.BentukUsahaDTO;
import org.Sikoling.main.restful.penanggungjawab.PenanggungJawabDTO;

public class PemrakarsaDTO implements Serializable {

	private static final long serialVersionUID = 739451306385730136L;
	private String id;
	private BentukUsahaDTO bentukUsaha;
//	private AktaPemrakarsaDTO aktaPemrakarsa;
	private AlamatPemrakarsaDTO alamat;
	private KontakPemrakarsaDTO kontakPemrakarsa;
//	private OSSDTO oss;
	private String nama;
	private String npwp;
	private PenanggungJawabDTO penanggungJawab;		
	private String idCreator;
	
	public PemrakarsaDTO() {
		
	}
	
	public PemrakarsaDTO(Perusahaan p) {
		this.id = p.getId();
		this.bentukUsaha = new BentukUsahaDTO(p.getBentukUsaha());
//		this.aktaPemrakarsa = new AktaPemrakarsaDTO(p.getAktaPendirian());
		this.alamat = new AlamatPemrakarsaDTO(p.getAlamat());
		this.kontakPemrakarsa = new KontakPemrakarsaDTO(p.getKontak());
//		this.oss = new OSSDTO(p.getOss());
		this.nama = p.getNama();
		this.npwp = p.getNpwp();
		this.penanggungJawab = new PenanggungJawabDTO(p.getPenanggungJawab());
	}	
	
	public PemrakarsaDTO(String id, BentukUsahaDTO bentukUsaha, AktaPemrakarsaDTO aktaPemrakarsa,
			AlamatPemrakarsaDTO alamat, KontakPemrakarsaDTO kontakPemrakarsa, OSSDTO oss, String nama, String npwp,
			PenanggungJawabDTO penanggungJawab, String idCreator) {
		super();
		this.id = id;
		this.bentukUsaha = bentukUsaha;
//		this.aktaPemrakarsa = aktaPemrakarsa;
		this.alamat = alamat;
		this.kontakPemrakarsa = kontakPemrakarsa;
//		this.oss = oss;
		this.nama = nama;
		this.npwp = npwp;
		this.penanggungJawab = penanggungJawab;
		this.idCreator = idCreator;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BentukUsahaDTO getBentukUsaha() {
		return bentukUsaha;
	}

	public void setBentukUsaha(BentukUsahaDTO bentukUsaha) {
		this.bentukUsaha = bentukUsaha;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public AlamatPemrakarsaDTO getAlamat() {
		return alamat;
	}

	public void setAlamat(AlamatPemrakarsaDTO alamat) {
		this.alamat = alamat;
	}

	public String getNpwp() {
		return npwp;
	}

	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	public PenanggungJawabDTO getPenanggungJawab() {
		return penanggungJawab;
	}

	public void setPenanggungJawab(PenanggungJawabDTO penanggungJawab) {
		this.penanggungJawab = penanggungJawab;
	}

	public String getIdCreator() {
		return idCreator;
	}

	public void setIdCreator(String idCreator) {
		this.idCreator = idCreator;
	}
	
//	public AktaPemrakarsaDTO getAktaPemrakarsa() {
//		return aktaPemrakarsa;
//	}

//	public void setAktaPemrakarsa(AktaPemrakarsaDTO aktaPemrakarsa) {
//		this.aktaPemrakarsa = aktaPemrakarsa;
//	}

	public KontakPemrakarsaDTO getKontakPemrakarsa() {
		return kontakPemrakarsa;
	}

	public void setKontakPemrakarsa(KontakPemrakarsaDTO kontakPemrakarsa) {
		this.kontakPemrakarsa = kontakPemrakarsa;
	}

//	public OSSDTO getOss() {
//		return oss;
//	}
//
//	public void setOss(OSSDTO oss) {
//		this.oss = oss;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
	public int hashCode() {
		int hash = 23;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        
        final PemrakarsaDTO other = (PemrakarsaDTO) obj;
        
        if (!this.id.equalsIgnoreCase(other.id)) {
            return false;
        }        

        return true;
	}

	@Override
	public String toString() {
		return "PemrakarsaDTO{" + "id=" + id + ", nama=" + bentukUsaha.getSingkatan() +". " + nama + ", alamat=" 
				+ alamat.getKeterangan() + '}';	  
	}

	public Perusahaan toPemrakarsa() {
//		return new Perusahaan(id, bentukUsaha.toBentukUsaha(), aktaPemrakarsa.toAktaPemrakarsa(), alamat.toAlamat(),
//				kontakPemrakarsa.toKontakPemrakarsa(), oss.toOSS(), nama, npwp, penanggungJawab.toPenanggungJawab());
		return new Perusahaan(id, bentukUsaha.toBentukUsaha(), alamat.toAlamat(),
				kontakPemrakarsa.toKontakPemrakarsa(), nama, npwp, penanggungJawab.toPenanggungJawab());
	}
	
}
