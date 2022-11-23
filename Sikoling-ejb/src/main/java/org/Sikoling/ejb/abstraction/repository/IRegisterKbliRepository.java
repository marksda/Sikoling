package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.RegisterKbli;

public interface IRegisterKbliRepository extends IRepository<RegisterKbli> {
	DeleteResponse delete(String nib, String kode);
	RegisterKbli updateById(String nib, String kode, RegisterKbli registerKbli);
	List<RegisterKbli> getAllByPage(Integer page, Integer pageSize);
	List<RegisterKbli> getByNama(String nama);
	List<RegisterKbli> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<RegisterKbli> getByKode(String kode);
	List<RegisterKbli> getByKodeAndPage(String kode, Integer page, Integer pageSize);
	List<RegisterKbli> getByNib(String nib);
	List<RegisterKbli> getByNibAndPage(String nib, Integer page, Integer pageSize);
}
