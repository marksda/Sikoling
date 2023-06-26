package org.Sikoling.ejb.abstraction.service.dokumen;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli;
import org.Sikoling.ejb.abstraction.repository.IKbliRepository;

public class KbliService implements IKbliService {
	
	private final IKbliRepository kbliRepository;	

	public KbliService(IKbliRepository kbliRepository) {
		this.kbliRepository = kbliRepository;
	}

	@Override
	public Kbli save(Kbli t) throws IOException {
		return kbliRepository.save(t);
	}

	@Override
	public Kbli update(Kbli t) {
		return kbliRepository.update(t);
	}

	@Override
	public Kbli updateId(String idLama, Kbli t) throws IOException {
		return kbliRepository.updateId(idLama, t);
	}

	@Override
	public Kbli delete(Kbli t) throws IOException {
		return kbliRepository.delete(t);
	}

	@Override
	public List<Kbli> getDaftarData(QueryParamFilters queryParamFilters) {
		return kbliRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return kbliRepository.getJumlahData(queryParamFilters);
	}
	
}
