package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.dokumen.RegisterKbli;

public interface IRegisterKbliService {
	RegisterKbli save(RegisterKbli registerKbli);
	RegisterKbli update(RegisterKbli registerKbli);	
	DeleteResponse delete(String nib, String kode);
	List<RegisterKbli> getAll();
	List<RegisterKbli> getAllByPage(Integer page, Integer pageSize);
	List<RegisterKbli> getByNama(String nama);
	List<RegisterKbli> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<RegisterKbli> getByKode(String kode);
	List<RegisterKbli> getByKodeAndPage(String kode, Integer page, Integer pageSize);
	List<RegisterKbli> getByNib(String nib);
	List<RegisterKbli> getByNibAndPage(String nib, Integer page, Integer pageSize);

}
