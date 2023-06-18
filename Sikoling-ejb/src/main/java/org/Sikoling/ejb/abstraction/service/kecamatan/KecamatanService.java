package org.Sikoling.ejb.abstraction.service.kecamatan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IKecamatanRepository;

public class KecamatanService implements IKecamatanService {
	
	private final IKecamatanRepository kecamatanRepository;	

	public KecamatanService(IKecamatanRepository kecamatanRepository) {
		this.kecamatanRepository = kecamatanRepository;
	}

	@Override
	public Kecamatan save(Kecamatan t) throws IOException {
		return kecamatanRepository.save(t);
	}

	@Override
	public Kecamatan update(Kecamatan t) {
		return kecamatanRepository.update(t);
	}

	@Override
	public Kecamatan updateId(String idLama, Kecamatan t) throws IOException {
		return kecamatanRepository.updateId(idLama, t);
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

}
