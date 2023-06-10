package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
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
	private Kbli2020RepositoryJPA kbliRepository;
	
	@Override
	public List<Kbli2020> getAll() {
		return kbliRepository.getAll();
	}

	@Override
	public Kbli2020 save(Kbli2020 t) {
		return kbliRepository.save(t);
	}

	@Override
	public Kbli2020 update(Kbli2020 t) {
		return kbliRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String kode) {
		return kbliRepository.delete(kode);
	}

	@Override
	public Kbli2020 updateById(String id, Kbli2020 kbli) {
		return kbliRepository.updateById(id, kbli);
	}


	@Override
	public List<Kbli2020> getDaftarKbli2020(QueryParamFilters queryParamFilters) {
		return kbliRepository.getDaftarKbli2020(queryParamFilters);
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return kbliRepository.getCount(queryParamFilters);
	}

}
