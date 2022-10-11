package org.Sikoling.ejb.main.repository.perusahaan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.DetailPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.DokumenPerusahaan;
import org.Sikoling.ejb.abstraction.entity.JenisPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.Kontak;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.repository.IPerusahaanRepository;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.DetailPelakuUsahaData;
import org.Sikoling.ejb.main.repository.pelakuusaha.JenisPelakuUsahaData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.persistence.EntityManager;


public class PerusahaanRepositoryJPA implements IPerusahaanRepository {
	
	private final EntityManager entityManager;	

	public PerusahaanRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<Perusahaan> getAll() {
		return entityManager.createNamedQuery("PerusahaanData.findAll", PerusahaanData.class)
				.getResultList()
				.stream()
				.map(t -> convertPerusahaanDataToPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public Perusahaan save(Perusahaan t) {
		PerusahaanData perusahaanData = convertPerusahaanToPerusahaanData(t);		
		entityManager.persist(perusahaanData);
		entityManager.flush();		
		return convertPerusahaanDataToPerusahaan(perusahaanData);
	}

	@Override
	public Perusahaan update(Perusahaan t) {
		PerusahaanData pemrakarsaData = convertPerusahaanToPerusahaanData(t);
		pemrakarsaData = entityManager.merge(pemrakarsaData);
		return convertPerusahaanDataToPerusahaan(pemrakarsaData);
	}

	@Override
	public List<Perusahaan> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("PerusahaanData.findAll", PerusahaanData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPerusahaanDataToPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Perusahaan> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("PerusahaanData.findByQueryNama", PerusahaanData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertPerusahaanDataToPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Perusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("PerusahaanData.findByQueryNama", PerusahaanData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPerusahaanDataToPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Perusahaan> getByCreator(String idCreator) {
		return entityManager.createNamedQuery("PerusahaanData.findByCreator", PerusahaanData.class)
				.setParameter("idCreator", idCreator)
				.getResultList()
				.stream()
				.map(t -> convertPerusahaanDataToPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Perusahaan> getByCreatorAndPage(String idCreator, Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("PerusahaanData.findByCreator", PerusahaanData.class)
				.setParameter("idCreator", idCreator)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPerusahaanDataToPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Perusahaan> getByCreatorAndNama(String idCreator, String nama) {
		return entityManager.createNamedQuery("PerusahaanData.findByCreatorAndNama", PerusahaanData.class)
				.setParameter("idCreator", idCreator)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertPerusahaanDataToPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Perusahaan> getByCreatorAndNamaAndPage(String idCreator, String nama, Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("PerusahaanData.findByCreatorAndNama", PerusahaanData.class)
				.setParameter("idCreator", idCreator)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPerusahaanDataToPerusahaan(t))
				.collect(Collectors.toList());
	}

	private PerusahaanData convertPerusahaanToPerusahaanData(Perusahaan p) {
		PerusahaanData perusahaanData = new PerusahaanData();		
		perusahaanData.setId(p.getId());
		perusahaanData.setNama(p.getNama());
		
		ModelPerizinanData modelPerizinanData = new ModelPerizinanData();
		modelPerizinanData.setId(p.getModelPerizinan().getId());
		modelPerizinanData.setNama(p.getModelPerizinan().getNama());
		modelPerizinanData.setSingkatan(p.getModelPerizinan().getSingkatan());
		perusahaanData.setModelPerizinanData(modelPerizinanData);
		
		SkalaUsahaData skalaUsahaData = new SkalaUsahaData();
		skalaUsahaData.setId(p.getSkalaUsaha().getId());
		skalaUsahaData.setNama(p.getSkalaUsaha().getNama());
		skalaUsahaData.setSingkatan(p.getSkalaUsaha().getSingkatan());
		perusahaanData.setSkalaUsaha(skalaUsahaData);
		
		JenisPelakuUsahaData jenisPelakuUsahaData = new JenisPelakuUsahaData();
		jenisPelakuUsahaData.setId(p.getJenisPelakuUsaha().getId());
		jenisPelakuUsahaData.setNama(p.getJenisPelakuUsaha().getNama());
		perusahaanData.setJenisPelakuUsahaData(jenisPelakuUsahaData);
		
		DetailPelakuUsahaData detailPelakuUsahaData = new DetailPelakuUsahaData();
		detailPelakuUsahaData.setNama(p.getDetailPelakuUsaha().getNama());
		detailPelakuUsahaData.setSingkatan(p.getDetailPelakuUsaha().getSingkatan());
		detailPelakuUsahaData.setJenisPelakuUsahaData(jenisPelakuUsahaData);
		perusahaanData.setDetailPelakuUsahaData(detailPelakuUsahaData);
				
		AlamatPerusahaanData alamatPerusahaanData = new AlamatPerusahaanData();
		DesaData desaData = new DesaData();
		desaData.setId(p.getAlamat().getDesa().getId());
		alamatPerusahaanData.setDesa(desaData);
		KecamatanData kecamatanData = new KecamatanData();
		kecamatanData.setId(p.getAlamat().getKecamatan().getId());
		alamatPerusahaanData.setKecamatan(kecamatanData);
		KabupatenData kabupatenData = new KabupatenData();
		kabupatenData.setId(p.getAlamat().getKabupaten().getId());
		alamatPerusahaanData.setKabupaten(kabupatenData);
		PropinsiData propinsiData = new PropinsiData();
		propinsiData.setId(p.getAlamat().getPropinsi().getId());
		alamatPerusahaanData.setPropinsi(propinsiData);
		perusahaanData.setAlamatPerusahaanData(alamatPerusahaanData);
		
		KontakData kontakPerusahaanData = new KontakData();
		kontakPerusahaanData.setEmail(p.getKontak().getEmail());
		kontakPerusahaanData.setFax(p.getKontak().getFax());
		kontakPerusahaanData.setTelepone(p.getKontak().getTelepone());
		perusahaanData.setKontakPerusahaanData(kontakPerusahaanData);		
		
		Jsonb jsonb = JsonbBuilder.create();
		String dokumen = jsonb.toJson(p.getDaftarDokumen());
		perusahaanData.setDokumen(dokumen);		
		
//		UserData userData = new UserData();
//		userData.setId("0001");
//		perusahaanData.setCreator(userData);
		
		return perusahaanData;
	}
	
	private Perusahaan convertPerusahaanDataToPerusahaan(PerusahaanData d) {
		
		ModelPerizinan modelPerizinan = new ModelPerizinan(
				d.getModelPerizinanData().getId(), d.getModelPerizinanData().getNama(), 
				d.getModelPerizinanData().getSingkatan());	
		
		SkalaUsaha skalaUsaha = new SkalaUsaha(
				d.getSkalaUsaha().getId(), d.getSkalaUsaha().getNama(), 
				d.getSkalaUsaha().getSingkatan());
		
		JenisPelakuUsaha pelakuUsaha = new JenisPelakuUsaha(
				d.getJenisPelakuUsahaData().getId(), d.getJenisPelakuUsahaData().getNama());
		
		DetailPelakuUsaha detailPelakuUsaha = new DetailPelakuUsaha(
				d.getDetailPelakuUsahaData().getId(), d.getDetailPelakuUsahaData().getNama(), d.getDetailPelakuUsahaData().getSingkatan(), pelakuUsaha);
		
		Alamat alamatPerusahaan = new Alamat(
				new Propinsi(d.getAlamatPerusahaanData().getPropinsi().getId(), d.getAlamatPerusahaanData().getPropinsi().getNama()),
				new Kabupaten(d.getAlamatPerusahaanData().getKabupaten().getId(), d.getAlamatPerusahaanData().getKabupaten().getNama()),
				new Kecamatan(d.getAlamatPerusahaanData().getKecamatan().getId(), d.getAlamatPerusahaanData().getKecamatan().getNama()),
				new Desa(d.getAlamatPerusahaanData().getDesa().getId(), d.getAlamatPerusahaanData().getDesa().getNama()),
				d.getAlamatPerusahaanData().getKeterangan());
		
		Kontak kontakPerusahaan = new Kontak(d.getKontakPerusahaanData().getTelepone(), 
				d.getKontakPerusahaanData().getFax(), d.getKontakPerusahaanData().getEmail());
		
		Jsonb jsonb = JsonbBuilder.create();
		
		@SuppressWarnings("serial")
		List<DokumenPerusahaan> daftarDokumen = jsonb.fromJson(d.getDokumen(), 
				new ArrayList<DokumenPerusahaan>(){}.getClass().getGenericSuperclass());
		

		return new Perusahaan( 
				d.getId(), d.getNama(), modelPerizinan, skalaUsaha, pelakuUsaha,
				detailPelakuUsaha, alamatPerusahaan, kontakPerusahaan, daftarDokumen);
	}
	
}
