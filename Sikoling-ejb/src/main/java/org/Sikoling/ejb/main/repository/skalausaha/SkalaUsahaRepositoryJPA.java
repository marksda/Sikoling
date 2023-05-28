package org.Sikoling.ejb.main.repository.skalausaha;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.ISkalaUsahaRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class SkalaUsahaRepositoryJPA implements ISkalaUsahaRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;	
	
	public SkalaUsahaRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public List<SkalaUsaha> getAll() {
		return entityManager.createNamedQuery("SkalaUsahaData.findAll", SkalaUsahaData.class)
				.getResultList()
				.stream()
				.map(t -> dataConverter.convertSkalaUsahaDataToSkalaUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public SkalaUsaha save(SkalaUsaha t) {
		SkalaUsahaData skalaUsahaData = dataConverter.convertSkalaUsahaToSkalaUsahaData(t);
		entityManager.persist(skalaUsahaData);
		entityManager.flush();		
		return dataConverter.convertSkalaUsahaDataToSkalaUsaha(skalaUsahaData);
	}
	
	@Override
	public DeleteResponse delete(String id) {
		SkalaUsahaData skalaUsahaData = entityManager.find(SkalaUsahaData.class, id);
		entityManager.remove(skalaUsahaData);	
		return new DeleteResponse(true, id);
	}

	@Override
	public SkalaUsaha update(SkalaUsaha t) {
		SkalaUsahaData skalaUsahaData = dataConverter.convertSkalaUsahaToSkalaUsahaData(t);
		skalaUsahaData = entityManager.merge(skalaUsahaData);		
		return dataConverter.convertSkalaUsahaDataToSkalaUsaha(skalaUsahaData);
	}
	
	@Override
	public SkalaUsaha updateById(String id, SkalaUsaha skalaUsaha) {
		String idBaru = skalaUsaha.getId();
		SkalaUsahaData skalaUsahaData = dataConverter.convertSkalaUsahaToSkalaUsahaData(skalaUsaha);
		skalaUsahaData.setId(id);
		skalaUsahaData = entityManager.merge(skalaUsahaData);
		if(!idBaru.equals(id)) {
			skalaUsahaData.setId(idBaru);
			entityManager.flush();
		}
		return dataConverter.convertSkalaUsahaDataToSkalaUsaha(skalaUsahaData);
	}

	@Override
	public List<SkalaUsaha> getDaftarSkalaUsaha(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<SkalaUsahaData> cq = cb.createQuery(SkalaUsahaData.class);
		Root<SkalaUsahaData> root = cq.from(SkalaUsahaData.class);		
		
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
			case "singkatan":
				daftarPredicate.add(cb.like(cb.lower(root.get("singkatan")), "%"+filter.getValue().toLowerCase()+"%"));
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
			case "singkatan":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("singkatan")));
				}
				else {
					cq.orderBy(cb.desc(root.get("singkatan")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<SkalaUsahaData> q = null;		
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
				.map(d -> dataConverter.convertSkalaUsahaDataToSkalaUsaha(d))
				.collect(Collectors.toList());
	}
	

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<SkalaUsahaData> root = cq.from(SkalaUsahaData.class);		
		
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
			case "singkatan":
				daftarPredicate.add(cb.like(cb.lower(root.get("singkatan")), "%"+filter.getValue().toLowerCase()+"%"));
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
