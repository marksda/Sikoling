package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;

public interface IPenanggungJawabRepository extends IRepository2<PenanggungJawab, String> {
	List<PenanggungJawab> getAllByPage(Integer page, Integer pageSize);
	List<PenanggungJawab> getByNama(String nama);
	List<PenanggungJawab> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
