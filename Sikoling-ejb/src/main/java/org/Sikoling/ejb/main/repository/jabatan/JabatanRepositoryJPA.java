package org.Sikoling.ejb.main.repository.jabatan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.repository.IJabatanRepository;
import jakarta.persistence.EntityManager;

public class JabatanRepositoryJPA implements IJabatanRepository {
	
	private EntityManager entityManager;

	public JabatanRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Jabatan save(Jabatan t) {
		JabatanData jabatanData = convertJabatanToJabatanData(t);
		entityManager.persist(jabatanData);
		entityManager.flush();
		return convertJabatanDataToJabatan(jabatanData);
	}

	@Override
	public Jabatan update(Jabatan t) {
		JabatanData jabatanData = convertJabatanToJabatanData(t);
		jabatanData = entityManager.merge(jabatanData);
		return convertJabatanDataToJabatan(jabatanData);
	}

	@Override
	public List<Jabatan> getAll() {
		return entityManager.createNamedQuery("JabatanData.findAll", JabatanData.class)
				.getResultList()
				.stream()
				.map(t -> convertJabatanDataToJabatan(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Jabatan> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("JabatanData.findAll", JabatanData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertJabatanDataToJabatan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Jabatan> getByQueryNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("JabatanData.findAllByQueryNama", JabatanData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertJabatanDataToJabatan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Jabatan> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("JabatanData.findAllByQueryNama", JabatanData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertJabatanDataToJabatan(t))
				.collect(Collectors.toList());
	}

	private JabatanData convertJabatanToJabatanData(Jabatan jabatan) {
		JabatanData jabatanData = new JabatanData();
		jabatanData.setId(jabatan.getId());
		jabatanData.setNama(jabatan.getNama());
		return jabatanData;
	}
	
	private Jabatan convertJabatanDataToJabatan(JabatanData jabatanData) {
		return new Jabatan(jabatanData.getId(), jabatanData.getNama());
	}
}
