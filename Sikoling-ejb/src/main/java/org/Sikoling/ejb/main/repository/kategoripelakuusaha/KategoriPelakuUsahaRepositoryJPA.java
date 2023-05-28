package org.Sikoling.ejb.main.repository.kategoripelakuusaha;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IKategoriPelakuUsahaRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class KategoriPelakuUsahaRepositoryJPA implements IKategoriPelakuUsahaRepository {
	
	private final EntityManager entityManager;	
	private final DataConverter dataConverter;	

	public KategoriPelakuUsahaRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public List<KategoriPelakuUsaha> getAll() {
		return entityManager.createNamedQuery("KategoriPelakuUsahaData.findAll", KategoriPelakuUsahaData.class)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertKategoriPelakuUsahaDataToKategoriPelakuUsaha(d))
				.collect(Collectors.toList());
	}
	
	@Override
	public KategoriPelakuUsaha save(KategoriPelakuUsaha t) {
		KategoriPelakuUsahaData kategoriPelakuUsahaData = dataConverter.convertKategoriPelakuUsahaToKategoriPelakuUsahaData(t);
		entityManager.persist(kategoriPelakuUsahaData);
		entityManager.flush();
		return dataConverter.convertKategoriPelakuUsahaDataToKategoriPelakuUsaha(kategoriPelakuUsahaData);
	}

	@Override
	public KategoriPelakuUsaha update(KategoriPelakuUsaha t) {
		KategoriPelakuUsahaData kategoriPelakuUsahaData = dataConverter.convertKategoriPelakuUsahaToKategoriPelakuUsahaData(t);
		kategoriPelakuUsahaData = entityManager.merge(kategoriPelakuUsahaData);
		return dataConverter.convertKategoriPelakuUsahaDataToKategoriPelakuUsaha(kategoriPelakuUsahaData);
	}

	@Override
	public DeleteResponse delete(String id) {
		KategoriPelakuUsahaData kategoriPelakuUsahaData = entityManager.find(KategoriPelakuUsahaData.class, id);
		entityManager.remove(kategoriPelakuUsahaData);
		
		return new DeleteResponse(true, id);
	}

	@Override
	public KategoriPelakuUsaha updateById(String id, KategoriPelakuUsaha kategoriPelakuUsaha) {
		String idBaru = kategoriPelakuUsaha.getId();
		KategoriPelakuUsahaData kategoriPelakuUsahaData = dataConverter.convertKategoriPelakuUsahaToKategoriPelakuUsahaData(kategoriPelakuUsaha);
		kategoriPelakuUsahaData.setId(id);
		kategoriPelakuUsahaData = entityManager.merge(kategoriPelakuUsahaData);
		
		if(!idBaru.equals(id)) {
			kategoriPelakuUsahaData.setId(idBaru);
			entityManager.flush();
		}
		
		return null;
	}
	
	@Override
	public List<KategoriPelakuUsaha> getDaftarKategoriPelakuUsaha(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<KategoriPelakuUsahaData> cq = cb.createQuery(KategoriPelakuUsahaData.class);
		Root<KategoriPelakuUsahaData> root = cq.from(KategoriPelakuUsahaData.class);		
		
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
			case "id_skala_usaha":
				daftarPredicate.add(cb.equal(root.get("skalaUsaha").get("id"), filter.getValue()));
				break;
			case "nama_skala_usaha":
				daftarPredicate.add(cb.like(cb.lower(root.get("skalaUsaha").get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
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
			case "nama_skala_usaha":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("skalaUsaha").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("skalaUsaha").get("nama")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<KategoriPelakuUsahaData> q = null;		
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
				.map(d -> dataConverter.convertKategoriPelakuUsahaDataToKategoriPelakuUsaha(d))
				.collect(Collectors.toList());
	}
	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<KategoriPelakuUsahaData> root = cq.from(KategoriPelakuUsahaData.class);		
		
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
			case "nama_skala_usaha":
				daftarPredicate.add(cb.like(cb.lower(root.get("skalaUsaha").get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
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
