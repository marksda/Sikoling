package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;

public interface IRegisterDokumenRepository extends IRepository<RegisterDokumen> {
	DeleteResponse delete(String id);
	List<RegisterDokumen> getAllByPage(Integer page, Integer pageSize);
	List<RegisterDokumen> getByNamaPerusahaan(String namaPerusahaan);
	List<RegisterDokumen> getByNamaPerusahaanAndPage(String namaPerusahaan, Integer page, Integer pageSize);	
	List<RegisterDokumen> getByIdPerusahaan(String idPerusahaan);
	List<RegisterDokumen> getByIdPerusahaanAndPage(String idPerusahaan, Integer page, Integer pageSize);
	List<RegisterDokumen> getByNamaDokumen(String namaDokumen);
	List<RegisterDokumen> getByNamaDokumenAndPage(String namaDokumen, Integer page, Integer pageSize);	
	List<RegisterDokumen> getByIdDokumen(String idDokumen);
	List<RegisterDokumen> getByIdDokumenAndPage(String idDokumen, Integer page, Integer pageSize);	
	RegisterDokumen getByIdRegisterDokumen(String idRegisterDokumen);
}
