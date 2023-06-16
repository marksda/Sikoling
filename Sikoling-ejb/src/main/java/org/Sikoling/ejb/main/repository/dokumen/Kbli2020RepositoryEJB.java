package org.Sikoling.ejb.main.repository.dokumen;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli2020;
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
	public Kbli2020 save(Kbli2020 t) throws IOException {
		return getJumlahData.save(t);
	}

	@Override
	public Kbli2020 update(Kbli2020 t) {
		return getJumlahData.update(t);
	}

	@Override
	public Kbli2020 delete(Kbli2020 t) throws IOException {
		return getJumlahData.delete(t);
	}

	@Override
	public List<Kbli2020> getDaftarData(QueryParamFilters queryParamFilters) {
		return getJumlahData.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return getJumlahData.getJumlahData(queryParamFilters);
	}

	@Override
	public Kbli2020 updateId(String idLama, Kbli2020 t) throws IOException {
		return getJumlahData.updateId(idLama, t);
	}
	

}
