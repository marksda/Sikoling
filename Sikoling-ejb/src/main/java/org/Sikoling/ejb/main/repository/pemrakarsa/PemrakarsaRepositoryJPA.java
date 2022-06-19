package org.Sikoling.ejb.main.repository.pemrakarsa;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.AktaPemrakarsa;
import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.BentukUsaha;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.KBLI;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.KontakPemrakarsa;
import org.Sikoling.ejb.abstraction.entity.OSS;
import org.Sikoling.ejb.abstraction.entity.Pemrakarsa;
import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.repository.IPemrakarsaRepository;
import org.Sikoling.ejb.main.repository.bentukusaha.BentukUsahaData;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.penanggungjawab.PenanggungJawabData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.user.UserData;

import jakarta.persistence.EntityManager;

public class PemrakarsaRepositoryJPA implements IPemrakarsaRepository {
	
	private final EntityManager entityManager;	

	public PemrakarsaRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<Pemrakarsa> getAll() {
		return entityManager.createNamedQuery("PemrakarsaData.findAll", PemrakarsaData.class)
				.getResultList()
				.stream()
				.map(t -> convertPemrakarsaDataToPemrakarsa(t))
				.collect(Collectors.toList());
	}

	@Override
	public Pemrakarsa save(Pemrakarsa t) {
		PemrakarsaData pemrakarsaData = convertPemrakarsaToPemrakarsaData(t);
		
		entityManager.persist(pemrakarsaData);
		entityManager.flush();
		
		return convertPemrakarsaDataToPemrakarsa(pemrakarsaData);
	}

	@Override
	public Pemrakarsa update(Pemrakarsa t) {
		PemrakarsaData pemrakarsaData = convertPemrakarsaToPemrakarsaData(t);
		pemrakarsaData = entityManager.merge(pemrakarsaData);
		return convertPemrakarsaDataToPemrakarsa(pemrakarsaData);
	}

