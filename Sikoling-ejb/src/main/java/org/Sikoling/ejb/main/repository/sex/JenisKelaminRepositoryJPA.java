package org.Sikoling.ejb.main.repository.sex;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.repository.IJenisKelaminRepository;

import jakarta.persistence.EntityManager;

public class JenisKelaminRepositoryJPA implements IJenisKelaminRepository {
	
	private EntityManager entityManager;

	public JenisKelaminRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public JenisKelamin save(JenisKelamin t) {
		JenisKelaminData jenisKelaminData = convertJenisKelaminToJenisKelaminData(t);
		entityManager.persist(t);
		entityManager.flush();
		return convertJenisKelaminDataToJenisKelamin(jenisKelaminData);
	}

	@Override
	public JenisKelamin update(JenisKelamin t) {
		JenisKelaminData jenisKelaminData = convertJenisKelaminToJenisKelaminData(t);
		jenisKelaminData = entityManager.merge(jenisKelaminData);
		return convertJenisKelaminDataToJenisKelamin(jenisKelaminData);
	}

	@Override
	public List<JenisKelamin> getAll() {
		return entityManager.createNamedQuery("JenisKelaminData.findAll", JenisKelaminData.class)
				.getResultList()
				.stream()
				.map(t -> convertJenisKelaminDataToJenisKelamin(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<JenisKelamin> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("JenisKelaminData.findAll", JenisKelaminData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertJenisKelaminDataToJenisKelamin(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<JenisKelamin> getByQueryNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("JenisKelaminData.findAllByQueryNama", JenisKelaminData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertJenisKelaminDataToJenisKelamin(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<JenisKelamin> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("JenisKelaminData.findAllByQueryNama", JenisKelaminData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertJenisKelaminDataToJenisKelamin(t))
				.collect(Collectors.toList());
	}

	private JenisKelaminData convertJenisKelaminToJenisKelaminData(JenisKelamin jenisKelamin) {
		JenisKelaminData jenisKelaminData = new JenisKelaminData();
		jenisKelaminData.setId(jenisKelamin.getId());
		jenisKelaminData.setNama(jenisKelamin.getNama());
		return jenisKelaminData;
	}
	
	private JenisKelamin convertJenisKelaminDataToJenisKelamin(JenisKelaminData jenisKelaminData) {
		return new JenisKelamin(jenisKelaminData.getId(), jenisKelaminData.getNama());
	}
}
