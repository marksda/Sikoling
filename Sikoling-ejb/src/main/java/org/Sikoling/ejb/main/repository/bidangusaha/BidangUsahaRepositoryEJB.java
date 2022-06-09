package org.Sikoling.ejb.main.repository.bidangusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.BidangUsaha;
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
	public List<BidangUsaha> getAll() {
		return bidangUsahaRepository.getAll();
	}

	@Override
	public BidangUsaha save(BidangUsaha t) {
		return bidangUsahaRepository.save(t);
	}

	@Override
	public BidangUsaha update(BidangUsaha t) {
		return bidangUsahaRepository.update(t);
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
