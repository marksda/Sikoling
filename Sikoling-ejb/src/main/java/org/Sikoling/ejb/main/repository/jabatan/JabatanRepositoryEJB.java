package org.Sikoling.ejb.main.repository.jabatan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
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
	private JabatanRepositoryJPA jabatanRepositoryJPA;

	@Override
	public List<Jabatan> getAll() {
		return jabatanRepositoryJPA.getAll();
	}

	@Override
	public Jabatan save(Jabatan t) {
		return jabatanRepositoryJPA.save(t);
	}

	@Override
	public Jabatan update(Jabatan t) {
		return jabatanRepositoryJPA.update(t);
	}

	
	@Override
	public Jabatan updateById(String id, Jabatan jabatan) {
		return jabatanRepositoryJPA.updateById(id, jabatan);
	}
	

	@Override
	public DeleteResponse delete(String id) {
		return jabatanRepositoryJPA.delete(id);
	}
	

	@Override
	public List<Jabatan> getDaftarJabatan(QueryParamFilters queryParamFilters) {
		return jabatanRepositoryJPA.getDaftarJabatan(queryParamFilters);
	}
	

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return jabatanRepositoryJPA.getCount(queryParamFilters);
	}
	
}
