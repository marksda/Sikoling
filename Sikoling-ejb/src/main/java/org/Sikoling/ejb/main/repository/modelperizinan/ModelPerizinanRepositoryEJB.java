package org.Sikoling.ejb.main.repository.modelperizinan;

import java.io.IOException;
import java.util.List;

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
	public ModelPerizinan save(ModelPerizinan t) throws IOException {
		return modelPerizinanRepository.save(t);
	}

	@Override
	public ModelPerizinan update(ModelPerizinan t) {
		return modelPerizinanRepository.update(t);
	}

	@Override
	public ModelPerizinan delete(ModelPerizinan t) throws IOException {
		return modelPerizinanRepository.delete(t);
	}

	@Override
	public List<ModelPerizinan> getDaftarData(QueryParamFilters queryParamFilters) {
		return modelPerizinanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return modelPerizinanRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public ModelPerizinan updateId(String idLama, ModelPerizinan t) throws IOException {
		return modelPerizinanRepository.updateId(idLama, t);
	}
	
	
	
}
