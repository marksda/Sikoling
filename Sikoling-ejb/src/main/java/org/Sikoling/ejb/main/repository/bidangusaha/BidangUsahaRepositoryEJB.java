package org.Sikoling.ejb.main.repository.bidangusaha;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.BidangUsaha;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IBidangUsahaRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class BidangUsahaRepositoryEJB implements IBidangUsahaRepository {
	
	@Inject
	private BidangUsahaRepositoryJPA bidangUsahaRepository;

	@Override
	public BidangUsaha save(BidangUsaha t) throws IOException {
		return bidangUsahaRepository.save(t);
	}

	@Override
	public BidangUsaha update(BidangUsaha t) {
		return bidangUsahaRepository.update(t);
	}

	@Override
	public BidangUsaha delete(BidangUsaha t) throws IOException {
		return bidangUsahaRepository.delete(t);
	}

	@Override
	public List<BidangUsaha> getDaftarData(QueryParamFilters queryParamFilters) {
		return bidangUsahaRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return bidangUsahaRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public BidangUsaha updateId(String idLama, BidangUsaha t) throws IOException {
		return bidangUsahaRepository.updateId(idLama, t);
	}


}
