package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.repository.IMasterDokumenRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class MasterDokumenRepositoryEJB implements IMasterDokumenRepository {

	@Inject
	private MasterDokumenRepositoryJPA dokumenPerusahaanRepository;
	
	@Override
	public List<Dokumen> getAll() {
		return dokumenPerusahaanRepository.getAll();
	}

	@Override
	public Dokumen save(Dokumen t) {
		return dokumenPerusahaanRepository.save(t);
	}

	@Override
	public Dokumen update(Dokumen t) {
		return dokumenPerusahaanRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String Id) {
		return dokumenPerusahaanRepository.delete(Id);
	}
	
	@Override
	public Dokumen updateById(String id, Dokumen dokumen) {
		return dokumenPerusahaanRepository.updateById(id, dokumen);
	}
	
	@Override
	public List<Dokumen> getDaftarMasterDokumen(QueryParamFilters queryParamFilters) {
		return dokumenPerusahaanRepository.getDaftarMasterDokumen(queryParamFilters);
	}
	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return dokumenPerusahaanRepository.getCount(queryParamFilters);
	}

}