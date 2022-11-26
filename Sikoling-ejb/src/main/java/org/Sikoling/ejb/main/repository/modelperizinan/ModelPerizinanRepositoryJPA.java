package org.Sikoling.ejb.main.repository.modelperizinan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.repository.IModelPerizinanRepository;
import jakarta.persistence.EntityManager;

public class ModelPerizinanRepositoryJPA implements IModelPerizinanRepository {
	
	private final EntityManager entityManager;	

	public ModelPerizinanRepositoryJPA(EntityManager entityManager) {
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

	@Override
	public ModelPerizinan updateById(String id, ModelPerizinan modelPerizinan) {
		String idBaru = modelPerizinan.getId();
		ModelPerizinanData modelPerizinanData = convertModelPerizinanTOModelPerizinanData(modelPerizinan);
		modelPerizinanData.setId(id);
		modelPerizinanData = entityManager.merge(modelPerizinanData);
		if(!idBaru.equals(id)) {
			modelPerizinanData.setId(idBaru);
			entityManager.flush();
		}
		return convertModelPerizinanDataToModelPerizinan(modelPerizinanData);
	}

	@Override
	public DeleteResponse delete(String id) {
		ModelPerizinanData modelPerizinanData = entityManager.find(ModelPerizinanData.class, id);
		entityManager.remove(modelPerizinanData);
		return new DeleteResponse(true, id);
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
