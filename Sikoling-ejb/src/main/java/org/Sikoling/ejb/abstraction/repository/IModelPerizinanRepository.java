package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;

public interface IModelPerizinanRepository extends IRepository<ModelPerizinan> {
	List<ModelPerizinan> getAllByPage(Integer page, Integer pageSize);
	List<ModelPerizinan> getByNama(String nama);
	List<ModelPerizinan> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
