package org.Sikoling.ejb.main.repository.pelakuusaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.repository.IPelakuUsahaRepository;

import jakarta.persistence.EntityManager;

public class PelakuUsahaRepositoryJPA implements IPelakuUsahaRepository {
	
	private final EntityManager entityManager;
	
	public PelakuUsahaRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<PelakuUsaha> getAll() {
		return entityManager.createNamedQuery("PelakuUsahaData.findAll", PelakuUsahaData.class)
				.getResultList()
				.stream()
				.map(t -> convertPelakuUsahaDataToPelakuUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public PelakuUsaha save(PelakuUsaha t) {
		PelakuUsahaData pelakuUsahaData = converterPelakuUsahaToPelakuUsahaData(t);
		entityManager.persist(pelakuUsahaData);
		entityManager.flush();
		
		return convertPelakuUsahaDataToPelakuUsaha(pelakuUsahaData);
	}

	@Override
	public PelakuUsaha update(PelakuUsaha t) {
		PelakuUsahaData detailPelakuUsahaData = converterPelakuUsahaToPelakuUsahaData(t);
		detailPelakuUsahaData = entityManager.merge(detailPelakuUsahaData);
		return convertPelakuUsahaDataToPelakuUsaha(detailPelakuUsahaData);
	}

	@Override
	public List<PelakuUsaha> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("PelakuUsahaData.findAll", PelakuUsahaData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPelakuUsahaDataToPelakuUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<PelakuUsaha> getByNama(String nama) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("PelakuUsahaData.findByQueryNama", PelakuUsahaData.class)
				.setParameter("nama", nama)				
				.getResultList()
				.stream()
				.map(t -> convertPelakuUsahaDataToPelakuUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<PelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("PelakuUsahaData.findByQueryNama", PelakuUsahaData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPelakuUsahaDataToPelakuUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<PelakuUsaha> getByKategoriPelakuUsaha(KategoriPelakuUsaha kategoriPelakuUsaha) {
		return entityManager.createNamedQuery("PelakuUsahaData.findByKategoriPelakuUsaha", PelakuUsahaData.class)
				.setParameter("idKategoriPelakuUsaha", kategoriPelakuUsaha.getId())
				.getResultList()
				.stream()
				.map(t -> convertPelakuUsahaDataToPelakuUsaha(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<PelakuUsaha> getByKategoriPelakuUsahaAndPage(KategoriPelakuUsaha kategoriPelakuUsaha, Integer page,
			Integer pageSize) {
		return entityManager.createNamedQuery("PelakuUsahaData.findByKategoriPelakuUsaha", PelakuUsahaData.class)
				.setParameter("kategoriPelakuUsaha", kategoriPelakuUsaha)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPelakuUsahaDataToPelakuUsaha(t))
				.collect(Collectors.toList());
	}
	
	private PelakuUsaha convertPelakuUsahaDataToPelakuUsaha(PelakuUsahaData d) {
		KategoriPelakuUsaha kategoriPelakuUsaha = new KategoriPelakuUsaha(d.getKategoriPelakuUsahaData().getId(), d.getKategoriPelakuUsahaData().getNama());
		
		return new PelakuUsaha(d.getId(), d.getNama(), d.getSingkatan(), kategoriPelakuUsaha);
	}
	
	private PelakuUsahaData converterPelakuUsahaToPelakuUsahaData(PelakuUsaha t) {
		PelakuUsahaData pelakuUsahaData = new PelakuUsahaData();
		pelakuUsahaData.setId(t.getId());
		pelakuUsahaData.setNama(t.getNama());
		pelakuUsahaData.setSingkatan(t.getSingkatan());
		
		KategoriPelakuUsahaData kategoriPelakuUsahaData = new KategoriPelakuUsahaData();
		KategoriPelakuUsaha kategoriPelakuUsaha = t.getKategoriPelakuUsaha();		
		kategoriPelakuUsahaData.setId(kategoriPelakuUsaha.getId());
		kategoriPelakuUsahaData.setNama(kategoriPelakuUsaha.getNama());
		
		pelakuUsahaData.setKategoriPelakuUsahaData(kategoriPelakuUsahaData);
		
		return pelakuUsahaData;
		
	}
		
}
