package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;

public interface IRegisterPerusahaanRepository extends IRepository<RegisterPerusahaan> {
	Boolean cekById(String id);
	DeleteResponse delete(String id);
	DeleteResponse deleteLinkKepemilikanPerusahaan(String idAutority, String idRegisterPerusahaan);
	List<RegisterPerusahaan> getAllByPage(Integer page, Integer pageSize);
	List<RegisterPerusahaan> getByNama(String nama);
	List<RegisterPerusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<RegisterPerusahaan> getByIdKreator(String idKreator);
	List<RegisterPerusahaan> getByIdLinkKepemilikan(String idLinkKepemilikan);
	List<RegisterPerusahaan> getByIdLinkKepemilikanTanpaRegisterDokumen(String idLinkKepemilikan);
	RegisterPerusahaan getByNpwp(String npwp);
}