package org.Sikoling.ejb.abstraction.service.perusahaan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.AutorityPerusahaan;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IAutorityPerusahaanService {
	AutorityPerusahaan save(AutorityPerusahaan t) throws IOException;
	AutorityPerusahaan update(AutorityPerusahaan t);
	AutorityPerusahaan updateId(String idLamaAutority, String idLamaRegisterPerusahaan, AutorityPerusahaan t) throws IOException;
	AutorityPerusahaan delete(AutorityPerusahaan t) throws IOException;
	List<AutorityPerusahaan> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
