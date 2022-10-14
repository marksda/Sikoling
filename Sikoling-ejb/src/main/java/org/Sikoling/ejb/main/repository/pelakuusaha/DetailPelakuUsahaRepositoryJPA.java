package org.Sikoling.ejb.main.repository.pelakuusaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.repository.IDetailPelakuUsahaRepository;

import jakarta.persistence.EntityManager;

public class DetailPelakuUsahaRepositoryJPA implements IDetailPelakuUsahaRepository {
	
	private final EntityManager entityManager;
	
	public DetailPelakuUsahaRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<PelakuUsaha> getAll() {
		return entityManager.createNamedQuery("DetailPelakuUsahaData.findAll", DetailPelakuUsahaData.class)
				.getResultList()
				.stream()
				.map(t -> convertDetailPelakuUsahaDataToDetailPelakuUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public PelakuUsaha save(PelakuUsaha t) {
		DetailPelakuUsahaData detailPelakuUsahaData = converterDetailPelakuUsahaToDetailPelakuUsahaData(t, t.getKategoriPelakuUsaha());
		entityManager.persist(detailPelakuUsahaData);
		entityManager.flush();
		
		return convertDetailPelakuUsahaDataToDetailPelakuUsaha(detailPelakuUsahaData);
	}

	@Override
	public PelakuUsaha update(PelakuUsaha t) {
		DetailPelakuUsahaData detailPelakuUsahaData = converterDetailPelakuUsahaToDetailPelakuUsahaData(t, t.getKategoriPelakuUsaha());
		detailPelakuUsahaData = entityManager.merge(detailPelakuUsahaData);
		return convertDetailPelakuUsahaDataToDetailPelakuUsaha(detailPelakuUsahaData);
	}

	@Override
	public List<PelakuUsaha> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("DetailPelakuUsahaData.findAll", DetailPelakuUsahaData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertDetailPelakuUsahaDataToDetailPelakuUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<PelakuUsaha> getByNama(String nama) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("DetailPelakuUsahaData.findByQueryNama", DetailPelakuUsahaData.class)
				.setParameter("nama", nama)				
				.getResultList()
				.stream()
				.map(t -> convertDetailPelakuUsahaDataToDetailPelakuUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<PelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("DetailPelakuUsahaData.findByQueryNama", DetailPelakuUsahaData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertDetailPelakuUsahaDataToDetailPelakuUsaha(t))
				.collect(Collectors.toList());
	}

	private PelakuUsaha convertDetailPelakuUsahaDataToDetailPelakuUsaha(DetailPelakuUsahaData d) {
		KategoriPelakuUsaha jenisPelakuUsaha = new KategoriPelakuUsaha(d.getJenisPelakuUsahaData().getId(), d.getJenisPelakuUsahaData().getNama());
		return new PelakuUsaha(d.getId(), d.getNama(), d.getSingkatan(), jenisPelakuUsaha);
	}
	
	private DetailPelakuUsahaData converterDetailPelakuUsahaToDetailPelakuUsahaData(PelakuUsaha t, KategoriPelakuUsaha pelakuUsaha) {
		DetailPelakuUsahaData detailPelakuUsahaData = new DetailPelakuUsahaData();
		detailPelakuUsahaData.setId(t.getId());
		detailPelakuUsahaData.setNama(t.getNama());
		detailPelakuUsahaData.setSingkatan(t.getSingkatan());
		
		KategoriPelakuUsahaData jenisPelakuUsahaData = new KategoriPelakuUsahaData();
		jenisPelakuUsahaData.setId(pelakuUsaha.getId());
		detailPelakuUsahaData.setJenisPelakuUsahaData(jenisPelakuUsahaData);
		
		return detailPelakuUsahaData;
		
	}
}
