package org.Sikoling.ejb.main.repository.kecamatan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IKecamatanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class KecamatanRepositoryJPA implements IKecamatanRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;

	public KecamatanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public Kecamatan save(Kecamatan t) throws IOException {
		try {
			KecamatanData kecamatanData = dataConverter.convertKecamatanToKecamatanData(t);
			entityManager.persist(kecamatanData);
			entityManager.flush();
			return dataConverter.convertKecamatanDataToKecamatan(kecamatanData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}
		
	}

	@Override
	public Kecamatan update(Kecamatan t) {
		KecamatanData kecamatanData = dataConverter.convertKecamatanToKecamatanData(t);
		KecamatanData dataTermerge = entityManager.merge(kecamatanData);	
		entityManager.flush();
		return dataConverter.convertKecamatanDataToKecamatan(dataTermerge);
	}

	@Override
	public Kecamatan updateId(String idLama, Kecamatan t) throws IOException {
		KecamatanData dataLama = entityManager.find(KecamatanData.class, idLama);
		if(dataLama != null) {
			KecamatanData kecamatanData = dataConverter.convertKecamatanToKecamatanData(t);
			entityManager.remove(dataLama);	
			KecamatanData dataTermerge = entityManager.merge(kecamatanData);
			entityManager.flush();
			return dataConverter.convertKecamatanDataToKecamatan(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public Kecamatan delete(Kecamatan t) throws IOException {
		KecamatanData kecamatanData = entityManager.find(KecamatanData.class, t.getId());
		if(kecamatanData != null) {
			entityManager.remove(kecamatanData);	
			entityManager.flush();
			return dataConverter.convertKecamatanDataToKecamatan(kecamatanData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public List<Kecamatan> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<KecamatanData> cq = cb.createQuery(KecamatanData.class);
		Root<KecamatanData> root = cq.from(KecamatanData.class);		
		
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
			case "kabupaten":
				daftarPredicate.add(cb.equal(root.get("kabupaten").get("id"), filter.getValue()));
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
			case "kabpaten":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("kabupaten").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("kabupaten").get("nama")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<KecamatanData> q = null;		
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
				.map(d -> dataConverter.convertKecamatanDataToKecamatan(d))
				.collect(Collectors.toList());
	}
		
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<KecamatanData> root = cq.from(KecamatanData.class);		
		
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
			case "kabupaten":
				daftarPredicate.add(cb.equal(root.get("kabupaten").get("id"), filter.getValue()));
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
