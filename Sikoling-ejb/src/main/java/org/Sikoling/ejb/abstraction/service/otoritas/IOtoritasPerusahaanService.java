package org.Sikoling.ejb.abstraction.service.otoritas;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.OtoritasPerusahaan;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IOtoritasPerusahaanService {
	OtoritasPerusahaan save(OtoritasPerusahaan t) throws IOException;
	OtoritasPerusahaan update(OtoritasPerusahaan t);
	OtoritasPerusahaan updateId(String idLamaAutority, String idLamaRegisterPerusahaan, OtoritasPerusahaan t) throws IOException;
	OtoritasPerusahaan delete(OtoritasPerusahaan t) throws IOException;
	List<OtoritasPerusahaan> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
