package org.Sikoling.ejb.abstraction.service.penanggungjawab;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;

public interface IPenanggungJawabService {
	PenanggungJawab save(PenanggungJawab t);
	PenanggungJawab update(PenanggungJawab t);
	List<PenanggungJawab> getAll();
	List<PenanggungJawab> getAllByPage(Integer page, Integer pageSize);
	List<PenanggungJawab> getByNama(String nama);
	List<PenanggungJawab> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
