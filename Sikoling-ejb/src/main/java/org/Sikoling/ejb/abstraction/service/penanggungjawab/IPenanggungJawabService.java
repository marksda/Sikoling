package org.Sikoling.ejb.abstraction.service.penanggungjawab;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;

public interface IPenanggungJawabService {
	PenanggungJawab save(PenanggungJawab t);
	PenanggungJawab update(PenanggungJawab t);
	List<PenanggungJawab> getAll();
	List<PenanggungJawab> getAllByPage(Integer page, Integer pageSize);
	List<PenanggungJawab> getByQueryNama(String nama);
	List<PenanggungJawab> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
	List<PenanggungJawab> getAllByPemilik(String idPemilik);
	List<PenanggungJawab> getAllByPemilikAndPage(String idPemilik, Integer page, Integer pageSize);
	List<PenanggungJawab> getAllByPemilikAndNama(String idPemilik, String nama);
	List<PenanggungJawab> getAllByPemilikAndNamaAndPage(String idPemilik, String nama, Integer page, Integer pageSize);
}
