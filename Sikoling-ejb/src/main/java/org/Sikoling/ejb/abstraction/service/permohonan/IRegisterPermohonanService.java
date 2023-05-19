package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;

public interface IRegisterPermohonanService {
	DeleteResponse delete(String id);
	RegisterPermohonan save(RegisterPermohonan t);
	RegisterPermohonan update(RegisterPermohonan t);
	List<RegisterPermohonan> getAll();
	List<RegisterPermohonan> getDaftarPermohonan(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
