package org.Sikoling.ejb.abstraction.repository;

import java.util.List;
import org.Sikoling.ejb.abstraction.entity.AutorityPerusahaan;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IAutorityPerusahaanRepository extends IRepository<AutorityPerusahaan> {
	DeleteResponse delete(String idAutority, String idRegisterPerusahaan);
	AutorityPerusahaan updateById(String idLamaAutority, String idLamaRegisterPerusahaan, AutorityPerusahaan dataBaru);
	List<AutorityPerusahaan> getDaftarAutorityPerusahaan(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}