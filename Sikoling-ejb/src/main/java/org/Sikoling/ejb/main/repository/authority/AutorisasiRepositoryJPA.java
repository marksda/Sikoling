package org.Sikoling.ejb.main.repository.authority;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IAuthorityRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class AutorisasiRepositoryJPA implements IAuthorityRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;	

	public AutorisasiRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public List<Authority> getAll() {
		return entityManager.createNamedQuery("AutorisasiData.findAll", AutorisasiData.class)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertAutorisasiDataToAuthority(d))
				.collect(Collectors.toList());
	}

	@Override
	public Authority save(Authority t) {
		AutorisasiData autorisasiData = dataConverter.convertAuthorityToAutorisasiData(t);
		entityManager.persist(autorisasiData);
		entityManager.flush();
		return dataConverter.convertAutorisasiDataToAuthority(autorisasiData);
	}

	@Override
	public Authority update(Authority t) {
		AutorisasiData autorisasiData = dataConverter.convertAuthorityToAutorisasiData(t);
		autorisasiData = entityManager.merge(autorisasiData);
		return dataConverter.convertAutorisasiDataToAuthority(autorisasiData);
	}
	
	@Override
	public DeleteResponse delete(String id) {
		AutorisasiData autorisasiData = entityManager.find(AutorisasiData.class, id);
		entityManager.remove(autorisasiData);
		return new DeleteResponse(true, id);
	}
	
	@Override
	public Authority getByUserName(String userName) {
		AutorisasiData data = Optional.ofNullable(
				entityManager.createNamedQuery("AutorisasiData.findByUserName", AutorisasiData.class)
				.setParameter("userName", userName).getSingleResult()
				)
				.orElse(null);
		return data != null ? dataConverter.convertAutorisasiDataToAuthority(data):null;				
	}
	
	@Override
	public List<Authority> getDaftarAuthority(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AutorisasiData> cq = cb.createQuery(AutorisasiData.class);
		Root<AutorisasiData> root = cq.from(AutorisasiData.class);
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.getFilters().iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "tanggal":
				daftarPredicate.add(cb.equal(root.get("tanggalRegistrasi"), filter.getValue()));
				break;
			case "user_name":
				daftarPredicate.add(cb.like(cb.lower(root.get("userName")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "nama":
				daftarPredicate.add(cb.like(cb.lower(root.get("person").get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "nik":
				daftarPredicate.add(cb.equal(root.get("person").get("id"), filter.getValue()));
				break;
			case "hak_akses":
				daftarPredicate.add(cb.equal(root.get("hakAkses").get("id"), filter.getValue()));
				break;
			case "status_internal":
				daftarPredicate.add(cb.equal(root.get("statusInternal"), filter.getValue()));
				break;
			case "is_verified":
				daftarPredicate.add(cb.equal(root.get("isVerified"), filter.getValue()));
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
			case "tanggal":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("id")));
				}
				else {
					cq.orderBy(cb.desc(root.get("id")));
				}
				break;
			case "user_name":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("userName")));
				}
				else {
					cq.orderBy(cb.desc(root.get("userName")));
				}
				break;
			case "nama":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("person").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("person").get("nama")));
				}
				break;
			case "hak_akses":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("hakAkses").get("id")));
				}
				else {
					cq.orderBy(cb.desc(root.get("hakAkses").get("id")));
				}
				break;	
			case "status_internal":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("statusInternal")));
				}
				else {
					cq.orderBy(cb.desc(root.get("statusInternal")));
				}
				break;
			case "is_verified":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("isVerified")));
				}
				else {
					cq.orderBy(cb.desc(root.get("isVerified")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<AutorisasiData> q = null;		
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
				.map(d -> dataConverter.convertAutorisasiDataToAuthority(d))
				.collect(Collectors.toList());
	}
	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<AutorisasiData> root = cq.from(AutorisasiData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "tanggal":
				daftarPredicate.add(cb.equal(root.get("tanggalRegistrasi"), filter.getValue()));
				break;
			case "user_name":
				daftarPredicate.add(cb.like(cb.lower(root.get("userName")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "person":
				daftarPredicate.add(cb.like(cb.lower(root.get("person").get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "hak_akses":
				daftarPredicate.add(cb.equal(root.get("hakAkses").get("id"), filter.getValue()));
				break;
			case "status_internal":
				daftarPredicate.add(cb.equal(root.get("statusInternal"), filter.getValue()));
				break;
			case "is_verified":
				daftarPredicate.add(cb.equal(root.get("isVerified"), filter.getValue()));
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
