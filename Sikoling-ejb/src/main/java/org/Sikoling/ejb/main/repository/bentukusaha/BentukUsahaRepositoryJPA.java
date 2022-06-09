package org.Sikoling.ejb.main.repository.bentukusaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.BentukUsaha;
import org.Sikoling.ejb.abstraction.repository.IBentukUsahaRepository;
import jakarta.persistence.EntityManager;

public class BentukUsahaRepositoryJPA implements IBentukUsahaRepository {
	
	private final EntityManager entityManager;	

	public BentukUsahaRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public BentukUsaha save(BentukUsaha t) {
		BentukUsahaData bentukUsahaData = convertBentukUsahaToBentukUsahaData(t);
		entityManager.persist(bentukUsahaData);
		entityManager.flush();
		return convertBentukUsahaDataToBentukUsaha(bentukUsahaData);
	}

	@Override
	public BentukUsaha update(BentukUsaha t) {
		BentukUsahaData bentukUsahaData = convertBentukUsahaToBentukUsahaData(t);
		bentukUsahaData = entityManager.merge(bentukUsahaData);
		return convertBentukUsahaDataToBentukUsaha(bentukUsahaData);
	}

	@Override
	public List<BentukUsaha> getAll() {
		return entityManager.createNamedQuery("BentukUsahaData.findAll", BentukUsahaData.class)
				.getResultList()
				.stream()
				.map(t -> convertBentukUsahaDataToBentukUsaha(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<BentukUsaha> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("BentukUsahaData.findAll", BentukUsahaData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertBentukUsahaDataToBentukUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<BentukUsaha> getByQueryNama(String nama) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("BentukUsahaData.findAllByQueryNama", BentukUsahaData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertBentukUsahaDataToBentukUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<BentukUsaha> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("BentukUsahaData.findAllByQueryNama", BentukUsahaData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertBentukUsahaDataToBentukUsaha(t))
				.collect(Collectors.toList());
	}

	private BentukUsahaData convertBentukUsahaToBentukUsahaData(BentukUsaha bentukUsaha) {
		return new BentukUsahaData(bentukUsaha.getId(), bentukUsaha.getNama(), bentukUsaha.getIdKelompokBentukUsaha());
	}
	
	private BentukUsaha convertBentukUsahaDataToBentukUsaha(BentukUsahaData bentukUsahaData) {
		return new BentukUsaha(bentukUsahaData.getId(), bentukUsahaData.getNama(), bentukUsahaData.getKelompokBentukUsaha());
	}
}
