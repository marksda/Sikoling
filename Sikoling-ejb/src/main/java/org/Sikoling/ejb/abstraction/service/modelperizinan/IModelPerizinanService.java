package org.Sikoling.ejb.abstraction.service.modelperizinan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IModelPerizinanService {
	List<ModelPerizinan> getALL();
	ModelPerizinan save(ModelPerizinan modelPerizinan);
	ModelPerizinan update(ModelPerizinan modelPerizinan);
	DeleteResponse delete(String id);
	List<ModelPerizinan> getDaftarModelPerizinan(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
