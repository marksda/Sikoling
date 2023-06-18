package org.Sikoling.ejb.abstraction.service.modelperizinan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IModelPerizinanRepository;

public class ModelPerizinanService implements IModelPerizinanService {
	
	private final IModelPerizinanRepository modelPerizinanRepository;	

	public ModelPerizinanService(IModelPerizinanRepository modelPerizinanRepository) {
		this.modelPerizinanRepository = modelPerizinanRepository;
	}

	@Override
	public ModelPerizinan save(ModelPerizinan t) throws IOException {
		return modelPerizinanRepository.save(t);
	}

	@Override
	public ModelPerizinan update(ModelPerizinan t) {
		return modelPerizinanRepository.update(t);
	}

	@Override
	public ModelPerizinan updateId(String idLama, ModelPerizinan t) throws IOException {
		return modelPerizinanRepository.updateId(idLama, t);
	}

	@Override
	public ModelPerizinan delete(ModelPerizinan t) throws IOException {
		return null;
	}

	@Override
	public List<ModelPerizinan> getDaftarData(QueryParamFilters queryParamFilters) {
		return modelPerizinanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return modelPerizinanRepository.getJumlahData(queryParamFilters);
	}	
	
}
