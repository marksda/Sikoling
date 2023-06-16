package org.Sikoling.ejb.abstraction.service.bidangusaha;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.BidangUsaha;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IBidangUsahaRepository;

public class BidangUsahaService implements IBidangUsahaService {
	
	private final IBidangUsahaRepository bidangUsahaRepository;

	public BidangUsahaService(IBidangUsahaRepository bidangUsahaRepository) {
		this.bidangUsahaRepository = bidangUsahaRepository;
	}

	@Override
	public BidangUsaha save(BidangUsaha t) throws IOException {
		return bidangUsahaRepository.save(t);
	}

	@Override
	public BidangUsaha update(BidangUsaha t) {
		return bidangUsahaRepository.update(t);
	}

	@Override
	public BidangUsaha updateId(String idLama, BidangUsaha t) throws IOException {
		return bidangUsahaRepository.updateId(idLama, t);
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

}
