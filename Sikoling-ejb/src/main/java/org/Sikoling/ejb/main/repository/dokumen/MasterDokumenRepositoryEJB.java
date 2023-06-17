package org.Sikoling.ejb.main.repository.dokumen;

import java.io.IOException;
import java.util.List;

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
	public Dokumen save(Dokumen t) throws IOException {
		return dokumenPerusahaanRepository.save(t);
	}

	@Override
	public Dokumen update(Dokumen t) {
		return dokumenPerusahaanRepository.update(t);
	}

	@Override
	public Dokumen delete(Dokumen t) throws IOException {
		return dokumenPerusahaanRepository.delete(t);
	}

	@Override
	public List<Dokumen> getDaftarData(QueryParamFilters queryParamFilters) {
		return dokumenPerusahaanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return dokumenPerusahaanRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public Dokumen updateId(String idLama, Dokumen t) throws IOException {
		return dokumenPerusahaanRepository.updateId(idLama, t);
	}
	
	

}