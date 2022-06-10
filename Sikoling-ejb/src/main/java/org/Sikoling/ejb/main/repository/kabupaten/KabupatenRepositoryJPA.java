package org.Sikoling.ejb.main.repository.kabupaten;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.repository.IKabupatenRepository;
import jakarta.persistence.EntityManager;

public class KabupatenRepositoryJPA implements IKabupatenRepository {
	
	private final EntityManager entityManager;

	public KabupatenRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Kabupaten save(Kabupaten t) {
		KabupatenData kabupatenData = convertKabupatenToKabupatenData(t);
		entityManager.persist(kabupatenData);
		entityManager.flush();		
		return convertKabupatenDataToKabupaten(kabupatenData);
	}

	@Override
	public Kabupaten update(Kabupaten t) {
		KabupatenData kabupatenData = convertKabupatenToKabupatenData(t);
		kabupatenData = entityManager.merge(kabupatenData);
		return convertKabupatenDataToKabupaten(kabupatenData);
	}
	
	@Override
	public List<Kabupaten> getAll() {
		return entityManager.createNamedQuery("KabupatenData.findAll", KabupatenData.class)
				.getResultList()
				.stream()
				.map(t -> convertKabupatenDataToKabupaten(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Kabupaten> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("kabupatenData.findAll", KabupatenData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKabupatenDataToKabupaten(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Kabupaten> getByQueryNama(String nama) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("kabupatenData.findAllByQueryNama", KabupatenData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertKabupatenDataToKabupaten(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Kabupaten> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("kabupatenData.findAllByQueryNama", KabupatenData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKabupatenDataToKabupaten(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Kabupaten> getByPropinsi(String idPropinsi) {
		return entityManager.createNamedQuery("kabupatenData.findAllByIdPropinsi", KabupatenData.class)
				.setParameter("id", idPropinsi)
				.getResultList()
				.stream()
				.map(t -> convertKabupatenDataToKabupaten(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kabupaten> getByPropinsiAndPage(String idPropinsi, Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("kabupatenData.findAllByIdPropinsi", KabupatenData.class)
				.setParameter("id", idPropinsi)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKabupatenDataToKabupaten(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kabupaten> getByPropinsiAndQueryNama(String idPropinsi, String nama) {		
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("kabupatenData.findAllByIdPropinsiAndQueryNama", KabupatenData.class)
				.setParameter("nama", nama)
				.setParameter("idPropinsi", idPropinsi)
				.getResultList()
				.stream()
				.map(t -> convertKabupatenDataToKabupaten(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Kabupaten> getByPropinsiAndQueryNamaAndPage(String idPropinsi, String nama, Integer page,
			Integer pageSize) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("kabupatenData.findAllByIdPropinsiAndQueryNama", KabupatenData.class)
				.setParameter("nama", nama)
				.setParameter("idPropinsi", idPropinsi)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKabupatenDataToKabupaten(t))
				.collect(Collectors.toList());
	}
	
	private Kabupaten convertKabupatenDataToKabupaten(KabupatenData kabupatenData) {
		return new Kabupaten(kabupatenData.getId(), kabupatenData.getNama(), kabupatenData.getIdPropinsi());
	}
	
	private KabupatenData convertKabupatenToKabupatenData(Kabupaten kabupaten) {
		KabupatenData kabupatenData = new KabupatenData();
		kabupatenData.setId(kabupaten.getId());
		kabupatenData.setNama(kabupaten.getNama());
		kabupatenData.setIdPropinsi(kabupaten.getIdPropinsi());
		return kabupatenData;
	}

	
	
}
