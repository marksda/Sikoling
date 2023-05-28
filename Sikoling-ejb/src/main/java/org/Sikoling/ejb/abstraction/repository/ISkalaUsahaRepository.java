package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;

public interface ISkalaUsahaRepository extends IRepository<SkalaUsaha> {
	SkalaUsaha updateById(String id, SkalaUsaha skalaUsaha);
	DeleteResponse delete(String id);
	List<SkalaUsaha> getDaftarSkalaUsaha(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
