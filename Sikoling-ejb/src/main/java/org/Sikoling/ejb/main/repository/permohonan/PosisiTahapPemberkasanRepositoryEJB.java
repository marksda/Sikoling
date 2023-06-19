package org.Sikoling.ejb.main.repository.permohonan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;
import org.Sikoling.ejb.abstraction.repository.IPosisiTahapPemberkasanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class PosisiTahapPemberkasanRepositoryEJB implements IPosisiTahapPemberkasanRepository {
	
	@Inject
	private PosisiTahapPemberkasanRepositoryJPA posisiTahapPemberkasanRepository;

	@Override
	public PosisiTahapPemberkasan save(PosisiTahapPemberkasan t) throws IOException {
		return posisiTahapPemberkasanRepository.save(t);
	}

	@Override
	public PosisiTahapPemberkasan update(PosisiTahapPemberkasan t) {
		return posisiTahapPemberkasanRepository.update(t);
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

	@Override
	public PosisiTahapPemberkasan updateId(String idLama, PosisiTahapPemberkasan t) throws IOException {
		return posisiTahapPemberkasanRepository.updateId(idLama, t);
	}

}
