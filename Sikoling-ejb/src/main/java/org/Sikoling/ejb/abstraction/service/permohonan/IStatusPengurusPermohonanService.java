package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatusPengurusPermohonan;

public interface IStatusPengurusPermohonanService {
	StatusPengurusPermohonan updateById(String id, StatusPengurusPermohonan statusPengurusPermohonan);
	DeleteResponse delete(String id);
	StatusPengurusPermohonan save(StatusPengurusPermohonan statusPengurusPermohonan);
	StatusPengurusPermohonan update(StatusPengurusPermohonan statusPengurusPermohonan);
	List<StatusPengurusPermohonan> getDaftarStatusPengurusPermohonan(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
