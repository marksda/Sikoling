package org.Sikoling.ejb.main.repository.jabatan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IJabatanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class JabatanRepositoryJPA implements IJabatanRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;

	public JabatanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public Jabatan save(Jabatan t) {
		JabatanData jabatanData = dataConverter.convertJabatanToJabatanData(t);
		entityManager.persist(jabatanData);
		entityManager.flush();
		return dataConverter.convertJabatanDataToJabatan(jabatanData);
	}

	@Override
	public Jabatan update(Jabatan t) {
		JabatanData jabatanData = dataConverter.convertJabatanToJabatanData(t);
		jabatanData = entityManager.merge(jabatanData);
		return dataConverter.convertJabatanDataToJabatan(jabatanData);
	}

	@Override
	public List<Jabatan> getAll() {
		return entityManager.createNamedQuery("JabatanData.findAll", JabatanData.class)
				.getResultList()
				.stream()
				.map(t -> dataConverter.convertJabatanDataToJabatan(t))
				.collect(Collectors.toList());
	}

	
	@Override
	public Jabatan updateById(String id, Jabatan jabatan) {
		String idBaru = jabatan.getId();
		JabatanData jabatanData = dataConverter.convertJabatanToJabatanData(jabatan);
		jabatanData.setId(id);
		jabatanData = entityManager.merge(jabatanData);
		if(!idBaru.equals(id)) {
			jabatanData.setId(idBaru);
			entityManager.flush();
		}
		return dataConverter.convertJabatanDataToJabatan(jabatanData);
	}
	

	@Override
	public DeleteResponse delete(String id) {
		JabatanData jabatanData = entityManager.find(JabatanData.class, id);
		entityManager.remove(jabatanData);	
		return new DeleteResponse(true, id);
	}
	

	@Override
	public List<Jabatan> getDaftarJabatan(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<JabatanData> cq = cb.createQuery(JabatanData.class);
		Root<JabatanData> root = cq.from(JabatanData.class);		
		
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
		
		TypedQuery<JabatanData> q = null;		
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
				.map(d -> dataConverter.convertJabatanDataToJabatan(d))
				.collect(Collectors.toList());
	}
	

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<JabatanData> root = cq.from(JabatanData.class);		
		
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
