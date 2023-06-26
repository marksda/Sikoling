package org.Sikoling.ejb.main.repository.dokumen;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli;
import org.Sikoling.ejb.abstraction.repository.IKbliRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class Kbli2020RepositoryEJB implements IKbliRepository {
	
	@Inject
	private Kbli2020RepositoryJPA getJumlahData;

	@Override
	public Kbli save(Kbli t) throws IOException {
		return getJumlahData.save(t);
	}

	@Override
	public Kbli update(Kbli t) {
		return getJumlahData.update(t);
	}

	@Override
	public Kbli delete(Kbli t) throws IOException {
		return getJumlahData.delete(t);
	}

	@Override
	public List<Kbli> getDaftarData(QueryParamFilters queryParamFilters) {
		return getJumlahData.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return getJumlahData.getJumlahData(queryParamFilters);
	}

	@Override
	public Kbli updateId(String idLama, Kbli t) throws IOException {
		return getJumlahData.updateId(idLama, t);
	}
	

}
