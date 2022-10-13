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
public class BidangUsahaRepositoryEJB implements IBidangUsahaRepository<BidangUsaha> {
	
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
	public List<BidangUsaha> getByNama(String nama) {
		return bidangUsahaRepository.getByNama(nama);
	}

	@Override
	public List<BidangUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return bidangUsahaRepository.getByNamaAndPage(nama, page, pageSize);
	}

}
