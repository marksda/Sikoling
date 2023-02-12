package org.Sikoling.ejb.main.repository.jabatan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.repository.IJabatanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;

import jakarta.persistence.EntityManager;

public class JabatanRepositoryJPA implements IJabatanRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;

	public JabatanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public Jabatan save(Jabatan t) {
		JabatanData jabatanData = dataConverter.convertJabatanToJabatanData(t);
		entityManager.persist(jabatanData);
		entityManager.flush();
		return dataConverter.convertJabatanDataToJabatan(jabatanData);
	}

	@Override
	public Jabatan update(Jabatan t) {
		JabatanData jabatanData = dataConverter.convertJabatanToJabatanData(t);
		jabatanData = entityManager.merge(jabatanData);
		return dataConverter.convertJabatanDataToJabatan(jabatanData);
	}

	@Override
	public List<Jabatan> getAll() {
		return entityManager.createNamedQuery("JabatanData.findAll", JabatanData.class)
				.getResultList()
				.stream()
				.map(t -> dataConverter.convertJabatanDataToJabatan(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Jabatan> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("JabatanData.findAll", JabatanData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertJabatanDataToJabatan(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<Jabatan> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("JabatanData.findByNama", JabatanData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertJabatanDataToJabatan(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<Jabatan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("JabatanData.findByNama", JabatanData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertJabatanDataToJabatan(d))
				.collect(Collectors.toList());
	}
	
}
