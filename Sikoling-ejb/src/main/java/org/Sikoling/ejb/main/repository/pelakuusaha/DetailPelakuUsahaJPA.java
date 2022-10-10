package org.Sikoling.ejb.main.repository.pelakuusaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DetailPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.JenisPelakuUsaha;
import org.Sikoling.ejb.abstraction.repository.IDetailPelakuUsahaRepository;

import jakarta.persistence.EntityManager;

public class DetailPelakuUsahaJPA implements IDetailPelakuUsahaRepository {
	
	private final EntityManager entityManager;
	
	public DetailPelakuUsahaJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

	@Override
	public List<DetailPelakuUsaha> getAll() {
		return entityManager.createNamedQuery("DetailPelakuUsahaData.findAll", DetailPelakuUsahaData.class)
				.getResultList()
				.stream()
				.map(t -> convertDetailPelakuUsahaDataToDetailPelakuUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public DetailPelakuUsaha save(DetailPelakuUsaha t, JenisPelakuUsaha s) {
		DetailPelakuUsahaData detailPelakuUsahaData = converterDetailPelakuUsahaToDetailPelakuUsahaData(t, s);
		entityManager.persist(detailPelakuUsahaData);
		entityManager.flush();
		
		return convertDetailPelakuUsahaDataToDetailPelakuUsaha(detailPelakuUsahaData);
	}

	@Override
	public DetailPelakuUsaha update(DetailPelakuUsaha t, JenisPelakuUsaha s) {
		DetailPelakuUsahaData detailPelakuUsahaData = converterDetailPelakuUsahaToDetailPelakuUsahaData(t, s);
		detailPelakuUsahaData = entityManager.merge(detailPelakuUsahaData);
		return convertDetailPelakuUsahaDataToDetailPelakuUsaha(detailPelakuUsahaData);
	}

	@Override
	public List<DetailPelakuUsaha> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("DetailPelakuUsahaData.findAll", DetailPelakuUsahaData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertDetailPelakuUsahaDataToDetailPelakuUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<DetailPelakuUsaha> getByNama(String nama) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("DetailPelakuUsahaData.findByQueryNama", DetailPelakuUsahaData.class)
				.setParameter("nama", nama)				
				.getResultList()
				.stream()
				.map(t -> convertDetailPelakuUsahaDataToDetailPelakuUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<DetailPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
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

	private DetailPelakuUsaha convertDetailPelakuUsahaDataToDetailPelakuUsaha(DetailPelakuUsahaData d) {
		return new DetailPelakuUsaha(d.getId(), d.getNama(), d.getSingkatan());
	}
	
	private DetailPelakuUsahaData converterDetailPelakuUsahaToDetailPelakuUsahaData(DetailPelakuUsaha t, JenisPelakuUsaha pelakuUsaha) {
		DetailPelakuUsahaData detailPelakuUsahaData = new DetailPelakuUsahaData();
		detailPelakuUsahaData.setId(t.getId());
		detailPelakuUsahaData.setNama(t.getNama());
		detailPelakuUsahaData.setSingkatan(t.getSingkatan());
		
		JenisPelakuUsahaData jenisPelakuUsahaData = new JenisPelakuUsahaData();
		jenisPelakuUsahaData.setId(pelakuUsaha.getId());
		detailPelakuUsahaData.setJenisPelakuUsahaData(jenisPelakuUsahaData);
		
		return detailPelakuUsahaData;
		
	}
}
