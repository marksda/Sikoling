package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli2020;

public interface IKbliRepository extends IRepository<Kbli2020> {
	DeleteResponse delete(String kode);
	Kbli2020 updateById(String id, Kbli2020 kbli);
	List<Kbli2020> getAllByPage(Integer page, Integer pageSize);
	List<Kbli2020> getByNama(String nama);
	List<Kbli2020> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<Kbli2020> getByKode(String kode);
	List<Kbli2020> getByKodeAndPage(String kode, Integer page, Integer pageSize);
	List<Kbli2020> getByKategori(String kategori);
	List<Kbli2020> getByKategoriAndPage(String kategori, Integer page, Integer pageSize);
}
