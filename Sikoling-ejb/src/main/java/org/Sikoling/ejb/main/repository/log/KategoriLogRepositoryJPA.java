package org.Sikoling.ejb.main.repository.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.entity.log.KategoriFlowLog;
import org.Sikoling.ejb.abstraction.repository.IKategoriLogRepository;
import org.Sikoling.ejb.main.repository.DataConverter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class KategoriLogRepositoryJPA implements IKategoriLogRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;

	public KategoriLogRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public KategoriFlowLog save(KategoriFlowLog t) throws IOException {
		try {
			KategoriLogData kategoriLogData = dataConverter.convertKategoriFlowLogToKategoriFlowLogData(t);
			entityManager.persist(kategoriLogData);
			entityManager.flush();
			return dataConverter.convertKategoriFlowLogDataToKategoriFlowLog(kategoriLogData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}
		
	}

	@Override
	public KategoriFlowLog update(KategoriFlowLog t) {
		KategoriLogData kategoriLogData = dataConverter.convertKategoriFlowLogToKategoriFlowLogData(t);
		KategoriLogData dataTermerge = entityManager.merge(kategoriLogData);
		entityManager.flush();
		return dataConverter.convertKategoriFlowLogDataToKategoriFlowLog(dataTermerge);
	}

	@Override
	public KategoriFlowLog updateId(String idLama, KategoriFlowLog t) throws IOException {		
		Query query = entityManager.createNamedQuery("KategoriLogData.updateId");
		query.setParameter("idBaru", t.getId());
		query.setParameter("idLama", idLama);
		int updateCount = query.executeUpdate();
		if(updateCount > 0) {
			try {
				KategoriLogData kategoriLogData = dataConverter.convertKategoriFlowLogToKategoriFlowLogData(t);
				KategoriLogData dataTermerge = entityManager.merge(kategoriLogData);
				entityManager.flush();
				return dataConverter.convertKategoriFlowLogDataToKategoriFlowLog(dataTermerge);
			} catch (Exception e) {
				throw new IOException("data id sudah ada");
			}
			
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public KategoriFlowLog delete(KategoriFlowLog t) throws IOException {
		KategoriLogData kategoriLogData = entityManager.find(KategoriLogData.class, t.getId());
		if(kategoriLogData != null) {
			entityManager.remove(kategoriLogData);	
			entityManager.flush();
			return dataConverter.convertKategoriFlowLogDataToKategoriFlowLog(kategoriLogData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public List<KategoriFlowLog> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<KategoriLogData> cq = cb.createQuery(KategoriLogData.class);
		Root<KategoriLogData> root = cq.from(KategoriLogData.class);		
		
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
		
		TypedQuery<KategoriLogData> q = null;		
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
				.map(d -> dataConverter.convertKategoriFlowLogDataToKategoriFlowLog(d))
				.collect(Collectors.toList());
	}
	
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<KategoriLogData> root = cq.from(KategoriLogData.class);		
		
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
