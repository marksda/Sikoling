package org.Sikoling.ejb.abstraction.service.perusahaan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.AutorityPerusahaan;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IAutorityPerusahaanService {
	List<AutorityPerusahaan> getALL();
	AutorityPerusahaan save(AutorityPerusahaan autorityPerusahaan);
	AutorityPerusahaan update(AutorityPerusahaan autorityPerusahaan);
	DeleteResponse delete(String id);
	List<AutorityPerusahaan> getDaftarAutorityPerusahaan(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
