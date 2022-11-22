package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Kbli;

public interface IKbliRepository extends IRepository<Kbli> {
	DeleteResponse delete(String kode);
	Kbli updateById(String id, Kbli kbli);
	List<Kbli> getAllByPage(Integer page, Integer pageSize);
	List<Kbli> getByNama(String nama);
	List<Kbli> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<Kbli> getByKode(String kode);
	List<Kbli> getByKodeAndPage(String kode, Integer page, Integer pageSize);
	List<Kbli> getByKategori(String kategori);
	List<Kbli> getByKategoriAndPage(String kategori, Integer page, Integer pageSize);
}
