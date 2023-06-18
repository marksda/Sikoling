package org.Sikoling.ejb.main.repository.penanggungjawab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IPenanggungJawabRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class PenanggungJawabRepositoryJPA implements IPenanggungJawabRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;	

	public PenanggungJawabRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public PenanggungJawab save(PenanggungJawab t) throws IOException {
		try {
			PenanggungJawabData skalaUsahaData = dataConverter.convertPenanggungJawabToPenanggungJawabData(t);
			entityManager.persist(skalaUsahaData);
			entityManager.flush();		
			return dataConverter.convertPenanggungJawabDataToPenanggungJawab(skalaUsahaData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}		
	}

	@Override
	public PenanggungJawab update(PenanggungJawab t) {
		PenanggungJawabData penanggungJawabData = dataConverter.convertPenanggungJawabToPenanggungJawabData(t);
		PenanggungJawabData dataTermerge = entityManager.merge(penanggungJawabData);	
		entityManager.flush();
		return dataConverter.convertPenanggungJawabDataToPenanggungJawab(dataTermerge);
	}

	@Override
	public PenanggungJawab updateId(String idLama, PenanggungJawab t) throws IOException {
		PenanggungJawabData dataLama = entityManager.find(PenanggungJawabData.class, idLama);
		if(dataLama != null) {
			PenanggungJawabData skalaUsahaData = dataConverter.convertPenanggungJawabToPenanggungJawabData(t);
			entityManager.remove(dataLama);	
			PenanggungJawabData dataTermerge = entityManager.merge(skalaUsahaData);
			entityManager.flush();
			return dataConverter.convertPenanggungJawabDataToPenanggungJawab(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public PenanggungJawab delete(PenanggungJawab t) throws IOException {
		PenanggungJawabData penanggungJawabData = entityManager.find(PenanggungJawabData.class, t.getId());
		if(penanggungJawabData != null) {
			entityManager.remove(penanggungJawabData);	
			entityManager.flush();
			return dataConverter.convertPenanggungJawabDataToPenanggungJawab(penanggungJawabData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public List<PenanggungJawab> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PenanggungJawabData> cq = cb.createQuery(PenanggungJawabData.class);
		Root<PenanggungJawabData> root = cq.from(PenanggungJawabData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.getFilters().iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "person":
				daftarPredicate.add(cb.equal(root.get("person").get("id"), filter.getValue()));
				break;
			case "perusahaan":
				daftarPredicate.add(cb.equal(root.get("pemrakarsa").get("id"), filter.getValue()));
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
			case "person":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("person").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("person").get("nama")));
				}
				break;
			case "perusahaan":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("pemrakarsa").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("pemrakarsa").get("nama")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<PenanggungJawabData> q = null;		
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
				.map(d -> dataConverter.convertPenanggungJawabDataToPenanggungJawab(d))
				.collect(Collectors.toList());
	}
		
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<PenanggungJawabData> root = cq.from(PenanggungJawabData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "person":
				daftarPredicate.add(cb.equal(root.get("person").get("id"), filter.getValue()));
				break;
			case "perusahaan":
				daftarPredicate.add(cb.equal(root.get("pemrakarsa").get("id"), filter.getValue()));
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
