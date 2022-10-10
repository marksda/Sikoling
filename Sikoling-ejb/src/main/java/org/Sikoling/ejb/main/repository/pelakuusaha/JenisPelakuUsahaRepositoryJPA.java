package org.Sikoling.ejb.main.repository.pelakuusaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.JenisPelakuUsaha;
import org.Sikoling.ejb.abstraction.repository.IJenisPelakuUsahaRepository;
import jakarta.persistence.EntityManager;

public class JenisPelakuUsahaRepositoryJPA implements IJenisPelakuUsahaRepository {
	
	private final EntityManager entityManager;	

	public JenisPelakuUsahaRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<JenisPelakuUsaha> getAll() {
		return entityManager.createNamedQuery("JenisPelakuUsahaData.findAll", JenisPelakuUsahaData.class)
				.getResultList()
				.stream()
				.map(t -> convertJenisPelakuUsahaDataTojJenisPelakuUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public JenisPelakuUsaha save(JenisPelakuUsaha t) {
		JenisPelakuUsahaData jenisPelakuUsahaData = convertJenisPelakuUsahaToJenisPelakuUsahaData(t);
		entityManager.persist(jenisPelakuUsahaData);
		entityManager.flush();
		return convertJenisPelakuUsahaDataTojJenisPelakuUsaha(jenisPelakuUsahaData);
	}

	@Override
	public JenisPelakuUsaha update(JenisPelakuUsaha t) {
		JenisPelakuUsahaData jenisPelakuUsahaData = convertJenisPelakuUsahaToJenisPelakuUsahaData(t);
		jenisPelakuUsahaData = entityManager.merge(jenisPelakuUsahaData);
		return convertJenisPelakuUsahaDataTojJenisPelakuUsaha(jenisPelakuUsahaData);
	}

	@Override
	public List<JenisPelakuUsaha> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("JenisPelakuUsahaData.findAll", JenisPelakuUsahaData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertJenisPelakuUsahaDataTojJenisPelakuUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<JenisPelakuUsaha> getByNama(String nama) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("JenisPelakuUsahaData.findByNama", JenisPelakuUsahaData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertJenisPelakuUsahaDataTojJenisPelakuUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<JenisPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("JenisPelakuUsahaData.findAll", JenisPelakuUsahaData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertJenisPelakuUsahaDataTojJenisPelakuUsaha(t))
				.collect(Collectors.toList());
	}
	
	private JenisPelakuUsaha convertJenisPelakuUsahaDataTojJenisPelakuUsaha(JenisPelakuUsahaData d) {
		return new JenisPelakuUsaha(d.getId(), d.getNama());
	}
	
	private JenisPelakuUsahaData convertJenisPelakuUsahaToJenisPelakuUsahaData(JenisPelakuUsaha t) {
		JenisPelakuUsahaData jenisPelakuUsahaData = new JenisPelakuUsahaData();
		jenisPelakuUsahaData.setId(t.getId());
		jenisPelakuUsahaData.setNama(t.getNama());
		
		return jenisPelakuUsahaData;
	}

}
