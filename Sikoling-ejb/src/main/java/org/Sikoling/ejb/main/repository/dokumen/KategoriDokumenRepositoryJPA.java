package org.Sikoling.ejb.main.repository.dokumen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;
import org.Sikoling.ejb.abstraction.repository.IKategoriDokumenRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
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
	public List<KategoriDokumen> getAll() {
			return entityManager.createNamedQuery("KategoriDokumenPerusahaanData.findAll", KategoriDokumenData.class)
					.getResultList()
					.stream()
					.map(d -> dataConverter.convertKategoriDokumenDataToKategoriDokumen(d))
					.collect(Collectors.toList());
	}

	@Override
	public KategoriDokumen save(KategoriDokumen t) {
		KategoriDokumenData kategoriDokumenPerusahaanData = dataConverter.convertKategoriDokumenToKategoriDokumenData(t);
		entityManager.persist(kategoriDokumenPerusahaanData);
		entityManager.flush();
		
		return dataConverter.convertKategoriDokumenDataToKategoriDokumen(kategoriDokumenPerusahaanData);
	}

	@Override
	public KategoriDokumen update(KategoriDokumen t) {
		KategoriDokumenData kategoriDokumenPerusahaanData = dataConverter.convertKategoriDokumenToKategoriDokumenData(t);
		kategoriDokumenPerusahaanData = entityManager.merge(kategoriDokumenPerusahaanData);		
		return dataConverter.convertKategoriDokumenDataToKategoriDokumen(kategoriDokumenPerusahaanData);
	}

	@Override
	public DeleteResponse delete(String Id) {
		KategoriDokumen kategoriDokumen = entityManager.find(KategoriDokumen.class, Id);
		entityManager.remove(kategoriDokumen);		
		return new DeleteResponse(true, Id);
	}
	
	@Override
	public KategoriDokumen updateById(String id, KategoriDokumen kategoriDokumen) {
		KategoriDokumenData updateData = dataConverter.convertKategoriDokumenToKategoriDokumenData(kategoriDokumen);
		KategoriDokumenData kategoriDokumenData = entityManager.find(KategoriDokumenData.class, id);
		kategoriDokumenData.setId(updateData.getId());
		kategoriDokumenData.setNama(updateData.getNama());
		kategoriDokumenData.setParent(updateData.getNama());
		kategoriDokumenData = entityManager.merge(kategoriDokumenData);
		return dataConverter.convertKategoriDokumenDataToKategoriDokumen(kategoriDokumenData);
	}
	
	@Override
	public List<KategoriDokumen> getDaftarKategoriDokumen(QueryParamFilters queryParamFilters) {
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
	public Long getCount(List<Filter> queryParamFilters) {
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
