package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
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
	public DeleteResponse delete(String id) {
		return posisiTahapPemberkasanRepository.delete(id);
	}

	@Override
	public PosisiTahapPemberkasan save(PosisiTahapPemberkasan t) {
		return posisiTahapPemberkasanRepository.save(t);
	}

	@Override
	public PosisiTahapPemberkasan update(PosisiTahapPemberkasan t) {
		return posisiTahapPemberkasanRepository.update(t);
	}


	@Override
	public PosisiTahapPemberkasan updateById(String id, PosisiTahapPemberkasan posisiTahapPemberkasan) {
		return posisiTahapPemberkasanRepository.updateById(id, posisiTahapPemberkasan);
	}
	

	@Override
	public List<PosisiTahapPemberkasan> getDaftarPosisiTahapPemberkasan(QueryParamFilters queryParamFilters) {
		return posisiTahapPemberkasanRepository.getDaftarPosisiTahapPemberkasan(queryParamFilters);
	}
	

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return posisiTahapPemberkasanRepository.getCount(queryParamFilters);
	}

}
