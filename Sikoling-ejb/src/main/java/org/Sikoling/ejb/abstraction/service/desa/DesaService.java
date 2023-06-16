package org.Sikoling.ejb.abstraction.service.desa;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IDesaRepository;

public class DesaService implements IDesaService {
	
	private final IDesaRepository desaRepository;

	public DesaService(IDesaRepository desaRepository) {
		this.desaRepository = desaRepository;
	}

	@Override
	public Desa save(Desa t) throws IOException {
		return desaRepository.save(t);
	}

	@Override
	public Desa update(Desa t) {
		return desaRepository.update(t);
	}

	@Override
	public Desa updateId(String idLama, Desa t) throws IOException {
		return desaRepository.updateId(idLama, t);
	}

	@Override
	public Desa delete(Desa t) throws IOException {
		return desaRepository.delete(t);
	}

	@Override
	public List<Desa> getDaftarData(QueryParamFilters queryParamFilters) {
		return desaRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return desaRepository.getJumlahData(queryParamFilters);
	}

}
