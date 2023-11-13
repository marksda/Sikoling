package org.Sikoling.ejb.main.repository.otoritas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IAutorityRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class OtoritasRepositoryJPA implements IAutorityRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;	

	public OtoritasRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public Otoritas save(Otoritas t) throws IOException {
		try {
			OtoritasData autorisasiData = dataConverter.convertAuthorityToAutorisasiData(t);
			entityManager.persist(autorisasiData);
			entityManager.flush();
			return dataConverter.convertAutorisasiDataToAutority(autorisasiData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}		
	}

	@Override
	public Otoritas update(Otoritas t) {
		OtoritasData autorisasiData = dataConverter.convertAuthorityToAutorisasiData(t);
		autorisasiData = entityManager.merge(autorisasiData);
		entityManager.flush();
		return dataConverter.convertAutorisasiDataToAutority(autorisasiData);
	}
	
	@Override
	public Otoritas updateId(String idLama, Otoritas t) throws IOException {
		OtoritasData dataLama = entityManager.find(OtoritasData.class, idLama);
		if(dataLama != null) {
			OtoritasData autorisasiData = dataConverter.convertAuthorityToAutorisasiData(t);
			entityManager.remove(dataLama);	
			OtoritasData dataTermerge = entityManager.merge(autorisasiData);
			entityManager.flush();
			return dataConverter.convertAutorisasiDataToAutority(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public Otoritas delete(Otoritas t) throws IOException {
		OtoritasData autorisasiData = entityManager.find(OtoritasData.class, t.getId());
		if(autorisasiData != null) {
			entityManager.remove(autorisasiData);
			entityManager.flush();
			return dataConverter.convertAutorisasiDataToAutority(autorisasiData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}		
	}
	
	@Override
	public Otoritas getByUserName(String userName) {
		OtoritasData data = Optional.ofNullable(
				entityManager.createNamedQuery("OtoritasData.findByUserName", OtoritasData.class)
				.setParameter("userName", userName).getSingleResult()
				)
				.orElse(null);
		return data != null ? dataConverter.convertAutorisasiDataToAutority(data):null;				
	}
	
	@Override
	public Otoritas getById(String idOtoritas) {
		OtoritasData autorisasiData = entityManager.find(OtoritasData.class, idOtoritas);
		return autorisasiData != null ? dataConverter.convertAutorisasiDataToAutority(autorisasiData):null;				
	}
	
	@Override
	public List<Otoritas> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<OtoritasData> cq = cb.createQuery(OtoritasData.class);
		Root<OtoritasData> root = cq.from(OtoritasData.class);
		
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
				daftarPredicate.add(cb.like(cb.lower(root.get("userName")), filter.getValue().toLowerCase()+"%"));
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
					cq.orderBy(cb.asc(root.get("tanggalRegistrasi")));
				}
				else {
					cq.orderBy(cb.desc(root.get("tanggalRegistrasi")));
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
		
		TypedQuery<OtoritasData> q = null;		
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
				.map(d -> dataConverter.convertAutorisasiDataToAutority(d))
				.collect(Collectors.toList());
	}
	
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<OtoritasData> root = cq.from(OtoritasData.class);		
		
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
				daftarPredicate.add(cb.like(cb.lower(root.get("userName")), filter.getValue().toLowerCase()+"%"));
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
