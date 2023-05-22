package org.Sikoling.ejb.abstraction.service.perusahaan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;

public interface IRegisterPerusahaanService {
	DeleteResponse delete(String id);
	DeleteResponse deleteLinkKepemilikanPerusahaan(String idPerson, String idPerusahaan);
	RegisterPerusahaan save(RegisterPerusahaan t);
	RegisterPerusahaan update(RegisterPerusahaan t);
	Boolean cekById(String id);
//	RegisterPerusahaan getByNpwp(String npwp);
	List<RegisterPerusahaan> getAll();
//	List<RegisterPerusahaan> getAllByPage(Integer page, Integer pageSize);
//	List<RegisterPerusahaan> getByNama(String nama);
//	List<RegisterPerusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize);
//	List<RegisterPerusahaan> getByIdKreator(String idKreator);
//	List<RegisterPerusahaan> getByIdLinkKepemilikan(String idLinkKepemilikan);
//	List<RegisterPerusahaan> getByIdLinkKepemilikanTanpaRegisterDokumen(String idLinkKepemilikan);
	List<RegisterPerusahaan> getDaftarPerusahaan(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
