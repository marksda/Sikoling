package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatusPengurusPermohonan;

public interface IStatusPengurusPermohonanRepository extends IRepository<StatusPengurusPermohonan> {
	StatusPengurusPermohonan updateById(String id, StatusPengurusPermohonan statusPengurusPermohonan);
	DeleteResponse delete(String id);
	List<StatusPengurusPermohonan> getDaftarStatusPengurusPermohonan(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
	
}
