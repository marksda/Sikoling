package org.Sikoling.ejb.abstraction.service.perusahaan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;

public interface IRegisterPerusahaanService {
	RegisterPerusahaan save(RegisterPerusahaan t) throws IOException;
	RegisterPerusahaan update(RegisterPerusahaan t);
	RegisterPerusahaan updateId(String idLama, RegisterPerusahaan t) throws IOException;
	RegisterPerusahaan delete(RegisterPerusahaan t) throws IOException;	
	List<RegisterPerusahaan> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
//	Boolean cekById(String id);
//	RegisterPerusahaan deleteLinkKepemilikanPerusahaan(OtoritasPerusahaan autorityPerusahaan) throws IOException;
//	RegisterPerusahaan addLinkKepemilanPerusahaan(OtoritasPerusahaan autorityPerusahaan) throws IOException;
	
}
