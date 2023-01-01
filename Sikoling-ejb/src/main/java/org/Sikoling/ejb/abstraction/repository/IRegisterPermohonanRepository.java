package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.RegisterPermohonan;

public interface IRegisterPermohonanRepository extends IRepository<RegisterPermohonan> {
	List<RegisterPermohonan> getAllByPage(Integer page, Integer pageSize);
	List<RegisterPermohonan> getByQueryNama(String nama);
	List<RegisterPermohonan> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
	List<RegisterPermohonan> getByIdPengakses(String idPengakses);
	List<RegisterPermohonan> getByIdPerusahaan(String idPerusahaan);
}