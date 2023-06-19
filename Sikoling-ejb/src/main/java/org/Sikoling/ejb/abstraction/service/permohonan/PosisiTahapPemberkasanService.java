package org.Sikoling.ejb.abstraction.service.permohonan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;
import org.Sikoling.ejb.abstraction.repository.IPosisiTahapPemberkasanRepository;

public class PosisiTahapPemberkasanService implements IPosisiTahapPemberkasanService {
	
	private final IPosisiTahapPemberkasanRepository posisiTahapPemberkasanRepository;

	public PosisiTahapPemberkasanService(IPosisiTahapPemberkasanRepository posisiTahapPemberkasanRepository) {
		this.posisiTahapPemberkasanRepository = posisiTahapPemberkasanRepository;
	}

	@Override
	public PosisiTahapPemberkasan save(PosisiTahapPemberkasan t) throws IOException {
		return posisiTahapPemberkasanRepository.save(t);
	}

	@Override
	public PosisiTahapPemberkasan update(PosisiTahapPemberkasan t) {
		return posisiTahapPemberkasanRepository.update(t);
	}

	@Override
	public PosisiTahapPemberkasan updateId(String idLama, PosisiTahapPemberkasan t) throws IOException {
		return posisiTahapPemberkasanRepository.updateId(idLama, t);
	}

	@Override
	public PosisiTahapPemberkasan delete(PosisiTahapPemberkasan t) throws IOException {
		return posisiTahapPemberkasanRepository.delete(t);
	}

	@Override
	public List<PosisiTahapPemberkasan> getDaftarData(QueryParamFilters queryParamFilters) {
		return posisiTahapPemberkasanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return posisiTahapPemberkasanRepository.getJumlahData(queryParamFilters);
	}

}
