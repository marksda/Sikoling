package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli2020;
import org.Sikoling.ejb.abstraction.repository.IKbliRepository;

public class KbliService implements IKbliService {
	
	private final IKbliRepository kbliRepository;	

	public KbliService(IKbliRepository kbliRepository) {
		this.kbliRepository = kbliRepository;
	}

	@Override
	public Kbli2020 save(Kbli2020 kbli) {
		return kbliRepository.save(kbli);
	}

	@Override
	public Kbli2020 update(Kbli2020 kbli) {
		return kbliRepository.update(kbli);
	}

	@Override
	public Kbli2020 updateById(String id, Kbli2020 kbli) {
		return kbliRepository.updateById(id, kbli);
	}

	@Override
	public DeleteResponse delete(String kode) {
		return kbliRepository.delete(kode);
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
