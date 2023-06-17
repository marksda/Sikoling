package org.Sikoling.ejb.main.repository.jabatan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IJabatanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class JabatanRepositoryEJB implements IJabatanRepository {
	
	@Inject
	private JabatanRepositoryJPA jabatanRepository;

	@Override
	public Jabatan save(Jabatan t) throws IOException {
		return jabatanRepository.save(t);
	}

	@Override
	public Jabatan update(Jabatan t) {
		return jabatanRepository.update(t);
	}

	@Override
	public Jabatan delete(Jabatan t) throws IOException {
		return jabatanRepository.delete(t);
	}

	@Override
	public List<Jabatan> getDaftarData(QueryParamFilters queryParamFilters) {
		return jabatanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return jabatanRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public Jabatan updateId(String idLama, Jabatan t) throws IOException {
		return jabatanRepository.updateId(idLama, t);
	}
	
}
