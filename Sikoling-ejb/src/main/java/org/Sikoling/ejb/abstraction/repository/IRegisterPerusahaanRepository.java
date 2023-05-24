package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;

public interface IRegisterPerusahaanRepository extends IRepository<RegisterPerusahaan> {
	Boolean cekById(String id);
	DeleteResponse delete(String id);
	DeleteResponse deleteLinkKepemilikanPerusahaan(String idAutority, String idRegisterPerusahaan);
	List<RegisterPerusahaan> getDaftarPerusahaan(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);	
}