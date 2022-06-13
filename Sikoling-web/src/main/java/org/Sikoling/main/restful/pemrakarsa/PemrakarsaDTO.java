package org.Sikoling.main.restful.pemrakarsa;

import java.io.Serializable;
import java.util.Date;

import org.Sikoling.ejb.abstraction.entity.Pemrakarsa;
import org.Sikoling.main.restful.bentukusaha.BentukUsahaDTO;
import org.Sikoling.main.restful.penanggungjawab.PenanggungJawabDTO;

public class PemrakarsaDTO implements Serializable {

	private static final long serialVersionUID = 739451306385730136L;
	private String id;
	private BentukUsahaDTO bentukUsaha;
	private String nomorIndukBerusaha;
	private String nama;
	private String namaNotaris;	
	private AlamatPemrakarsaDTO alamat;
	private String telepone;
	private String fax;
	private String npwp;
	private String email;
	private PenanggungJawabDTO penanggungJawab;
	private Date tanggalNotaris;
	private Date tanggalOSS;
	private String idCreator;
	
	public PemrakarsaDTO() {
		
	}
	
	public PemrakarsaDTO(Pemrakarsa p) {
		this.id = p.getId();
		this.bentukUsaha = new BentukUsahaDTO(p.getBentukUsaha());
		this.nomorIndukBerusaha = p.getNomorIndukBerusaha();
		this.nama = p.getNama();
		this.namaNotaris = p.getNamaNotaris();
		this.alamat = new AlamatPemrakarsaDTO(p.getAlamat());
		this.telepone = p.getTelepone();
		this.fax = p.getFax();
		this.npwp = p.getNpwp();
		this.email = p.getEmail();
		this.penanggungJawab = new PenanggungJawabDTO(p.getPenanggungJawab());
		this.tanggalNotaris = p.getTanggalNotaris();
		this.tanggalOSS = p.getTanggalOSS();
		this.idCreator = p.getIdCreator();
	}	
	
	public PemrakarsaDTO(String id, BentukUsahaDTO bentukUsaha, String nomorIndukBerusaha, String nama,
			String namaNotaris, AlamatPemrakarsaDTO alamat, String telepone, String fax, String npwp, String email,
			PenanggungJawabDTO penanggungJawab, Date tanggalNotaris, Date tanggalOSS, String idCreator) {
		super();
		this.id = id;
		this.bentukUsaha = bentukUsaha;
		this.nomorIndukBerusaha = nomorIndukBerusaha;
		this.nama = nama;
		this.namaNotaris = namaNotaris;
		this.alamat = alamat;
		this.telepone = telepone;
		this.fax = fax;
		this.npwp = npwp;
		this.email = email;
		this.penanggungJawab = penanggungJawab;
		this.tanggalNotaris = tanggalNotaris;
		this.tanggalOSS = tanggalOSS;
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

	public String getNomorIndukBerusaha() {
		return nomorIndukBerusaha;
	}

	public void setNomorIndukBerusaha(String nomorIndukBerusaha) {
		this.nomorIndukBerusaha = nomorIndukBerusaha;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getNamaNotaris() {
		return namaNotaris;
	}

	public void setNamaNotaris(String namaNotaris) {
		this.namaNotaris = namaNotaris;
	}

	public AlamatPemrakarsaDTO getAlamat() {
		return alamat;
	}

	public void setAlamat(AlamatPemrakarsaDTO alamat) {
		this.alamat = alamat;
	}

	public String getTelepone() {
		return telepone;
	}

	public void setTelepone(String telepone) {
		this.telepone = telepone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getNpwp() {
		return npwp;
	}

	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PenanggungJawabDTO getPenanggungJawab() {
		return penanggungJawab;
	}

	public void setPenanggungJawab(PenanggungJawabDTO penanggungJawab) {
		this.penanggungJawab = penanggungJawab;
	}

	public Date getTanggalNotaris() {
		return tanggalNotaris;
	}

	public void setTanggalNotaris(Date tanggalNotaris) {
		this.tanggalNotaris = tanggalNotaris;
	}

	public Date getTanggalOSS() {
		return tanggalOSS;
	}

	public void setTanggalOSS(Date tanggalOSS) {
		this.tanggalOSS = tanggalOSS;
	}

	public String getIdCreator() {
		return idCreator;
	}

	public void setIdCreator(String idCreator) {
		this.idCreator = idCreator;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
	public Pemrakarsa toPemrakarsa() {
		return new Pemrakarsa(id, bentukUsaha.toBentukUsaha(), nomorIndukBerusaha, nama, namaNotaris, alamat.toAlamat(), 
				telepone, fax, npwp, email, penanggungJawab.toPenanggungJawab(), tanggalNotaris, tanggalOSS, idCreator);
	}
}
