package org.Sikoling.ejb.main.repository.bidangusaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.BidangUsaha;
import org.Sikoling.ejb.abstraction.repository.IBidangUsahaRepository;
import jakarta.persistence.EntityManager;

public class BidangUsahaRepositoryJPA implements IBidangUsahaRepository {
	
	private EntityManager entityManager;	

	public BidangUsahaRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public BidangUsaha save(BidangUsaha t) {
		BidangUsahaData bidangUsahaData = convertBidangUsahaToBidangUsahaData(t);
		entityManager.persist(bidangUsahaData);
		entityManager.flush();
		return convertBidangUsahaDataToBidangUsaha(bidangUsahaData);
	}

	@Override
	public BidangUsaha update(BidangUsaha t) {
		BidangUsahaData bidangUsahaData = convertBidangUsahaToBidangUsahaData(t);
		bidangUsahaData = entityManager.merge(bidangUsahaData);
		return convertBidangUsahaDataToBidangUsaha(bidangUsahaData);
	}
	
	@Override
	public List<BidangUsaha> getAll() {
		return entityManager.createNamedQuery("BidangUsahaData.findAll", BidangUsahaData.class)
				.getResultList()
				.stream()
				.map(t -> convertBidangUsahaDataToBidangUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<BidangUsaha> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("BidangUsahaData.findAll", BidangUsahaData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertBidangUsahaDataToBidangUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<BidangUsaha> getByQueryNama(String nama) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("BidangUsahaData.findAllByQueryNama", BidangUsahaData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertBidangUsahaDataToBidangUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<BidangUsaha> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("BidangUsahaData.findAllByQueryNama", BidangUsahaData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertBidangUsahaDataToBidangUsaha(t))
				.collect(Collectors.toList());
	}

	private BidangUsahaData convertBidangUsahaToBidangUsahaData(BidangUsaha bidangUsaha) {
		return new BidangUsahaData(bidangUsaha.getId(), bidangUsaha.getNama());
	}
	
	private BidangUsaha convertBidangUsahaDataToBidangUsaha(BidangUsahaData bidangUsahaData) {
		return new BidangUsaha(bidangUsahaData.getId(), bidangUsahaData.getNama());
	}

}