package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Kecamatan;

public interface KecamatanRepository extends Repository<Kecamatan> {
	List<Kecamatan> getAll(Integer page, Integer pageSize);
	List<Kecamatan> getByKabupaten(String idKabupaten);
	List<Kecamatan> getByKabupatenAndQueryNama(String idKabupaten, String nama);
}
