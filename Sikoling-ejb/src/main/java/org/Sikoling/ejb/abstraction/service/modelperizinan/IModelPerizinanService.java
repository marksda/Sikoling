package org.Sikoling.ejb.abstraction.service.modelperizinan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;

public interface IModelPerizinanService {
	List<ModelPerizinan> getALL();
	ModelPerizinan save(ModelPerizinan modelPerizinan);
	ModelPerizinan update(ModelPerizinan modelPerizinan);
	ModelPerizinan updateById(String id, ModelPerizinan modelPerizinan);
	DeleteResponse delete(String id);
	List<ModelPerizinan> getAllByPage(Integer page, Integer pageSize);
	List<ModelPerizinan> getByNama(String nama);
	List<ModelPerizinan> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
