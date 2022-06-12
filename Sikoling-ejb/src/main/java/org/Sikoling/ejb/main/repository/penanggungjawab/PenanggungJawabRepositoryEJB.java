package org.Sikoling.ejb.main.repository.penanggungjawab;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;
import org.Sikoling.ejb.abstraction.repository.IPenanggungJawabRepository;

import jakarta.inject.Inject;

public class PenanggungJawabRepositoryEJB implements IPenanggungJawabRepository {
	
	@Inject
	private PenanggungJawabRepositoryJPA penanggungJawabRepositoryJPA;

	@Override
	public List<PenanggungJawab> getAll() {		
		return penanggungJawabRepositoryJPA.getAll();
	}

	@Override
	public PenanggungJawab save(PenanggungJawab t) {
		return penanggungJawabRepositoryJPA.save(t);
	}

	@Override
	public PenanggungJawab update(PenanggungJawab t) {
		return penanggungJawabRepositoryJPA.update(t);
	}

	@Override
	public List<PenanggungJawab> getAllByPage(Integer page, Integer pageSize) {
		return penanggungJawabRepositoryJPA.getAllByPage(page, pageSize);
	}

	@Override
	public List<PenanggungJawab> getByQueryNama(String nama) {
		return penanggungJawabRepositoryJPA.getByQueryNama(nama);
	}

	@Override
	public List<PenanggungJawab> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return penanggungJawabRepositoryJPA.getByQueryNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<PenanggungJawab> getAllByPemilik(String idPemilik) {
		return penanggungJawabRepositoryJPA.getAllByPemilik(idPemilik);
	}

	@Override
	public List<PenanggungJawab> getAllByPemilikAndPage(String idPemilik, Integer page, Integer pageSize) {
		return penanggungJawabRepositoryJPA.getAllByPemilik(idPemilik);
	}

	@Override
	public List<PenanggungJawab> getAllByPemilikAndNama(String idPemilik, String nama) {
		return penanggungJawabRepositoryJPA.getAllByPemilikAndNama(idPemilik, nama);
	}

	@Override
	public List<PenanggungJawab> getAllByPemilikAndNamaAndPage(String idPemilik, String nama, Integer page,
			Integer pageSize) {
		return penanggungJawabRepositoryJPA.getAllByPemilikAndNamaAndPage(idPemilik, nama, page, pageSize);
	}

}
