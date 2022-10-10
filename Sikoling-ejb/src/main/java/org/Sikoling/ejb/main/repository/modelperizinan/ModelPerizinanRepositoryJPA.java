package org.Sikoling.ejb.main.repository.modelperizinan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.repository.IModelPerizinanRepository;
import jakarta.persistence.EntityManager;

public class ModelPerizinanRepositoryJPA implements IModelPerizinanRepository {
	
	private final EntityManager entityManager;	

	public ModelPerizinanRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	

	@Override
	public List<ModelPerizinan> getAll() {
		return entityManager.createNamedQuery("ModelPerizinanData.findAll", ModelPerizinanData.class)
				.getResultList()
				.stream()
				.map(t -> convertModelPerizinanDataToModelPerizinan(t))
				.collect(Collectors.toList());
	}

	@Override
	public ModelPerizinan save(ModelPerizinan t) {
		ModelPerizinanData modelPerizinanData = convertModelPerizinanTOModelPerizinanData(t);
		entityManager.persist(modelPerizinanData);
		entityManager.flush();
		return convertModelPerizinanDataToModelPerizinan(modelPerizinanData);
	}

	@Override
	public ModelPerizinan update(ModelPerizinan t) {
		ModelPerizinanData modelPerizinanData = convertModelPerizinanTOModelPerizinanData(t);
		modelPerizinanData = entityManager.merge(modelPerizinanData);
		return convertModelPerizinanDataToModelPerizinan(modelPerizinanData);
	}

	@Override
	public List<ModelPerizinan> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("ModelPerizinanData.findAll", ModelPerizinanData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertModelPerizinanDataToModelPerizinan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<ModelPerizinan> getByNama(String nama) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("ModelPerizinanData.findByQueryNama", ModelPerizinanData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertModelPerizinanDataToModelPerizinan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<ModelPerizinan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("ModelPerizinanData.findByQueryNama", ModelPerizinanData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertModelPerizinanDataToModelPerizinan(t))
				.collect(Collectors.toList());
	}

	private ModelPerizinanData convertModelPerizinanTOModelPerizinanData(ModelPerizinan m) {
		ModelPerizinanData modelPerizinanData = new ModelPerizinanData();
		modelPerizinanData.setId(m.getId());
		modelPerizinanData.setNama(m.getNama());
		modelPerizinanData.setSingkatan(m.getSingkatan());
		
		return modelPerizinanData;
	}
	
	private ModelPerizinan convertModelPerizinanDataToModelPerizinan(ModelPerizinanData d) {
		return new ModelPerizinan(d.getId(), d.getNama(), d.getSingkatan());
	}
	
}
