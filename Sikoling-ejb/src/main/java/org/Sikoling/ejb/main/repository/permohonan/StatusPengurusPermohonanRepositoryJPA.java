package org.Sikoling.ejb.main.repository.permohonan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatuswaliPermohonan;
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
	public StatuswaliPermohonan save(StatuswaliPermohonan t) throws IOException {
		try {
			StatusPengurusPermohonanData statusPengurusPermohonanData = dataConverter.convertStatusPengurusPermohonanToStatusPengurusPermohonanData(t);
			entityManager.persist(statusPengurusPermohonanData);
			entityManager.flush();		
			return dataConverter.convertStatusPengurusPermohonanDataToStatusPengurusPermohonan(statusPengurusPermohonanData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}		
	}

	@Override
	public StatuswaliPermohonan update(StatuswaliPermohonan t) {
		StatusPengurusPermohonanData statusPengurusPermohonanData = dataConverter.convertStatusPengurusPermohonanToStatusPengurusPermohonanData(t);
		StatusPengurusPermohonanData dataTermerge = entityManager.merge(statusPengurusPermohonanData);	
		entityManager.flush();
		return dataConverter.convertStatusPengurusPermohonanDataToStatusPengurusPermohonan(dataTermerge);
	}

	@Override
	public StatuswaliPermohonan updateId(String idLama, StatuswaliPermohonan t) throws IOException {
		StatusPengurusPermohonanData dataLama = entityManager.find(StatusPengurusPermohonanData.class, idLama);
		if(dataLama != null) {
			StatusPengurusPermohonanData statusPengurusPermohonanData = dataConverter.convertStatusPengurusPermohonanToStatusPengurusPermohonanData(t);
			entityManager.remove(dataLama);	
			StatusPengurusPermohonanData dataTermerge = entityManager.merge(statusPengurusPermohonanData);
			entityManager.flush();
			return dataConverter.convertStatusPengurusPermohonanDataToStatusPengurusPermohonan(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public StatuswaliPermohonan delete(StatuswaliPermohonan t) throws IOException {
		StatusPengurusPermohonanData statusPengurusPermohonanData = entityManager.find(StatusPengurusPermohonanData.class, t.getId());
		if(statusPengurusPermohonanData != null) {
			entityManager.remove(statusPengurusPermohonanData);	
			entityManager.flush();
			return dataConverter.convertStatusPengurusPermohonanDataToStatusPengurusPermohonan(statusPengurusPermohonanData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public List<StatuswaliPermohonan> getDaftarData(QueryParamFilters queryParamFilters) {
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
	public Long getJumlahData(List<Filter> queryParamFilters) {
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
	
}
