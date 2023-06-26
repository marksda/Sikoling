package org.Sikoling.ejb.main.repository.dokumen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli;
import org.Sikoling.ejb.abstraction.repository.IKbliRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class Kbli2020RepositoryJPA implements IKbliRepository {	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;

	public Kbli2020RepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public Kbli save(Kbli t) throws IOException {
		try {
			KbliData kbliData = dataConverter.convertKbli2020ToKbli2020Data(t);
			entityManager.persist(kbliData);
			entityManager.flush();
			return dataConverter.convertKbli2022DataToKbli2020(kbliData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}
	}

	@Override
	public Kbli update(Kbli t) {
		KbliData kbliData = dataConverter.convertKbli2020ToKbli2020Data(t);
		KbliData dataTermerge = entityManager.merge(kbliData);
		entityManager.flush();
		return dataConverter.convertKbli2022DataToKbli2020(dataTermerge);
	}

	@Override
	public Kbli updateId(String idLama, Kbli t) throws IOException {
		KbliData dataLama = entityManager.find(KbliData.class, t.getKode());
		
		if(dataLama != null) {
			KbliData kbli2020Data = dataConverter.convertKbli2020ToKbli2020Data(t);
			entityManager.remove(dataLama);	
			KbliData dataTermerge = entityManager.merge(kbli2020Data);
			entityManager.flush();
			return dataConverter.convertKbli2022DataToKbli2020(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}		
	}
	
	
	@Override
	public Kbli delete(Kbli t) throws IOException {
		KbliData kbliData = entityManager.find(KbliData.class, t.getKode());
		if(kbliData != null) {
			entityManager.remove(kbliData);
			entityManager.flush();
			return dataConverter.convertKbli2022DataToKbli2020(kbliData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}		
	}
	
	@Override
	public List<Kbli> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<KbliData> cq = cb.createQuery(KbliData.class);
		Root<KbliData> root = cq.from(KbliData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.getFilters().iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "kode":
				daftarPredicate.add(cb.like(cb.lower(root.get("id")), filter.getValue().toLowerCase()+"%"));
				break;
			case "nama":
				daftarPredicate.add(cb.like(cb.lower(root.get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "kategori":
				daftarPredicate.add(cb.equal(root.get("kategori"), filter.getValue()));
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
			case "kode":
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
			case "kategori":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("kategori")));
				}
				else {
					cq.orderBy(cb.desc(root.get("kategori")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<KbliData> q = null;		
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
				.map(d -> dataConverter.convertKbli2022DataToKbli2020(d))
				.collect(Collectors.toList());
	}
	
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<KbliData> root = cq.from(KbliData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "kode":
				daftarPredicate.add(cb.like(cb.lower(root.get("id")), filter.getValue().toLowerCase()+"%"));
				break;
			case "nama":
				daftarPredicate.add(cb.like(cb.lower(root.get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "kategori":
				daftarPredicate.add(cb.equal(root.get("kategori"), filter.getValue()));
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
