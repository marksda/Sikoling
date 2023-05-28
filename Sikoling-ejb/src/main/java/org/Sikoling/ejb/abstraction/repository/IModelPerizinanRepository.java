package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IModelPerizinanRepository extends IRepository<ModelPerizinan> {
	DeleteResponse delete(String id);
	List<ModelPerizinan> getDaftarModelPerizinan(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
