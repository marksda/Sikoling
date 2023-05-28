package org.Sikoling.ejb.main.repository.modelperizinan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IModelPerizinanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class ModelPerizinanRepositoryEJB implements IModelPerizinanRepository {

	@Inject
	private ModelPerizinanRepositoryJPA modelPerizinanRepository;
	
	@Override
	public List<ModelPerizinan> getAll() {
		return modelPerizinanRepository.getAll();
	}

	@Override
	public ModelPerizinan save(ModelPerizinan t) {
		return modelPerizinanRepository.save(t);
	}

	@Override
	public ModelPerizinan update(ModelPerizinan t) {
		return modelPerizinanRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return modelPerizinanRepository.delete(id);
	}

	
	@Override
	public List<ModelPerizinan> getDaftarModelPerizinan(QueryParamFilters queryParamFilters) {
		return modelPerizinanRepository.getDaftarModelPerizinan(queryParamFilters);
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return modelPerizinanRepository.getCount(queryParamFilters);
	}

}
