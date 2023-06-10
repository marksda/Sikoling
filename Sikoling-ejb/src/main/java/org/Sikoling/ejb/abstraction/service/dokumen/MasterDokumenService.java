package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.repository.IMasterDokumenRepository;

public class MasterDokumenService implements IMasterDokumenService {
	
	private final IMasterDokumenRepository dokumenRepository;
	
	public MasterDokumenService(IMasterDokumenRepository dokumenRepository) {
		this.dokumenRepository = dokumenRepository;
	}

	@Override
	public Dokumen save(Dokumen dokumen) {
		return dokumenRepository.save(dokumen);
	}

	@Override
	public Dokumen update(Dokumen dokumen) {
		return dokumenRepository.update(dokumen);
	}

	@Override
	public DeleteResponse delete(String Id) {
		return dokumenRepository.delete(Id);
	}
	
	@Override
	public Dokumen updateById(String id, Dokumen dokumen) {
		return dokumenRepository.updateById(id, dokumen);
	}

	
	@Override
	public List<Dokumen> getDaftarMasterDokumen(QueryParamFilters queryParamFilters) {
		return dokumenRepository.getDaftarMasterDokumen(queryParamFilters);
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return dokumenRepository.getCount(queryParamFilters);
	}

}
