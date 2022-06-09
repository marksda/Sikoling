package org.Sikoling.ejb.abstraction.service.bidangusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.BidangUsaha;
import org.Sikoling.ejb.abstraction.repository.IBidangUsahaRepository;

public class BidangUsahaService implements IBidangUsahaService {
	
	private IBidangUsahaRepository bidangUsahaRepository;

	public BidangUsahaService(IBidangUsahaRepository bidangUsahaRepository) {
		super();
		this.bidangUsahaRepository = bidangUsahaRepository;
	}

	@Override
	public BidangUsaha save(BidangUsaha bidangUsaha) {
		return bidangUsahaRepository.save(bidangUsaha);
	}

	@Override
	public BidangUsaha update(BidangUsaha bidangUsaha) {
		return bidangUsahaRepository.update(bidangUsaha);
	}

	@Override
	public List<BidangUsaha> getAll() {
		return bidangUsahaRepository.getAll();
	}

	@Override
	public List<BidangUsaha> getAllByPage(Integer page, Integer pageSize) {
		return bidangUsahaRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<BidangUsaha> getByQueryNama(String nama) {
		return bidangUsahaRepository.getByQueryNama(nama);
	}

	@Override
	public List<BidangUsaha> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return bidangUsahaRepository.getByQueryNamaAndPage(nama, page, pageSize);
	}

}
