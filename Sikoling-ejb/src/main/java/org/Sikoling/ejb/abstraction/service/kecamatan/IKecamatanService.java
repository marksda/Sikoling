package org.Sikoling.ejb.abstraction.service.kecamatan;

import java.util.List;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;

public interface IKecamatanService {
	Kecamatan save(Kecamatan kecamatan, String idKabupaten);
	Kecamatan update(Kecamatan kecamatan, String idKabupaten);
	List<Kecamatan> getAll();
	List<Kecamatan> getAllByPage(Integer page, Integer pageSize);
	List<Kecamatan> getByNama(String nama);
	List<Kecamatan> getByNamaAndPage(String nama, Integer page, Integer pageSize);	
	List<Kecamatan> getByKabupaten(String idKabupaten);
	List<Kecamatan> getByKabupatenAndPage(String idKabupaten, Integer page, Integer pageSize);
	List<Kecamatan> getByKabupatenAndNama(String idKabupaten, String nama);
	List<Kecamatan> getByKabupatenAndNamaAndPage(String idKabupaten, String nama, Integer page, Integer pageSize);
}
