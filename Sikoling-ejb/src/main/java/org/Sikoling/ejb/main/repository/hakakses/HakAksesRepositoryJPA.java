package org.Sikoling.ejb.main.repository.hakakses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.HakAkses;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IHakAksesRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class HakAksesRepositoryJPA implements IHakAksesRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;

	public HakAksesRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public HakAkses save(HakAkses t) throws IOException {
		try {
			HakAksesData hakAksesData = dataConverter.convertHakAksesToHakAksesData(t);
			entityManager.persist(hakAksesData);
			entityManager.flush();
			return dataConverter.convertHakAksesDataToHakAkses(hakAksesData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}
		
	}

	@Override
	public HakAkses update(HakAkses t) {
		HakAksesData hakAksesData = dataConverter.convertHakAksesToHakAksesData(t);
		HakAksesData dataTermerge = entityManager.merge(hakAksesData);
		entityManager.flush();
		return dataConverter.convertHakAksesDataToHakAkses(dataTermerge);
	}

	@Override
	public HakAkses updateId(String idLama, HakAkses t) throws IOException {
		HakAksesData dataLama = entityManager.find(HakAksesData.class, idLama);
		if(dataLama != null) {
			HakAksesData skalaUsahaData = dataConverter.convertHakAksesToHakAksesData(t);
			entityManager.remove(dataLama);	
			HakAksesData dataTermerge = entityManager.merge(skalaUsahaData);
			entityManager.flush();
			return dataConverter.convertHakAksesDataToHakAkses(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public HakAkses delete(HakAkses t) throws IOException {
		HakAksesData hakAksesData = entityManager.find(HakAksesData.class, t.getId());
		if(hakAksesData != null) {
			entityManager.remove(hakAksesData);	
			entityManager.flush();
			return dataConverter.convertHakAksesDataToHakAkses(hakAksesData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}		
	}

	@Override
	public List<HakAkses> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<HakAksesData> cq = cb.createQuery(HakAksesData.class);
		Root<HakAksesData> root = cq.from(HakAksesData.class);
		
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
			case "keterangan":
				daftarPredicate.add(cb.like(cb.lower(root.get("keterangan")), "%"+filter.getValue().toLowerCase()+"%"));
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
				case "keterangan":
					if(sort.getValue().equals("ASC")) {
						cq.orderBy(cb.asc(root.get("keterangan")));
					}
					else {
						cq.orderBy(cb.desc(root.get("keterangan")));
					}
					break;
				default:
					break;
				}			
			}
				
			TypedQuery<HakAksesData> q = null;		
			if( queryParamFilters.getPageSize() != null && queryParamFilters.getPageSize() > 0) { //limit query result
				q = entityManager.createQuery(cq)
						.setMaxResults(queryParamFilters.getPageSize())
						.setFirstResult((queryParamFilters.getPageNumber()-1)*queryParamFilters.getPageSize());
			}
			else {
				q = entityManager.createQuery(cq);
			}
			
			return q.getResultList()
					.stream()
					.map(d -> dataConverter.convertHakAksesDataToHakAkses(d))
					.collect(Collectors.toList());
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<HakAksesData> root = cq.from(HakAksesData.class);		
		
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
			case "keterangan":
				daftarPredicate.add(cb.like(cb.lower(root.get("keterangan")), "%"+filter.getValue().toLowerCase()+"%"));
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