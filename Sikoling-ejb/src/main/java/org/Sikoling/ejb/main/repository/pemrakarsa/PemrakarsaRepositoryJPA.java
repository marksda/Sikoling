package org.Sikoling.ejb.main.repository.pemrakarsa;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.BentukUsaha;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.Pemrakarsa;
import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.repository.IPemrakarsaRepository;
import org.Sikoling.ejb.main.repository.bentukusaha.BentukUsahaData;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.jabatan.JabatanData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.penanggungjawab.PenanggungJawabData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminData;
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
		pemrakarsaData.setAlamatEmail(p.getEmail());
		BentukUsahaData bentukUsahaData = new BentukUsahaData();
		bentukUsahaData.setId(p.getBentukUsaha().getId());
		pemrakarsaData.setBentukUsaha(bentukUsahaData);
		pemrakarsaData.setNama(p.getNama());
		pemrakarsaData.setNamaNotaris(p.getNamaNotaris());
		PenanggungJawabData penanggungJawabData = new PenanggungJawabData();
		penanggungJawabData.setId(p.getPenanggungJawab().getId());
		pemrakarsaData.setPenanggungJawab(penanggungJawabData);
		pemrakarsaData.setNoNibOss(p.getNomorIndukBerusaha());
		pemrakarsaData.setNoNpwp(p.getNpwp());
		pemrakarsaData.setTanggalNotaris(p.getTanggalNotaris());
		pemrakarsaData.setTanggalOss(p.getTanggalOSS());
		pemrakarsaData.setTelepone(p.getTelepone());
		pemrakarsaData.setFax(p.getFax());
		UserData userData = new UserData();
		userData.setId(p.getIdCreator());
		pemrakarsaData.setCreator(userData);
		
		return pemrakarsaData;
	}
	
	private Pemrakarsa convertPemrakarsaDataToPemrakarsa(PemrakarsaData d) {
		BentukUsaha bentukUsaha = new BentukUsaha(d.getBentukUsaha().getId(), d.getBentukUsaha().getNama());
		PropinsiData propinsiData = d.getAlamatPemrakarsaDatas().get(0).getPropinsi();
		Propinsi propinsi = new Propinsi(propinsiData.getId(), propinsiData.getNama());
		KabupatenData kabupatenData = d.getAlamatPemrakarsaDatas().get(0).getKabupaten();
		Kabupaten kabupaten = new Kabupaten(kabupatenData.getId(), kabupatenData.getNama());
		KecamatanData kecamatanData = d.getAlamatPemrakarsaDatas().get(0).getKecamatan();
		Kecamatan kecamatan = new Kecamatan(kecamatanData.getId(), kecamatanData.getNama());
		DesaData desaData = d.getAlamatPemrakarsaDatas().get(0).getDesa();
		Desa desa = new Desa(desaData.getId(), desaData.getNama());
		Alamat alamat = new Alamat(propinsi, kabupaten, kecamatan, desa, d.getAlamatPemrakarsaDatas().get(0).getKeterangan());
		PenanggungJawabData penanggungJawabData = d.getPenanggungJawab();
		PropinsiData propinsiDataPJ = penanggungJawabData.getPropinsi();
		Propinsi propinsiPJ = new Propinsi(propinsiDataPJ.getId(), propinsiDataPJ.getNama());
		KabupatenData kabupatenDataPJ = penanggungJawabData.getKabupaten();
		Kabupaten kabupatenPJ = new Kabupaten(kabupatenDataPJ.getId(), kabupatenDataPJ.getNama());
		KecamatanData kecamatanDataPJ = penanggungJawabData.getKecamatan();
		Kecamatan kecamatanPJ = new Kecamatan(kecamatanDataPJ.getId(), kecamatanDataPJ.getNama());
		DesaData desaDataPJ = penanggungJawabData.getDesa();
		Desa desaPJ = new Desa(desaDataPJ.getId(), desaDataPJ.getNama());
		Alamat alamatPJ = new Alamat(propinsiPJ, kabupatenPJ, kecamatanPJ, desaPJ, penanggungJawabData.getDetailAlamat());
		JabatanData jabatanData = penanggungJawabData.getJabatan();
		Jabatan jabatan = new Jabatan(jabatanData.getId(), jabatanData.getNama());
		JenisKelaminData jenisKelaminData = penanggungJawabData.getSex();
		JenisKelamin sex = new JenisKelamin(jenisKelaminData.getId(), jenisKelaminData.getNama());		
		PenanggungJawab penanggungJawab = new PenanggungJawab(penanggungJawabData.getId(), penanggungJawabData.getNama(), 
				alamatPJ, jabatan, sex, penanggungJawabData.getNomorIdentitas(), penanggungJawabData.getNomorHandphone());
		
		return new Pemrakarsa(d.getId(), bentukUsaha, d.getNoNibOss(), d.getNama(), d.getNamaNotaris(), 
				alamat, d.getTelepone(), d.getFax(), d.getNoNpwp(), d.getAlamatEmail(), penanggungJawab, 
				d.getTanggalNotaris(), d.getTanggalOss(), d.getCreator().getId());
	}
}
