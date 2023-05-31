package org.Sikoling.ejb.abstraction.service.jabatan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IJabatanRepository;

public class JabatanService implements IJabatanService {
	
	private final IJabatanRepository jabatanRepository;

	public JabatanService(IJabatanRepository jabatanRepository) {
		super();
		this.jabatanRepository = jabatanRepository;
	}

	@Override
 	public Jabatan save(Jabatan jabatan) {
		return jabatanRepository.save(jabatan);
	}

	@Override
	public Jabatan update(Jabatan jabatan) {
		return jabatanRepository.update(jabatan);
	}

	
	@Override
	public Jabatan updateById(String id, Jabatan jabatan) {
		return jabatanRepository.updateById(id, jabatan);
	}

	
	@Override
	public DeleteResponse delete(String id) {
		return jabatanRepository.delete(id);
	}

	
	@Override
	public List<Jabatan> getDaftarJabatan(QueryParamFilters queryParamFilters) {
		return jabatanRepository.getDaftarJabatan(queryParamFilters);
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return jabatanRepository.getCount(queryParamFilters);
	}
	
}
