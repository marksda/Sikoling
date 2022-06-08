package org.Sikoling.ejb.abstraction.service.kecamatan;

import java.util.List;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;

public interface IServiceKecamatan {
	Kecamatan save(Kecamatan kecamatan);
	Kecamatan update(Kecamatan kecamatan);
	List<Kecamatan> getAllByPage(Integer page, Integer pageSize);
	List<Kecamatan> getByQueryNama(String nama);
	List<Kecamatan> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);	
	List<Kecamatan> getByKabupaten(String idKabupaten);
	List<Kecamatan> getByKabupatenAndPage(String idKabupaten, Integer page, Integer pageSize);
	List<Kecamatan> getByKabupatenAndQueryNama(String idKabupaten, String nama);
	List<Kecamatan> getByKabupatenAndQueryNamaAndPage(String idKabupaten, String nama, Integer page, Integer pageSize);
}
