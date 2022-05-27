package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.JenisKelamin;

public interface JenisKelaminRepository extends Repository<JenisKelamin> {
	List<JenisKelamin> getAll(Integer page, Integer pageSize);
}
