package org.Sikoling.ejb.main.repository.permohonan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonan;
import org.Sikoling.ejb.abstraction.repository.IKategoriPermohonanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class KategoriPermohonanRepositoryJPA implements IKategoriPermohonanRepository {

	private final EntityManager entityManager;
	private final DataConverter dataConverter;
	
	public KategoriPermohonanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public KategoriPermohonan save(KategoriPermohonan t) throws IOException {
		try {
			KategoriPermohonanData kategoriPermohonanData = dataConverter.convertKategoriPermohonanToKategoriPermohonanData(t);
			entityManager.persist(kategoriPermohonanData);
			entityManager.flush();
			return dataConverter.convertKategoriPermohonanDataToKategoriPermohonan(kategoriPermohonanData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}
		
	}

	@Override
	public KategoriPermohonan update(KategoriPermohonan t) {
		KategoriPermohonanData kategoriPermohonanData = dataConverter.convertKategoriPermohonanToKategoriPermohonanData(t);
		KategoriPermohonanData dataTermerge = entityManager.merge(kategoriPermohonanData);	
		entityManager.flush();
		return dataConverter.convertKategoriPermohonanDataToKategoriPermohonan(dataTermerge);
	}

	@Override
	public KategoriPermohonan updateId(String idLama, KategoriPermohonan t) throws IOException {
		KategoriPermohonanData dataLama = entityManager.find(KategoriPermohonanData.class, idLama);
		if(dataLama != null) {
			KategoriPermohonanData kategoriPermohonanData = dataConverter.convertKategoriPermohonanToKategoriPermohonanData(t);
			entityManager.remove(dataLama);	
			KategoriPermohonanData dataTermerge = entityManager.merge(kategoriPermohonanData);
			entityManager.flush();
			return dataConverter.convertKategoriPermohonanDataToKategoriPermohonan(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public KategoriPermohonan delete(KategoriPermohonan t) throws IOException {
		KategoriPermohonanData kategoriPermohonanData = entityManager.find(KategoriPermohonanData.class, t.getId());
		if(kategoriPermohonanData != null) {
			entityManager.remove(kategoriPermohonanData);	
			entityManager.flush();
			return dataConverter.convertKategoriPermohonanDataToKategoriPermohonan(kategoriPermohonanData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public List<KategoriPermohonan> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<KategoriPermohonanData> cq = cb.createQuery(KategoriPermohonanData.class);
		Root<KategoriPermohonanData> root = cq.from(KategoriPermohonanData.class);		
		
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
		
		TypedQuery<KategoriPermohonanData> q = null;		
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
				.map(d -> dataConverter.convertKategoriPermohonanDataToKategoriPermohonan(d))
				.collect(Collectors.toList());
	}
	
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<KategoriPermohonanData> root = cq.from(KategoriPermohonanData.class);		
		
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
