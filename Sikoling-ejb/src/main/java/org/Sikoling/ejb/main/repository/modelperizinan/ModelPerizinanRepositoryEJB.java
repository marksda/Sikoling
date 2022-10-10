package org.Sikoling.ejb.main.repository.modelperizinan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
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
	public List<ModelPerizinan> getAllByPage(Integer page, Integer pageSize) {
		return modelPerizinanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<ModelPerizinan> getByNama(String nama) {
		return modelPerizinanRepository.getByNama(nama);
	}

	@Override
	public List<ModelPerizinan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return modelPerizinanRepository.getByNamaAndPage(nama, page, pageSize);
	}

}
