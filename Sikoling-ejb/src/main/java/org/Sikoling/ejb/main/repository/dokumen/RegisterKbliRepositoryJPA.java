package org.Sikoling.ejb.main.repository.dokumen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.entity.dokumen.RegisterKbli;
import org.Sikoling.ejb.abstraction.repository.IRegisterKbliRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class RegisterKbliRepositoryJPA implements IRegisterKbliRepository {
	
	private final EntityManager entityManager;	
	private final DataConverter dataConverter;	

	public RegisterKbliRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public RegisterKbli save(RegisterKbli t) throws IOException {
		try {
			RegisterKbliData registerKbliData = dataConverter.convertRegisterKbliToRegisterKbliData(t);
			entityManager.persist(registerKbliData);
			entityManager.flush();
			
			return dataConverter.convertRegisterKbliDataToRegisterKbli(registerKbliData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}
		
	}

	@Override
	public RegisterKbli update(RegisterKbli t) {
		RegisterKbliData registerKbliData = dataConverter.convertRegisterKbliToRegisterKbliData(t);
		RegisterKbliData dataTermerge = entityManager.merge(registerKbliData);
		entityManager.flush();
		return dataConverter.convertRegisterKbliDataToRegisterKbli(dataTermerge);
	}

	@Override
	public RegisterKbli updateId(String idNibLama, String idKbliLama, RegisterKbli t) throws IOException {
		RegisterKbliDataId id = new RegisterKbliDataId();
		id.setNib(idNibLama);
		id.setKbli(idKbliLama);
		
		RegisterKbliData dataLama = entityManager.find(RegisterKbliData.class, id);
		if(dataLama != null) {
			RegisterKbliData registerKbliData = dataConverter.convertRegisterKbliToRegisterKbliData(t);
			entityManager.remove(dataLama);	
			RegisterKbliData dataTermerge = entityManager.merge(registerKbliData);
			entityManager.flush();
			return dataConverter.convertRegisterKbliDataToRegisterKbli(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public RegisterKbli delete(RegisterKbli t) throws IOException {
		RegisterKbliDataId id = new RegisterKbliDataId();
		id.setNib(t.getDokumenNibOss().getNomor());
		id.setKbli(t.getKbli().getKode());
		RegisterKbliData registerKbliData = entityManager.find(RegisterKbliData.class, id);
		
		if(registerKbliData != null) {
			entityManager.remove(registerKbliData);
			entityManager.flush();
			return dataConverter.convertRegisterKbliDataToRegisterKbli(registerKbliData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public List<RegisterKbli> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RegisterKbliData> cq = cb.createQuery(RegisterKbliData.class);
		Root<RegisterKbliData> root = cq.from(RegisterKbliData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.getFilters().iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "nib":
				daftarPredicate.add(cb.equal(root.get("nib").get("nomor"), filter.getValue()));
				break;
			case "kbli":
				daftarPredicate.add(cb.equal(root.get("kbli").get("id"), filter.getValue()));
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
			case "nib":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("nib").get("nomor")));
				}
				else {
					cq.orderBy(cb.desc(root.get("nib").get("nomor")));
				}
				break;
			case "kbli":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("kbli").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("kbli").get("nama")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<RegisterKbliData> q = null;		
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
				.map(d -> dataConverter.convertRegisterKbliDataToRegisterKbli(d))
				.collect(Collectors.toList());
	}
		
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<RegisterKbliData> root = cq.from(RegisterKbliData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "nib":
				daftarPredicate.add(cb.equal(root.get("nib").get("nomor"), filter.getValue()));
				break;
			case "kbli":
				daftarPredicate.add(cb.equal(root.get("kbli").get("id"), filter.getValue()));
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
