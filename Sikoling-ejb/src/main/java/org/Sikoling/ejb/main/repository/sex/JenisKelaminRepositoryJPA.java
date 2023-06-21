package org.Sikoling.ejb.main.repository.sex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IJenisKelaminRepository;
import org.Sikoling.ejb.main.repository.DataConverter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class JenisKelaminRepositoryJPA implements IJenisKelaminRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;		

	public JenisKelaminRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public JenisKelamin save(JenisKelamin t) throws IOException {
		try {
			JenisKelaminData jenisKelaminData = dataConverter.convertJenisKelaminToJenisKelaminData(t);
			entityManager.persist(t);
			entityManager.flush();
			return dataConverter.convertJenisKelaminDataToJenisKelamin(jenisKelaminData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}
		
	}
	
	@Override
	public JenisKelamin update(JenisKelamin t) {
		JenisKelaminData jenisKelaminData = dataConverter.convertJenisKelaminToJenisKelaminData(t);
		JenisKelaminData dataTermerge = entityManager.merge(jenisKelaminData);	
		entityManager.flush();
		return dataConverter.convertJenisKelaminDataToJenisKelamin(dataTermerge);
	}

	@Override
	public JenisKelamin updateId(String idLama, JenisKelamin t) throws IOException {
		JenisKelaminData dataLama = entityManager.find(JenisKelaminData.class, idLama);
		if(dataLama != null) {
			JenisKelaminData jenisKelaminData = dataConverter.convertJenisKelaminToJenisKelaminData(t);
			entityManager.remove(dataLama);	
			JenisKelaminData dataTermerge = entityManager.merge(jenisKelaminData);
			entityManager.flush();
			return dataConverter.convertJenisKelaminDataToJenisKelamin(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public JenisKelamin delete(JenisKelamin t) throws IOException {
		JenisKelaminData jenisKelaminData = entityManager.find(JenisKelaminData.class, t.getId());
		if(jenisKelaminData != null) {
			entityManager.remove(jenisKelaminData);	
			entityManager.flush();
			return dataConverter.convertJenisKelaminDataToJenisKelamin(jenisKelaminData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public List<JenisKelamin> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<JenisKelaminData> cq = cb.createQuery(JenisKelaminData.class);
		Root<JenisKelaminData> root = cq.from(JenisKelaminData.class);		
		
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
				daftarPredicate.add(cb.like(cb.lower(root.get("nama")), filter.getValue().toLowerCase()+"%"));
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
		
		TypedQuery<JenisKelaminData> q = null;		
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
				.map(d -> dataConverter.convertJenisKelaminDataToJenisKelamin(d))
				.collect(Collectors.toList());
	}
		
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<JenisKelaminData> root = cq.from(JenisKelaminData.class);		
		
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
