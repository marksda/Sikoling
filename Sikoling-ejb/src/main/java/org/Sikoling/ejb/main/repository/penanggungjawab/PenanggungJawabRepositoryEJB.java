package org.Sikoling.ejb.main.repository.penanggungjawab;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;
import org.Sikoling.ejb.abstraction.repository.IPenanggungJawabRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;


@Stateless
@LocalBean
@Infrastructure
public class PenanggungJawabRepositoryEJB implements IPenanggungJawabRepository {
	
	@Inject
	private PenanggungJawabRepositoryJPA penanggungJawabRepositoryJPA;

	@Override
	public List<PenanggungJawab> getAll() {		
		return penanggungJawabRepositoryJPA.getAll();
	}

	@Override
	public PenanggungJawab save(PenanggungJawab t, String idPemrakarsa) {
		return penanggungJawabRepositoryJPA.save(t, idPemrakarsa);
	}

	@Override
	public PenanggungJawab update(PenanggungJawab t, String idPemrakarsa) {
		return penanggungJawabRepositoryJPA.update(t, idPemrakarsa);
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
