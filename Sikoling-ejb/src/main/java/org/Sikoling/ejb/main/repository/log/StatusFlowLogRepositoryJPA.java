package org.Sikoling.ejb.main.repository.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.entity.log.StatusFlowLog;
import org.Sikoling.ejb.abstraction.repository.IStatusFlowLogRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class StatusFlowLogRepositoryJPA implements IStatusFlowLogRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;	

	public StatusFlowLogRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public StatusFlowLog save(StatusFlowLog t) throws IOException {
		try {
			StatusFlowLogData statusFlowLogData = dataConverter.convertStatusFlowLogToStatusFlowData(t);
			entityManager.persist(statusFlowLogData);
			entityManager.flush();
			
			return dataConverter.convertStatusFlowLogDataToStatusFlowLog(statusFlowLogData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}
		
	}

	@Override
	public StatusFlowLog update(StatusFlowLog t) {
		StatusFlowLogData statusFlowLogData = dataConverter.convertStatusFlowLogToStatusFlowData(t);
		statusFlowLogData = entityManager.merge(statusFlowLogData);
		
		return dataConverter.convertStatusFlowLogDataToStatusFlowLog(statusFlowLogData);
	}

	@Override
	public StatusFlowLog updateId(String idLama, StatusFlowLog t) throws IOException {
		StatusFlowLogData dataLama = entityManager.find(StatusFlowLogData.class, idLama);
		if(dataLama != null) {
			StatusFlowLogData skalaUsahaData = dataConverter.convertStatusFlowLogToStatusFlowData(t);
			entityManager.remove(dataLama);	
			StatusFlowLogData dataTermerge = entityManager.merge(skalaUsahaData);
			entityManager.flush();
			return dataConverter.convertStatusFlowLogDataToStatusFlowLog(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public StatusFlowLog delete(StatusFlowLog t) throws IOException {
		StatusFlowLogData statusFlowLogData = entityManager.find(StatusFlowLogData.class, t.getId());
		if(statusFlowLogData != null) {
			entityManager.remove(statusFlowLogData);	
			entityManager.flush();
			return dataConverter.convertStatusFlowLogDataToStatusFlowLog(statusFlowLogData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public List<StatusFlowLog> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<StatusFlowLogData> cq = cb.createQuery(StatusFlowLogData.class);
		Root<StatusFlowLogData> root = cq.from(StatusFlowLogData.class);		
		
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
		
		TypedQuery<StatusFlowLogData> q = null;		
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
				.map(d -> dataConverter.convertStatusFlowLogDataToStatusFlowLog(d))
				.collect(Collectors.toList());
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<StatusFlowLogData> root = cq.from(StatusFlowLogData.class);		
		
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
