package org.Sikoling.ejb.abstraction.service.modelperizinan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IModelPerizinanRepository;

public class ModelPerizinanService implements IModelPerizinanService {
	
	private final IModelPerizinanRepository modelPerizinanRepository;	

	public ModelPerizinanService(IModelPerizinanRepository modelPerizinanRepository) {
		super();
		this.modelPerizinanRepository = modelPerizinanRepository;
	}

	@Override
	public List<ModelPerizinan> getALL() {
		return modelPerizinanRepository.getAll();
	}

	@Override
	public ModelPerizinan save(
			org.Sikoling.ejb.abstraction.entity.ModelPerizinan modelPerizinan) {
		return modelPerizinanRepository.save(modelPerizinan);
	}

	@Override
	public ModelPerizinan update(
			org.Sikoling.ejb.abstraction.entity.ModelPerizinan modelPerizinan) {
		return modelPerizinanRepository.update(modelPerizinan);
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
