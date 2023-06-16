package org.Sikoling.ejb.main.repository.bidangusaha;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.BidangUsaha;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IBidangUsahaRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class BidangUsahaRepositoryJPA implements IBidangUsahaRepository {
	
	private final EntityManager entityManager;	
	private final DataConverter dataConverter;	

	public BidangUsahaRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public BidangUsaha save(BidangUsaha t) throws IOException {
		try {
			BidangUsahaData bidangUsahaData = dataConverter.convertBidangUsahaToBidangUsahaData(t);
			entityManager.persist(bidangUsahaData);
			entityManager.flush();
			return dataConverter.convertBidangUsahaDataToBidangUsaha(bidangUsahaData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}
		
	}

	@Override
	public BidangUsaha update(BidangUsaha t) {
		BidangUsahaData bidangUsahaData = dataConverter.convertBidangUsahaToBidangUsahaData(t);
		BidangUsahaData dataTermerge = entityManager.merge(bidangUsahaData);
		return dataConverter.convertBidangUsahaDataToBidangUsaha(dataTermerge);
	}
	
	@Override
	public BidangUsaha updateId(String idLama, BidangUsaha t) throws IOException {
		BidangUsahaData dataLama = entityManager.find(BidangUsahaData.class, idLama);
		if(dataLama != null) {
			BidangUsahaData bidangUsahaData = dataConverter.convertBidangUsahaToBidangUsahaData(t);
			entityManager.remove(dataLama);	
			BidangUsahaData dataTermerge = entityManager.merge(bidangUsahaData);
			entityManager.flush();
			return dataConverter.convertBidangUsahaDataToBidangUsaha(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public BidangUsaha delete(BidangUsaha t) throws IOException {
		BidangUsahaData bidangUsahaData = entityManager.find(BidangUsahaData.class, t.getId());
		if(bidangUsahaData != null) {
			entityManager.remove(bidangUsahaData);	
			entityManager.flush();
			return dataConverter.convertBidangUsahaDataToBidangUsaha(bidangUsahaData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public List<BidangUsaha> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<BidangUsahaData> cq = cb.createQuery(BidangUsahaData.class);
		Root<BidangUsahaData> root = cq.from(BidangUsahaData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.getFilters().iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "nama":
				daftarPredicate.add(cb.like(cb.lower(root.get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			default:
				break;
			}			
		}
		
		if(daftarPredicate.isEmpty()) {
			cq.select(root);
		}
		else {
			cq.select(root).where(cb.and(daftarPredicate.toArray(new Predicate[0])));
		}
		
		// sort clause
		Iterator<SortOrder> iterSort = queryParamFilters.getSortOrders().iterator();
				
		while (iterSort.hasNext()) {
			SortOrder sort = (SortOrder) iterSort.next();
			switch (sort.getFieldName()) {
			case "id":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("id")));
				}
				else {
					cq.orderBy(cb.desc(root.get("id")));
				}
				break;
			case "nama":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("nama")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<BidangUsahaData> q = null;		
		if( queryParamFilters.getPageSize() != null && queryParamFilters.getPageSize() > 0) { 
			q = entityManager.createQuery(cq)
					.setMaxResults(queryParamFilters.getPageSize())
					.setFirstResult((queryParamFilters.getPageNumber()-1)*queryParamFilters.getPageSize());
		}
		else {
			q = entityManager.createQuery(cq);
		}
		
		return q.getResultList()
				.stream()
				.map(d -> dataConverter.convertBidangUsahaDataToBidangUsaha(d))
				.collect(Collectors.toList());
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<BidangUsahaData> root = cq.from(BidangUsahaData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "nama":
				daftarPredicate.add(cb.like(cb.lower(root.get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			default:
				break;
			}			
		}
		
		if(daftarPredicate.isEmpty()) {
			cq.select(cb.count(root));
		}
		else {
			cq.select(cb.count(root)).where(cb.and(daftarPredicate.toArray(new Predicate[0])));
		}
		
		return entityManager.createQuery(cq).getSingleResult();
	}	
}
