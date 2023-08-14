package org.Sikoling.ejb.main.repository.penanggungjawab;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IPenanggungJawabRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;


@Stateless
@Local
@Infrastructure
public class PenanggungJawabRepositoryEJB implements IPenanggungJawabRepository {
	
	@Inject
	private PenanggungJawabRepositoryJPA penanggungJawabRepository;

	@Override
	public PenanggungJawab save(PenanggungJawab t) throws IOException {
		return penanggungJawabRepository.save(t);
	}

	@Override
	public PenanggungJawab update(PenanggungJawab t) {
		return penanggungJawabRepository.update(t);
	}

	@Override
	public PenanggungJawab delete(PenanggungJawab t) throws IOException {
		return penanggungJawabRepository.delete(t);
	}

	@Override
	public List<PenanggungJawab> getDaftarData(QueryParamFilters queryParamFilters) {
		return penanggungJawabRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return penanggungJawabRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public PenanggungJawab updateId(String idLama, PenanggungJawab t) throws IOException {
		return penanggungJawabRepository.updateId(idLama, t);
	}

	
	
}
