package org.Sikoling.ejb.main.repository.perusahaan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Dokumen;
import org.Sikoling.ejb.abstraction.entity.DokumenOss;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.KategoriDokumen;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.Kontak;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.repository.IPerusahaanRepository;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.dokumen.DokumenData;
import org.Sikoling.ejb.main.repository.dokumen.RegisterDokumenData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.pelakuusaha.KategoriPelakuUsahaData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;
import jakarta.persistence.EntityManager;


public class PerusahaanRepositoryJPA implements IPerusahaanRepository {
	
	private final EntityManager entityManager;	

	public PerusahaanRepositoryJPA(EntityManager entityManager) {
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
	public Perusahaan updateStatusVerifikasi(Perusahaan t, boolean statusVerifikasi) {
		PerusahaanData perusahaanData = convertPerusahaanToPerusahaanData(t);
		perusahaanData.setStatusVerifikasi(statusVerifikasi);
		perusahaanData = entityManager.merge(perusahaanData);
		return convertPerusahaanDataToPerusahaan(perusahaanData);
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
		
		AlamatPerusahaanData alamatPerusahaanData = new AlamatPerusahaanData();
		alamatPerusahaanData.setKeterangan(p.getAlamat().getKeterangan());
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
		
		ModelPerizinanData modelPerizinanData = new ModelPerizinanData();
		modelPerizinanData.setId(p.getModelPerizinan().getId());
		modelPerizinanData.setNama(p.getModelPerizinan().getNama());
		modelPerizinanData.setSingkatan(p.getModelPerizinan().getSingkatan());
		
		SkalaUsahaData skalaUsahaData = new SkalaUsahaData();
		skalaUsahaData.setId(p.getSkalaUsaha().getId());
		skalaUsahaData.setNama(p.getSkalaUsaha().getNama());
		skalaUsahaData.setSingkatan(p.getSkalaUsaha().getSingkatan());
		
		KategoriPelakuUsahaData jenisPelakuUsahaData = new KategoriPelakuUsahaData();
		jenisPelakuUsahaData.setId(p.getPelakuUsaha().getKategoriPelakuUsaha().getId());
		jenisPelakuUsahaData.setNama(p.getPelakuUsaha().getKategoriPelakuUsaha().getNama());
		
		PelakuUsahaData detailPelakuUsahaData = new PelakuUsahaData();
		detailPelakuUsahaData.setId(p.getPelakuUsaha().getId());
		detailPelakuUsahaData.setNama(p.getPelakuUsaha().getNama());
		detailPelakuUsahaData.setSingkatan(p.getPelakuUsaha().getSingkatan());
		detailPelakuUsahaData.setKategoriPelakuUsahaData(jenisPelakuUsahaData);
		
		KontakPerusahaanData kontakPerusahaanData = new KontakPerusahaanData();
		kontakPerusahaanData.setEmail(p.getKontak().getEmail());
		kontakPerusahaanData.setFax(p.getKontak().getFax());
		kontakPerusahaanData.setTelepone(p.getKontak().getTelepone());	

		PerusahaanData perusahaanData = new PerusahaanData();		
		perusahaanData.setId(p.getId());
		perusahaanData.setNama(p.getNama());
		perusahaanData.setAlamatPerusahaanData(alamatPerusahaanData);
		perusahaanData.setModelPerizinanData(modelPerizinanData);
		perusahaanData.setSkalaUsaha(skalaUsahaData);
		perusahaanData.setPelakuUsahaData(detailPelakuUsahaData);
		perusahaanData.setKontakPerusahaanData(kontakPerusahaanData);	
		
		
		return perusahaanData;
	}
	
	private Perusahaan convertPerusahaanDataToPerusahaan(PerusahaanData d) {
		
		ModelPerizinan modelPerizinan = new ModelPerizinan(
				d.getModelPerizinanData().getId(), d.getModelPerizinanData().getNama(), 
				d.getModelPerizinanData().getSingkatan());	
		
		SkalaUsaha skalaUsaha = new SkalaUsaha(
				d.getSkalaUsaha().getId(), d.getSkalaUsaha().getNama(), 
				d.getSkalaUsaha().getSingkatan());
		
		KategoriPelakuUsaha kategoriPelakuUsaha = new KategoriPelakuUsaha(
				d.getPelakuUsahaData().getKategoriPelakuUsahaData().getId(), 
				d.getPelakuUsahaData().getKategoriPelakuUsahaData().getNama());
		
		PelakuUsaha pelakuUsaha = new PelakuUsaha(
				d.getPelakuUsahaData().getId(), d.getPelakuUsahaData().getNama(), d.getPelakuUsahaData().getSingkatan(), kategoriPelakuUsaha);
		
		Alamat alamatPerusahaan = new Alamat(
				new Propinsi(d.getAlamatPerusahaanData().getPropinsi().getId(), d.getAlamatPerusahaanData().getPropinsi().getNama()),
				new Kabupaten(d.getAlamatPerusahaanData().getKabupaten().getId(), d.getAlamatPerusahaanData().getKabupaten().getNama()),
				new Kecamatan(d.getAlamatPerusahaanData().getKecamatan().getId(), d.getAlamatPerusahaanData().getKecamatan().getNama()),
				new Desa(d.getAlamatPerusahaanData().getDesa().getId(), d.getAlamatPerusahaanData().getDesa().getNama()),
				d.getAlamatPerusahaanData().getKeterangan());
		
		Kontak kontakPerusahaan = new Kontak(d.getKontakPerusahaanData().getTelepone(), 
				d.getKontakPerusahaanData().getFax(), d.getKontakPerusahaanData().getEmail());
		List<RegisterDokumenData> daftarItemRegisterDokumenData = d.getDaftarItemRegisterDokumenData();
		List<Dokumen> daftarDokumen = new ArrayList<Dokumen>();
		
		for(RegisterDokumenData item : daftarItemRegisterDokumenData) {
			DokumenData dokumenData = item.getDokumen();
			
			switch (dokumenData.getId()) {
			case "010301":				
				DokumenOss dokumenOss = new DokumenOss(
						dokumenData.getId(), 
						dokumenData.getNama(), 
						new KategoriDokumen(dokumenData.getKategori().getId(), null, null), 
						item.getRegisterDokumenOssData().getNib(), 
						null, 
						null);
				daftarDokumen.add(dokumenOss);
				break;
			default:
				break;
			}			
		}
		
		return new Perusahaan( 
				d.getId(), d.getNama(), modelPerizinan, skalaUsaha, 
				pelakuUsaha, alamatPerusahaan, kontakPerusahaan, daftarDokumen);
	}
	
	@Override
	public Boolean getById(String id) {
		PerusahaanData perusahaanData = entityManager.find(PerusahaanData.class, id);
		if(perusahaanData == null) {
			return false;
		}
		else {
			return true;
		}		 
	}
	
}
