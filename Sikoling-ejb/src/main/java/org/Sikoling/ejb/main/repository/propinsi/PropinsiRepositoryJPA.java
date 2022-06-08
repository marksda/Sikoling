package org.Sikoling.ejb.main.repository.propinsi;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.repository.IPropinsiRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class PropinsiRepositoryJPA implements IPropinsiRepository {
	
	private final EntityManager entityManager;

	public PropinsiRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<Propinsi> getAll() {		
		return entityManager.createNamedQuery("PropinsiData.findAll", PropinsiData.class)
				.getResultList()
				.stream()
				.map(t -> convertPropinsiDataToPropinsi(t))
				.collect(Collectors.toList());
	}

	@Override
	public Propinsi save(Propinsi t) {
		PropinsiData propinsiData = convertPropinsiToPropinsiData(t);		
		entityManager.persist(propinsiData);
		entityManager.flush();		
		return convertPropinsiDataToPropinsi(propinsiData);
	}

	@Override
	public Propinsi update(Propinsi t) {
		PropinsiData propinsiData = convertPropinsiToPropinsiData(t);
		propinsiData = entityManager.merge(propinsiData);
		return convertPropinsiDataToPropinsi(propinsiData);
	}

	@Override
	public List<Propinsi> getAllByPage(Integer page, Integer pageSize) {		
		return entityManager.createNamedQuery("PropinsiData.findAll", PropinsiData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPropinsiDataToPropinsi(t))
				.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Propinsi> getByQueryNama(String nama) {
		nama = "%" + nama + "%";
		Query q = entityManager.createNativeQuery("PropinsiData.findAllByName", PropinsiData.class);		
		List<PropinsiData> resultList = q.setParameter("nama", nama).getResultList();
		
		return resultList
				.stream()
				.map(t -> convertPropinsiDataToPropinsi(t))
				.collect(Collectors.toList());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Propinsi> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "'%" + nama + "%'";
		Query q = entityManager.createNativeQuery("PropinsiData.findAllByName", PropinsiData.class);		
		List<PropinsiData> resultList = q.setParameter("nama", nama)
										.setMaxResults(pageSize)
										.setFirstResult((page-1)*pageSize)
										.getResultList();
		
		return resultList.stream()
				.map(t -> convertPropinsiDataToPropinsi(t))
				.collect(Collectors.toList());
	}

	private Propinsi convertPropinsiDataToPropinsi(PropinsiData propinsiData) {
		return new Propinsi(propinsiData.getId(), propinsiData.getNama());
	}
	
	private PropinsiData convertPropinsiToPropinsiData(Propinsi propinsi) {
		return new PropinsiData(propinsi.getId(), propinsi.getNama());
	}
}
