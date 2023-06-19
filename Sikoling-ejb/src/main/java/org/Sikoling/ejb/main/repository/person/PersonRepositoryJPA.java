package org.Sikoling.ejb.main.repository.person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IPersonRepository;
import org.Sikoling.ejb.main.repository.DataConverter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class PersonRepositoryJPA implements IPersonRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;

	public PersonRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public Person save(Person t) throws IOException {
		try {
			PersonData personData = dataConverter.convertPersonToPersonData(t);
			entityManager.persist(personData);
			entityManager.flush();		
			return dataConverter.convertPersonDataToPerson(personData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}		
	}

	@Override
	public Person update(Person t) {
		PersonData personData = dataConverter.convertPersonToPersonData(t);
		PersonData dataTermerge = entityManager.merge(personData);	
		entityManager.flush();
		return dataConverter.convertPersonDataToPerson(dataTermerge);
	}

	@Override
	public Person updateId(String idLama, Person t) throws IOException {
		PersonData dataLama = entityManager.find(PersonData.class, idLama);
		if(dataLama != null) {
			PersonData personData = dataConverter.convertPersonToPersonData(t);
			entityManager.remove(dataLama);	
			PersonData dataTermerge = entityManager.merge(personData);
			entityManager.flush();
			return dataConverter.convertPersonDataToPerson(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public Person delete(Person t) throws IOException {
		PersonData personData = entityManager.find(PersonData.class, t.getNik());
		if(personData != null) {
			entityManager.remove(personData);	
			entityManager.flush();
			return dataConverter.convertPersonDataToPerson(personData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public List<Person> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PersonData> cq = cb.createQuery(PersonData.class);
		Root<PersonData> root = cq.from(PersonData.class);
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.getFilters().iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
				
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "nik":
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
			case "nik":
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
				
		TypedQuery<PersonData> q = null;		
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
				.map(d -> dataConverter.convertPersonDataToPerson(d))
				.collect(Collectors.toList());
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<PersonData> root = cq.from(PersonData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "nik":
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
