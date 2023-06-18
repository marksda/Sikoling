package org.Sikoling.ejb.main.repository.kecamatan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IKecamatanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class KecamatanRepositoryEJB implements IKecamatanRepository {
	
	@Inject
	private KecamatanRepositoryJPA kecamatanRepository;

	@Override
	public Kecamatan save(Kecamatan t) throws IOException {
		return kecamatanRepository.save(t);
	}

	@Override
	public Kecamatan update(Kecamatan t) {
		return kecamatanRepository.update(t);
	}

	@Override
	public Kecamatan delete(Kecamatan t) throws IOException {
		return kecamatanRepository.delete(t);
	}

	@Override
	public List<Kecamatan> getDaftarData(QueryParamFilters queryParamFilters) {
		return kecamatanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return kecamatanRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public Kecamatan updateId(String idLama, Kecamatan t) throws IOException {
		return kecamatanRepository.updateId(idLama, t);
	}

	
}
