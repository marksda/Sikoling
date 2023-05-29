package org.Sikoling.ejb.main.repository.permohonan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatusPengurusPermohonan;
import org.Sikoling.ejb.abstraction.repository.IStatusPengurusPermohonanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class StatusPengurusPermohonanRepositoryJPA implements IStatusPengurusPermohonanRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;

	public StatusPengurusPermohonanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public List<StatusPengurusPermohonan> getAll() {
		return entityManager.createNamedQuery("StatusPengurusPermohonanData.findAll", StatusPengurusPermohonanData.class)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertStatusPengurusPermohonanDataToStatusPengurusPermohonan(d))
				.collect(Collectors.toList());
	}

	@Override
	public StatusPengurusPermohonan save(StatusPengurusPermohonan t) {
		StatusPengurusPermohonanData statusPengurusPermohonanData = dataConverter.convertStatusPengurusPermohonanToKategoriPengurusPermohonanData(t);
		entityManager.persist(statusPengurusPermohonanData);
		entityManager.flush();
		
		return dataConverter.convertStatusPengurusPermohonanDataToStatusPengurusPermohonan(statusPengurusPermohonanData);
	}

	@Override
	public StatusPengurusPermohonan update(StatusPengurusPermohonan t) {
		StatusPengurusPermohonanData statusPengurusPermohonanData = dataConverter.convertStatusPengurusPermohonanToKategoriPengurusPermohonanData(t);
		statusPengurusPermohonanData = entityManager.merge(statusPengurusPermohonanData);
		return dataConverter.convertStatusPengurusPermohonanDataToStatusPengurusPermohonan(statusPengurusPermohonanData);
	}

	@Override
	public DeleteResponse delete(String id) {
		StatusPengurusPermohonanData statusPengurusPermohonanData = entityManager.find(StatusPengurusPermohonanData.class, id);
		entityManager.remove(statusPengurusPermohonanData);			
		return new DeleteResponse(true, id);
	}


	@Override
	public List<StatusPengurusPermohonan> getDaftarStatusPengurusPermohonan(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<StatusPengurusPermohonanData> cq = cb.createQuery(StatusPengurusPermohonanData.class);
		Root<StatusPengurusPermohonanData> root = cq.from(StatusPengurusPermohonanData.class);		
		
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
		
		TypedQuery<StatusPengurusPermohonanData> q = null;		
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
				.map(d -> dataConverter.convertStatusPengurusPermohonanDataToStatusPengurusPermohonan(d))
				.collect(Collectors.toList());
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<StatusPengurusPermohonanData> root = cq.from(StatusPengurusPermohonanData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "kategori_permohonan":
				daftarPredicate.add(cb.equal(root.get("nama"), filter.getValue()));
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

	
	@Override
	public StatusPengurusPermohonan updateById(String id, StatusPengurusPermohonan statusPengurusPermohonan) {
		String idBaru = statusPengurusPermohonan.getId();
		StatusPengurusPermohonanData statusPengurusPermohonanData = dataConverter.convertStatusPengurusPermohonanToKategoriPengurusPermohonanData(statusPengurusPermohonan);
		statusPengurusPermohonanData.setId(id);
		statusPengurusPermohonanData = entityManager.merge(statusPengurusPermohonanData);
		if(!idBaru.equals(id)) {
			statusPengurusPermohonanData.setId(idBaru);
			entityManager.flush();
		}
		return dataConverter.convertStatusPengurusPermohonanDataToStatusPengurusPermohonan(statusPengurusPermohonanData);
	}

	
}
