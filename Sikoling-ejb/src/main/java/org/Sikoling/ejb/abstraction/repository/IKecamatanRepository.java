package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Kecamatan;

public interface IKecamatanRepository extends IRepository2<Kecamatan, String> {
	List<Kecamatan> getAllByPage(Integer page, Integer pageSize);
	List<Kecamatan> getByNama(String nama);
	List<Kecamatan> getByNamaAndPage(String nama, Integer page, Integer pageSize);	
	List<Kecamatan> getByKabupaten(String idKabupaten);
	List<Kecamatan> getByKabupatenAndPage(String idKabupaten, Integer page, Integer pageSize);
	List<Kecamatan> getByKabupatenAndNama(String idKabupaten, String nama);
	List<Kecamatan> getByKabupatenAndNamaAndPage(String idKabupaten, String nama, Integer page, Integer pageSize);
}
