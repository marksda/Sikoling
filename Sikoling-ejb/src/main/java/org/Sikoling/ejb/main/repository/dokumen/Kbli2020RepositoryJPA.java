package org.Sikoling.ejb.main.repository.dokumen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli2020;
import org.Sikoling.ejb.abstraction.repository.IKbliRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class Kbli2020RepositoryJPA implements IKbliRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;

	public Kbli2020RepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		super();
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public List<Kbli2020> getAll() {
		return entityManager.createNamedQuery("Kbli2020Data.findAll", Kbli2020Data.class)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertKbli2022DataToKbli2020(d))
				.collect(Collectors.toList());
	}

	@Override
	public Kbli2020 save(Kbli2020 t) {
		Kbli2020Data kbliData = dataConverter.convertKbli2020ToKbli2020Data(t);
		entityManager.persist(kbliData);
		entityManager.flush();
		return dataConverter.convertKbli2022DataToKbli2020(kbliData);
	}

	@Override
	public Kbli2020 update(Kbli2020 t) {
		Kbli2020Data kbliData = dataConverter.convertKbli2020ToKbli2020Data(t);
		kbliData = entityManager.merge(kbliData);
		return dataConverter.convertKbli2022DataToKbli2020(kbliData);
	}

	@Override
	public DeleteResponse delete(String kode) {
		Kbli2020Data kbliData = entityManager.find(Kbli2020Data.class, kode);
		entityManager.remove(kbliData);
		return new DeleteResponse(true, kode);
	}

	@Override
	public Kbli2020 updateById(String id, Kbli2020 kbli) {
		Kbli2020Data updateData = dataConverter.convertKbli2020ToKbli2020Data(kbli);
		Kbli2020Data kbliData = entityManager.find(Kbli2020Data.class, id);
		kbliData.setId(updateData.getId());
		kbliData.setNama(updateData.getNama());
		kbliData.setKategori(updateData.getKategori());
		
		return dataConverter.convertKbli2022DataToKbli2020(kbliData);
	}

	
	@Override
	public List<Kbli2020> getDaftarKbli2020(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Kbli2020Data> cq = cb.createQuery(Kbli2020Data.class);
		Root<Kbli2020Data> root = cq.from(Kbli2020Data.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.getFilters().iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "kode":
				daftarPredicate.add(cb.like(cb.lower(root.get("id")), filter.getValue().toLowerCase()+"%"));
				break;
			case "nama":
				daftarPredicate.add(cb.like(cb.lower(root.get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "kategori":
				daftarPredicate.add(cb.equal(root.get("kategori"), filter.getValue()));
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
			case "kode":
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
			case "kategori":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("kategori")));
				}
				else {
					cq.orderBy(cb.desc(root.get("kategori")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<Kbli2020Data> q = null;		
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
				.map(d -> dataConverter.convertKbli2022DataToKbli2020(d))
				.collect(Collectors.toList());
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Kbli2020Data> root = cq.from(Kbli2020Data.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "kode":
				daftarPredicate.add(cb.like(cb.lower(root.get("id")), filter.getValue().toLowerCase()+"%"));
				break;
			case "nama":
				daftarPredicate.add(cb.like(cb.lower(root.get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "kategori":
				daftarPredicate.add(cb.equal(root.get("kategori"), filter.getValue()));
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
