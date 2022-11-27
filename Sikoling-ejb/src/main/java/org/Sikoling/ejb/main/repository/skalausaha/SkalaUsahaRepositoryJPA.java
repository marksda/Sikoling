package org.Sikoling.ejb.main.repository.skalausaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.repository.ISkalaUsahaRepository;
import jakarta.persistence.EntityManager;

public class SkalaUsahaRepositoryJPA implements ISkalaUsahaRepository {
	
	private final EntityManager entityManager;
	
	public SkalaUsahaRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	@Override
	public List<SkalaUsaha> getAll() {
		return entityManager.createNamedQuery("SkalaUsahaData.findAll", SkalaUsahaData.class)
				.getResultList()
				.stream()
				.map(t -> convertSkalaUsahaDataToSkalaUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public SkalaUsaha save(SkalaUsaha t) {
		SkalaUsahaData skalaUsahaData = convertSkalaUsahaToSkalaUsahaData(t);
		entityManager.persist(skalaUsahaData);
		entityManager.flush();		
		return convertSkalaUsahaDataToSkalaUsaha(skalaUsahaData);
	}
	
	@Override
	public DeleteResponse delete(String id) {
		SkalaUsahaData skalaUsahaData = entityManager.find(SkalaUsahaData.class, id);
		entityManager.remove(skalaUsahaData);	
		return new DeleteResponse(true, id);
	}

	@Override
	public SkalaUsaha update(SkalaUsaha t) {
		SkalaUsahaData skalaUsahaData = convertSkalaUsahaToSkalaUsahaData(t);
		skalaUsahaData = entityManager.merge(skalaUsahaData);		
		return convertSkalaUsahaDataToSkalaUsaha(skalaUsahaData);
	}
	
	@Override
	public SkalaUsaha updateById(String id, SkalaUsaha skalaUsaha) {
		String idBaru = skalaUsaha.getId();
		SkalaUsahaData skalaUsahaData = convertSkalaUsahaToSkalaUsahaData(skalaUsaha);
		skalaUsahaData.setId(id);
		skalaUsahaData = entityManager.merge(skalaUsahaData);
		if(!idBaru.equals(id)) {
			skalaUsahaData.setId(idBaru);
			entityManager.flush();
		}
		return convertSkalaUsahaDataToSkalaUsaha(skalaUsahaData);
	}

	@Override
	public List<SkalaUsaha> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("SkalaUsahaData.findAll", SkalaUsahaData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertSkalaUsahaDataToSkalaUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<SkalaUsaha> getByNama(String nama) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("SkalaUsahaData.findByQueryNama", SkalaUsahaData.class)
				.setParameter("nama", nama)				
				.getResultList()
				.stream()
				.map(t -> convertSkalaUsahaDataToSkalaUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<SkalaUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("SkalaUsahaData.findByQueryNama", SkalaUsahaData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertSkalaUsahaDataToSkalaUsaha(t))
				.collect(Collectors.toList());
	}

	private SkalaUsahaData convertSkalaUsahaToSkalaUsahaData(SkalaUsaha t) {
		SkalaUsahaData skalaUsahaData = new SkalaUsahaData();
		skalaUsahaData.setId(t.getId());
		skalaUsahaData.setNama(t.getNama());
		skalaUsahaData.setSingkatan(t.getSingkatan());
		
		return skalaUsahaData;
	}
	
	private SkalaUsaha convertSkalaUsahaDataToSkalaUsaha(SkalaUsahaData d) {
		return new SkalaUsaha(d.getId(), d.getNama(), d.getSingkatan());
	}

	
	
}
