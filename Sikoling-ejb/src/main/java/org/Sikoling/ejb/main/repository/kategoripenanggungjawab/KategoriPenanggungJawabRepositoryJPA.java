package org.Sikoling.ejb.main.repository.kategoripenanggungjawab;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.KategoriPenanggungJawab;
import org.Sikoling.ejb.abstraction.repository.IKategoriPenanggungJawabRepository;

import jakarta.persistence.EntityManager;

public class KategoriPenanggungJawabRepositoryJPA implements IKategoriPenanggungJawabRepository {
	
	private final EntityManager entityManager;	

	public KategoriPenanggungJawabRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public KategoriPenanggungJawab save(KategoriPenanggungJawab t) {
		KategoriPenanggungJawabData kategoriPenanggungJawabData = convertKategoriPenanggungJawabToKategoriPenanggungJawabData(t);
		entityManager.persist(kategoriPenanggungJawabData);
		entityManager.flush();
		return convertKategoriPenanggungJawabDataToKategoriPenanggungJawab(kategoriPenanggungJawabData);
	}

	@Override
	public KategoriPenanggungJawab update(KategoriPenanggungJawab t) {
		KategoriPenanggungJawabData kategoriPenanggungJawabData = convertKategoriPenanggungJawabToKategoriPenanggungJawabData(t);
		kategoriPenanggungJawabData = entityManager.merge(kategoriPenanggungJawabData);
		return convertKategoriPenanggungJawabDataToKategoriPenanggungJawab(kategoriPenanggungJawabData);
	}

	@Override
	public List<KategoriPenanggungJawab> getAll() {
		return entityManager.createNamedQuery("KategoriPenanggungJawabData.findAll", KategoriPenanggungJawabData.class)
				.getResultList()
				.stream()
				.map(t -> convertKategoriPenanggungJawabDataToKategoriPenanggungJawab(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<KategoriPenanggungJawab> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("KategoriPenanggungJawabData.findAll", KategoriPenanggungJawabData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKategoriPenanggungJawabDataToKategoriPenanggungJawab(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<KategoriPenanggungJawab> getByQueryNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("KategoriPenanggungJawabData.findAllByQueryNama", KategoriPenanggungJawabData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertKategoriPenanggungJawabDataToKategoriPenanggungJawab(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<KategoriPenanggungJawab> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("KategoriPenanggungJawabData.findAllByQueryNama", KategoriPenanggungJawabData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKategoriPenanggungJawabDataToKategoriPenanggungJawab(t))
				.collect(Collectors.toList());
	}

	private KategoriPenanggungJawabData convertKategoriPenanggungJawabToKategoriPenanggungJawabData(KategoriPenanggungJawab t) {
		KategoriPenanggungJawabData kategoriPenanggungJawabData = new KategoriPenanggungJawabData();
		kategoriPenanggungJawabData.setId(t.getId());
		kategoriPenanggungJawabData.setNama(t.getNama());
		return kategoriPenanggungJawabData;
	}
	
	private KategoriPenanggungJawab convertKategoriPenanggungJawabDataToKategoriPenanggungJawab(KategoriPenanggungJawabData t) {
		return new KategoriPenanggungJawab(t.getId(), t.getNama());
	}
}
