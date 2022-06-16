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
	public List<PenanggungJawab> getByNama(String nama) {
		return penanggungJawabRepositoryJPA.getByNama(nama);
	}

	@Override
	public List<PenanggungJawab> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return penanggungJawabRepositoryJPA.getByNamaAndPage(nama, page, pageSize);
	}

}
