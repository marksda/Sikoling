package org.Sikoling.ejb.main.repository.kategoriproduk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.KategoriProduk;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IKategoriProdukRepository;
import org.Sikoling.ejb.main.repository.DataConverter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class KategoriProdukRepositoryJPA implements IKategoriProdukRepository {
	
	private final EntityManager entityManager;	
	private final DataConverter dataConverter;

	public KategoriProdukRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public KategoriProduk save(KategoriProduk t) throws IOException {
		try {
			KategoriProdukData kategoriProdukData = dataConverter.convertKategoriProdukToKategoriProdukData(t);
			entityManager.persist(kategoriProdukData);
			entityManager.flush();
			
			return dataConverter.convertKategoriProdukDataToKategoriProduk(kategoriProdukData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}
		
	}

	@Override
	public KategoriProduk update(KategoriProduk t) {
		KategoriProdukData kategoriProdukData = dataConverter.convertKategoriProdukToKategoriProdukData(t);
		KategoriProdukData dataTermerge = entityManager.merge(kategoriProdukData);
		entityManager.flush();
		return dataConverter.convertKategoriProdukDataToKategoriProduk(dataTermerge);
	}

	@Override
	public KategoriProduk updateId(String idLama, KategoriProduk t) throws IOException {
		KategoriProdukData dataLama = entityManager.find(KategoriProdukData.class, idLama);
		if(dataLama != null) {
			KategoriProdukData kategoriProdukData = dataConverter.convertKategoriProdukToKategoriProdukData(t);
			entityManager.remove(dataLama);	
			KategoriProdukData dataTermerge = entityManager.merge(kategoriProdukData);
			entityManager.flush();
			return dataConverter.convertKategoriProdukDataToKategoriProduk(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public KategoriProduk delete(KategoriProduk t) throws IOException {
		KategoriProdukData kategoriProdukData = entityManager.find(KategoriProdukData.class, t.getId());
		if(kategoriProdukData != null) {
			entityManager.remove(kategoriProdukData);	
			entityManager.flush();
			return dataConverter.convertKategoriProdukDataToKategoriProduk(kategoriProdukData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public List<KategoriProduk> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<KategoriProdukData> cq = cb.createQuery(KategoriProdukData.class);
		Root<KategoriProdukData> root = cq.from(KategoriProdukData.class);		
		
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
		
		TypedQuery<KategoriProdukData> q = null;		
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
				.map(d -> dataConverter.convertKategoriProdukDataToKategoriProduk(d))
				.collect(Collectors.toList());
	}
		
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<KategoriProdukData> root = cq.from(KategoriProdukData.class);		
		
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
