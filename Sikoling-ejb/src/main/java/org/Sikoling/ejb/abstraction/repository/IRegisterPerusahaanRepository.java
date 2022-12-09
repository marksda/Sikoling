package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;

public interface IRegisterPerusahaanRepository extends IRepository<RegisterPerusahaan> {
	Boolean cekById(String id);
	DeleteResponse delete(String id);
	List<RegisterPerusahaan> getAllByPage(Integer page, Integer pageSize);
	List<RegisterPerusahaan> getByNama(String nama);
	List<RegisterPerusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<RegisterPerusahaan> getByIdPerson(String personId);
	RegisterPerusahaan getByNpwp(String npwp);
}