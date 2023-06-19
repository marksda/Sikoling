package org.Sikoling.ejb.main.repository.permohonan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonanSuratArahan;
import org.Sikoling.ejb.abstraction.repository.IKategoriPermohonanSuratArahahanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class KategoriPermohonanSuratArahanRepositoryJPA implements IKategoriPermohonanSuratArahahanRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;

	public KategoriPermohonanSuratArahanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public KategoriPermohonanSuratArahan save(KategoriPermohonanSuratArahan t) {
		KategoriPermohonanSuratArahanData kategoriPermohonanSuratArahanData = dataConverter.convertKategoriPermohonanSuratArahanToKategoriPermohonanSuratArahanData(t);
		entityManager.persist(kategoriPermohonanSuratArahanData);
		entityManager.flush();
		
		return dataConverter.convertKategoriPermohonanSuratArahanDataToKategoriPermohonanSuratArahan(kategoriPermohonanSuratArahanData);
	}

	@Override
	public KategoriPermohonanSuratArahan update(KategoriPermohonanSuratArahan t) {
			KategoriPermohonanSuratArahanData kategoriPermohonanSuratArahanData = dataConverter.convertKategoriPermohonanSuratArahanToKategoriPermohonanSuratArahanData(t);
			KategoriPermohonanSuratArahanData dataTermerge = entityManager.merge(kategoriPermohonanSuratArahanData);
			entityManager.flush();
			
			return dataConverter.convertKategoriPermohonanSuratArahanDataToKategoriPermohonanSuratArahan(dataTermerge);
	}

	@Override
	public KategoriPermohonanSuratArahan updateId(String idLama, KategoriPermohonanSuratArahan t) throws IOException {
		KategoriPermohonanSuratArahanData dataLama = entityManager.find(KategoriPermohonanSuratArahanData.class, idLama);
		if(dataLama != null) {
			KategoriPermohonanSuratArahanData skalaUsahaData = dataConverter.convertKategoriPermohonanSuratArahanToKategoriPermohonanSuratArahanData(t);
			entityManager.remove(dataLama);	
			KategoriPermohonanSuratArahanData dataTermerge = entityManager.merge(skalaUsahaData);
			entityManager.flush();
			return dataConverter.convertKategoriPermohonanSuratArahanDataToKategoriPermohonanSuratArahan(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public KategoriPermohonanSuratArahan delete(KategoriPermohonanSuratArahan t) throws IOException {
		KategoriPermohonanSuratArahanData skalaUsahaData = entityManager.find(KategoriPermohonanSuratArahanData.class, t.getId());
		if(skalaUsahaData != null) {
			entityManager.remove(skalaUsahaData);	
			entityManager.flush();
			return dataConverter.convertKategoriPermohonanSuratArahanDataToKategoriPermohonanSuratArahan(skalaUsahaData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public List<KategoriPermohonanSuratArahan> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<KategoriPermohonanSuratArahanData> cq = cb.createQuery(KategoriPermohonanSuratArahanData.class);
		Root<KategoriPermohonanSuratArahanData> root = cq.from(KategoriPermohonanSuratArahanData.class);		
		
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
		
		TypedQuery<KategoriPermohonanSuratArahanData> q = null;		
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
				.map(d -> dataConverter.convertKategoriPermohonanSuratArahanDataToKategoriPermohonanSuratArahan(d))
				.collect(Collectors.toList());
	}
		
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<KategoriPermohonanSuratArahanData> root = cq.from(KategoriPermohonanSuratArahanData.class);		
		
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
