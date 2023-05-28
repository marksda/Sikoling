package org.Sikoling.ejb.main.repository.pelakuusaha;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.repository.IPelakuUsahaRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class PelakuUsahaRepositoryJPA implements IPelakuUsahaRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;
	
	public PelakuUsahaRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		super();
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public List<PelakuUsaha> getAll() {
		return entityManager.createNamedQuery("PelakuUsahaData.findAll", PelakuUsahaData.class)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertPelakuUsahaDataToPelakuUsaha(d))
				.collect(Collectors.toList());
	}

	@Override
	public PelakuUsaha save(PelakuUsaha t) {
		PelakuUsahaData pelakuUsahaData = dataConverter.convertPelakuUsahaToPelakuUsahaData(t);
		entityManager.persist(pelakuUsahaData);
		entityManager.flush();
		
		return dataConverter.convertPelakuUsahaDataToPelakuUsaha(pelakuUsahaData);
	}
	
	@Override
	public DeleteResponse delete(String id) {
		PelakuUsahaData pelakuUsahaData = entityManager.find(PelakuUsahaData.class, id);
		entityManager.remove(pelakuUsahaData);
		return new DeleteResponse(true, id);
	}
		
	@Override
	public PelakuUsaha update(PelakuUsaha t) {
		PelakuUsahaData detailPelakuUsahaData = dataConverter.convertPelakuUsahaToPelakuUsahaData(t);
		detailPelakuUsahaData = entityManager.merge(detailPelakuUsahaData);
		return dataConverter.convertPelakuUsahaDataToPelakuUsaha(detailPelakuUsahaData);
	}

	@Override
	public PelakuUsaha updateById(String id, PelakuUsaha pelakuUsaha) {
		String idBaru = pelakuUsaha.getId();
		PelakuUsahaData pelakuUsahaData = dataConverter.convertPelakuUsahaToPelakuUsahaData(pelakuUsaha);
		pelakuUsahaData.setId(id);
		pelakuUsahaData = entityManager.merge(pelakuUsahaData);
		
		if(!idBaru.equals(id)) {
			pelakuUsahaData.setId(idBaru);
		}
		
		return dataConverter.convertPelakuUsahaDataToPelakuUsaha(pelakuUsahaData);
	}

	
	@Override
	public List<PelakuUsaha> getDaftarPelakuUsaha(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PelakuUsahaData> cq = cb.createQuery(PelakuUsahaData.class);
		Root<PelakuUsahaData> root = cq.from(PelakuUsahaData.class);		
		
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
			case "singkatan":
				daftarPredicate.add(cb.like(cb.lower(root.get("singkatan")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "kategori_pelaku_usaha":
				daftarPredicate.add(cb.equal(root.get("kategoriPelakuUsaha").get("id"), filter.getValue()));
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
			case "singkatan":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("singkatan")));
				}
				else {
					cq.orderBy(cb.desc(root.get("singkatan")));
				}
				break;
			case "kategori_pelaku_usaha":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("kategoriPelakuUsaha").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("kategoriPelakuUsaha").get("nama")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<PelakuUsahaData> q = null;		
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
				.map(d -> dataConverter.convertPelakuUsahaDataToPelakuUsaha(d))
				.collect(Collectors.toList());
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<PelakuUsahaData> root = cq.from(PelakuUsahaData.class);		
		
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
			case "kategori_pelaku_usaha":
				daftarPredicate.add(cb.equal(root.get("kategoriPelakuUsaha").get("id"), filter.getValue()));
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
