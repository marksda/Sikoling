package org.Sikoling.ejb.abstraction.service.dokumen;

import java.io.IOException;
import java.util.List;

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
	public Kbli2020 save(Kbli2020 t) throws IOException {
		return kbliRepository.save(t);
	}

	@Override
	public Kbli2020 update(Kbli2020 t) {
		return kbliRepository.update(t);
	}

	@Override
	public Kbli2020 updateId(String idLama, Kbli2020 t) throws IOException {
		return kbliRepository.updateId(idLama, t);
	}

	@Override
	public Kbli2020 delete(Kbli2020 t) throws IOException {
		return kbliRepository.delete(t);
	}

	@Override
	public List<Kbli2020> getDaftarData(QueryParamFilters queryParamFilters) {
		return kbliRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return kbliRepository.getJumlahData(queryParamFilters);
	}
	
}