	@Override
	public List<Pemrakarsa> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("PemrakarsaData.findAll", PemrakarsaData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPemrakarsaDataToPemrakarsa(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Pemrakarsa> getByQueryNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("PemrakarsaData.findByQueryNama", PemrakarsaData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertPemrakarsaDataToPemrakarsa(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Pemrakarsa> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("PemrakarsaData.findByQueryNama", PemrakarsaData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPemrakarsaDataToPemrakarsa(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Pemrakarsa> getByCreator(String idCreator) {
		return entityManager.createNamedQuery("PemrakarsaData.findByCreator", PemrakarsaData.class)
				.setParameter("idCreator", idCreator)
				.getResultList()
				.stream()
				.map(t -> convertPemrakarsaDataToPemrakarsa(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Pemrakarsa> getByCreatorAndPage(String idCreator, Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("PemrakarsaData.findByCreator", PemrakarsaData.class)
				.setParameter("idCreator", idCreator)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPemrakarsaDataToPemrakarsa(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Pemrakarsa> getByCreatorAndNama(String idCreator, String nama) {
		return entityManager.createNamedQuery("PemrakarsaData.findByCreatorAndNama", PemrakarsaData.class)
				.setParameter("idCreator", idCreator)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertPemrakarsaDataToPemrakarsa(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Pemrakarsa> getByCreatorAndNamaAndPage(String idCreator, String nama, Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("PemrakarsaData.findByCreatorAndNama", PemrakarsaData.class)
				.setParameter("idCreator", idCreator)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPemrakarsaDataToPemrakarsa(t))
				.collect(Collectors.toList());
	}

	private PemrakarsaData convertPemrakarsaToPemrakarsaData(Pemrakarsa p) {
		PemrakarsaData pemrakarsaData = new PemrakarsaData();
		
		pemrakarsaData.setId(p.getId());
		
		BentukUsahaData bentukUsahaData = new BentukUsahaData();
		bentukUsahaData.setId(p.getBentukUsaha().getId());
		bentukUsahaData.setNama(p.getBentukUsaha().getNama());
		pemrakarsaData.setBentukUsaha(bentukUsahaData);
		
		AktaPemrakarsaData aktaPemrakarsaData = new AktaPemrakarsaData();
		aktaPemrakarsaData.setNamaNotaris(p.getAktaPendirian().getNamaNotaris());
		aktaPemrakarsaData.setNomor(p.getAktaPendirian().getNomor());
		aktaPemrakarsaData.setTanggal(p.getAktaPendirian().getTanggal());
		pemrakarsaData.setAktaPemrakarsaData(aktaPemrakarsaData);
		
		AlamatPemrakarsaData alamatPemrakarsaData = new AlamatPemrakarsaData();
		DesaData desaData = new DesaData();
		desaData.setId(p.getAlamat().getDesa().getId());
		alamatPemrakarsaData.setDesa(desaData);
		KecamatanData kecamatanData = new KecamatanData();
		kecamatanData.setId(p.getAlamat().getKecamatan().getId());
		alamatPemrakarsaData.setKecamatan(kecamatanData);
		KabupatenData kabupatenData = new KabupatenData();
		kabupatenData.setId(p.getAlamat().getKabupaten().getId());
		alamatPemrakarsaData.setKabupaten(kabupatenData);
		PropinsiData propinsiData = new PropinsiData();
		propinsiData.setId(p.getAlamat().getPropinsi().getId());
		alamatPemrakarsaData.setPropinsi(propinsiData);
		pemrakarsaData.setAlamatPemrakarsaData(alamatPemrakarsaData);
		
		KontakPemrakarsaData kontakPemrakarsaData = new KontakPemrakarsaData();
		kontakPemrakarsaData.setEmail(p.getKontakPemrakarsa().getEmail());
		kontakPemrakarsaData.setFax(p.getKontakPemrakarsa().getFax());
		kontakPemrakarsaData.setTelepone(p.getKontakPemrakarsa().getTelepone());
		pemrakarsaData.setKontakPemrakarsaData(kontakPemrakarsaData);
		
		OSSData ossData = new OSSData();
		ossData.setNib(p.getOss().getNib());
		ossData.setTanggal(p.getOss().getTanggal());	
		ossData.setKbliDatas(p.getOss().getKblis()
				.stream()
				.map(t -> convertKBLIToKBData(t))
				.collect(Collectors.toList())				
				);		
		pemrakarsaData.setOssData(ossData);		
		
		pemrakarsaData.setNama(p.getNama());
		pemrakarsaData.setNoNpwp(p.getNpwp());
		
		PenanggungJawabData penanggungJawabData = new PenanggungJawabData();
		penanggungJawabData.setId(p.getPenanggungJawab().getId());
		pemrakarsaData.setPenanggungJawab(penanggungJawabData);
		
		UserData userData = new UserData();
		userData.setId("0001");
		pemrakarsaData.setCreator(userData);
		
		return pemrakarsaData;
	}
	
	private Pemrakarsa convertPemrakarsaDataToPemrakarsa(PemrakarsaData d) {
		BentukUsaha bentukUsaha = new BentukUsaha(d.getBentukUsaha().getId(), d.getBentukUsaha().getNama());	
		
		AktaPemrakarsa aktaPemrakarsa = new AktaPemrakarsa(d.getAktaPemrakarsaData().getNomor(), d.getAktaPemrakarsaData().getTanggal(), 
				d.getAktaPemrakarsaData().getNamaNotaris());		
		
		Alamat alamat = new Alamat(
				new Propinsi(d.getAlamatPemrakarsaData().getPropinsi().getId(), d.getAlamatPemrakarsaData().getPropinsi().getNama()),
				new Kabupaten(d.getAlamatPemrakarsaData().getKabupaten().getId(), d.getAlamatPemrakarsaData().getKabupaten().getNama()),
				new Kecamatan(d.getAlamatPemrakarsaData().getKecamatan().getId(), d.getAlamatPemrakarsaData().getKecamatan().getNama()),
				new Desa(d.getAlamatPemrakarsaData().getDesa().getId(), d.getAlamatPemrakarsaData().getDesa().getNama()),
				d.getAlamatPemrakarsaData().getKeterangan());
		
		KontakPemrakarsa kontakPemrakarsa = new KontakPemrakarsa(d.getKontakPemrakarsaData().getTelepone(), 
				d.getKontakPemrakarsaData().getFax(), d.getKontakPemrakarsaData().getEmail());
		
		OSS oss = new OSS(d.getOssData().getNib(), d.getOssData().getTanggal(), 
				d.getOssData().getKbliDatas()
				.stream()
				.map(dt -> convertKBLIDataToKBLI(dt))
				.collect(Collectors.toList())
				);
		
//		PenanggungJawab penanggungJawab = new PenanggungJawab(
//				d.getPenanggungJawab().getId(), d.getPenanggungJawab().getNama()
//				null, null, alamat, null, null, null, null);
		
		
//		PenanggungJawabData penanggungJawabData = d.getPenanggungJawab();
//		PropinsiData propinsiDataPJ = penanggungJawabData.getPropinsi();
//		Propinsi propinsiPJ = new Propinsi(propinsiDataPJ.getId(), propinsiDataPJ.getNama());
//		KabupatenData kabupatenDataPJ = penanggungJawabData.getKabupaten();
//		Kabupaten kabupatenPJ = new Kabupaten(kabupatenDataPJ.getId(), kabupatenDataPJ.getNama());
//		KecamatanData kecamatanDataPJ = penanggungJawabData.getKecamatan();
//		Kecamatan kecamatanPJ = new Kecamatan(kecamatanDataPJ.getId(), kecamatanDataPJ.getNama());
//		DesaData desaDataPJ = penanggungJawabData.getDesa();
//		Desa desaPJ = new Desa(desaDataPJ.getId(), desaDataPJ.getNama());
//		Alamat alamatPJ = new Alamat(propinsiPJ, kabupatenPJ, kecamatanPJ, desaPJ, penanggungJawabData.getDetailAlamat());
//		JabatanData jabatanData = penanggungJawabData.getJabatan();
//		Jabatan jabatan = new Jabatan(jabatanData.getId(), jabatanData.getNama());
//		JenisKelaminData jenisKelaminData = penanggungJawabData.getSex();
//		JenisKelamin sex = new JenisKelamin(jenisKelaminData.getId(), jenisKelaminData.getNama());		
//		PenanggungJawab penanggungJawab = new PenanggungJawab(penanggungJawabData.getId(), penanggungJawabData.getNama(), 
//				alamatPJ, jabatan, sex, penanggungJawabData.getNomorIdentitas(), penanggungJawabData.getNomorHandphone());
//		
//		return new Pemrakarsa(d.getId(), bentukUsaha, d.getNoNibOss(), d.getNama(), d.getNamaNotaris(), 
//				alamat, d.getTelepone(), d.getFax(), d.getNoNpwp(), d.getAlamatEmail(), penanggungJawab, 
//				d.getTanggalNotaris(), d.getTanggalOss(), d.getCreator().getId());
		return null;
	}
	
	private KBLIData convertKBLIToKBData(KBLI t) {
		KBLIData kbliData = new KBLIData();
		kbliData.setKode(t.getKode());
		kbliData.setNama(t.getNama());
		return kbliData;
	}
	
	private KBLI convertKBLIDataToKBLI(KBLIData d) {
		return new KBLI(d.getKode(), d.getNama());
	}
}
