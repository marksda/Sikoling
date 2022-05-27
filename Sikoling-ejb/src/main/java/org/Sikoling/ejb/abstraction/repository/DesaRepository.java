package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;

public interface DesaRepository extends Repository<Desa> {
	List<Desa> getAll(Integer page, Integer pageSize);
	List<Desa> getByKecamatan(Kecamatan kecamatan);
}
