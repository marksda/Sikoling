package org.Sikoling.ejb.main.repository.hakakses;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.HakAkses;
import org.Sikoling.ejb.abstraction.repository.IHakAksesRepository;
import jakarta.persistence.EntityManager;

public class HakAksesRepositoryJPA implements IHakAksesRepository {
	
	private final EntityManager entityManager;

	public HakAksesRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

	@Override
	public List<HakAkses> getAll() {
		return entityManager.createNamedQuery("HakAksesData.findAll", HakAksesData.class)
				.getResultList()
				.stream()
				.map(d -> convertHakAksesDataToHakAkses(d))
				.collect(Collectors.toList());
	}

	@Override
	public HakAkses save(HakAkses t) {
		HakAksesData hakAksesData = convertHakAksesToHakAksesData(t);
		entityManager.persist(hakAksesData);
		entityManager.flush();
		return convertHakAksesDataToHakAkses(hakAksesData);
	}

	@Override
	public HakAkses update(HakAkses t) {
		HakAksesData hakAksesData = convertHakAksesToHakAksesData(t);
		hakAksesData = entityManager.merge(hakAksesData);
		
		return convertHakAksesDataToHakAkses(hakAksesData);
	}

	@Override
	public List<HakAkses> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("HakAksesData.findAll", HakAksesData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> convertHakAksesDataToHakAkses(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<HakAkses> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("HakAksesData.findAll", HakAksesData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(d -> convertHakAksesDataToHakAkses(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<HakAkses> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("HakAksesData.findAll", HakAksesData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> convertHakAksesDataToHakAkses(d))
				.collect(Collectors.toList());
	}

	private HakAkses convertHakAksesDataToHakAkses(HakAksesData d) {
		return new HakAkses(d.getId(), d.getNama(), d.getKeterangan());
	}
	
	private HakAksesData convertHakAksesToHakAksesData(HakAkses t) {
		HakAksesData hakAksesData = new HakAksesData();
		hakAksesData.setId(t.getId());
		hakAksesData.setNama(t.getNama());
		hakAksesData.setKeterangan(t.getKeterangan());
		
		return hakAksesData;
	}
}
