package org.Sikoling.ejb.abstraction.service.modelperizinan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
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
	public List<ModelPerizinan> getAllByPage(Integer page, Integer pageSize) {
		return modelPerizinanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<ModelPerizinan> getByNama(String nama) {
		return modelPerizinanRepository.getByNama(nama);
	}

	@Override
	public List<ModelPerizinan> getByNamaAndPage(String nama, Integer page,
			Integer pageSize) {
		return modelPerizinanRepository.getByNamaAndPage(nama, page, pageSize);
	}
	
	@Override
	public ModelPerizinan updateById(String id, ModelPerizinan modelPerizinan) {
		return modelPerizinanRepository.updateById(id, modelPerizinan);
	}

	@Override
	public DeleteResponse delete(String id) {
		return modelPerizinanRepository.delete(id);
	}

}
