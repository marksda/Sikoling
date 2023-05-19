package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;

public interface IRegisterPermohonanRepository extends IRepository<RegisterPermohonan> {
	DeleteResponse delete(String id);
	List<RegisterPermohonan> getDaftarPermohonan(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}