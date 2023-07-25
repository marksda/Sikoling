package org.Sikoling.ejb.main.repository.dokumen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;
import org.Sikoling.ejb.abstraction.repository.IKategoriDokumenRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class KategoriDokumenRepositoryJPA implements IKategoriDokumenRepository {

	private final EntityManager entityManager;
	private final DataConverter dataConverter;
	
	public KategoriDokumenRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}	

	@Override
	public KategoriDokumen save(KategoriDokumen t) throws IOException {
		try {
			KategoriDokumenData kategoriDokumenPerusahaanData = dataConverter.convertKategoriDokumenToKategoriDokumenData(t);
			entityManager.persist(kategoriDokumenPerusahaanData);
			entityManager.flush();			
			return dataConverter.convertKategoriDokumenDataToKategoriDokumen(kategoriDokumenPerusahaanData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}		
	}

	@Override
	public KategoriDokumen update(KategoriDokumen t) {
		KategoriDokumenData kategoriDokumenPerusahaanData = dataConverter.convertKategoriDokumenToKategoriDokumenData(t);
		KategoriDokumenData dataTermerge = entityManager.merge(kategoriDokumenPerusahaanData);	
		entityManager.flush();
		return dataConverter.convertKategoriDokumenDataToKategoriDokumen(dataTermerge);
	}

	@Override
	public KategoriDokumen updateId(String idLama, KategoriDokumen t) throws IOException {
		Query query = entityManager.createNamedQuery("KategoriDokumenData.updateId");
		query.setParameter("idBaru", t.getId());
		query.setParameter("idLama", idLama);
		int updateCount = query.executeUpdate();		
		if(updateCount > 0) {
			KategoriDokumenData kategoriDokumenData = dataConverter.convertKategoriDokumenToKategoriDokumenData(t);
			KategoriDokumenData dataTermerge = entityManager.merge(kategoriDokumenData);
			entityManager.flush();
			return dataConverter.convertKategoriDokumenDataToKategoriDokumen(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public KategoriDokumen delete(KategoriDokumen t) throws IOException {
		KategoriDokumenData kategoriDokumenData = entityManager.find(KategoriDokumenData.class, t.getId());
		if(kategoriDokumenData != null) {
			entityManager.remove(kategoriDokumenData);	
			entityManager.flush();
			return dataConverter.convertKategoriDokumenDataToKategoriDokumen(kategoriDokumenData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}	
	
	@Override
	public List<KategoriDokumen> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<KategoriDokumenData> cq = cb.createQuery(KategoriDokumenData.class);
		Root<KategoriDokumenData> root = cq.from(KategoriDokumenData.class);		
		
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
			case "parent":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("parent")));
				}
				else {
					cq.orderBy(cb.desc(root.get("parent")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<KategoriDokumenData> q = null;		
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
				.map(d -> dataConverter.convertKategoriDokumenDataToKategoriDokumen(d))
				.collect(Collectors.toList());
	}
	
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<KategoriDokumenData> root = cq.from(KategoriDokumenData.class);		
		
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
