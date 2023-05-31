package org.Sikoling.ejb.main.repository.perusahaan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Pegawai;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IPegawaiPerusahaanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import org.Sikoling.ejb.main.repository.person.PersonData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class PegawaiPerusahaanRepositoryJPA implements IPegawaiPerusahaanRepository {
	
	private final EntityManager entityManager;	
	private final DataConverter dataConverter;

	public PegawaiPerusahaanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public List<Pegawai> getAll() {
		return entityManager.createNamedQuery("PegawaiPerusahaanData.findAll", PegawaiPerusahaanData.class)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertPegawaiPerusahaanDataToPegawaiPerusahaan(d))
				.collect(Collectors.toList());
	}

	@Override
	public Pegawai save(Pegawai t) {
		PegawaiPerusahaanData pegawaiPerusahaanData = dataConverter.convertPegawaiPerusahaanToPegawaiPerusahaanData(t);
		PersonData personData = null;
		
		try {
			personData = entityManager.createNamedQuery("PersonData.findById", PersonData.class)
					.setParameter("nik", pegawaiPerusahaanData.getPersonData().getId())
					.getSingleResult();
		} catch (NoResultException e) {
			personData = pegawaiPerusahaanData.getPersonData();
			entityManager.persist(personData);
			entityManager.flush();
		}
						
		entityManager.persist(pegawaiPerusahaanData);
		entityManager.flush();
		
		return dataConverter.convertPegawaiPerusahaanDataToPegawaiPerusahaan(pegawaiPerusahaanData);
	}

	@Override
	public Pegawai update(Pegawai t) {
		PegawaiPerusahaanData pegawaiPerusahaanData = dataConverter.convertPegawaiPerusahaanToPegawaiPerusahaanData(t);
		pegawaiPerusahaanData = entityManager.merge(pegawaiPerusahaanData);
		return dataConverter.convertPegawaiPerusahaanDataToPegawaiPerusahaan(pegawaiPerusahaanData);
	}

	
	@Override
	public Pegawai updateById(String id, Pegawai pegawai) {
		String idBaru = pegawai.getId();
		PegawaiPerusahaanData pegawaiPerusahaanData = dataConverter.convertPegawaiPerusahaanToPegawaiPerusahaanData(pegawai);
		pegawaiPerusahaanData.setId(id);
		pegawaiPerusahaanData = entityManager.merge(pegawaiPerusahaanData);
		if(!idBaru.equals(id)) {
			pegawaiPerusahaanData.setId(idBaru);
			entityManager.flush();
		}
		return dataConverter.convertPegawaiPerusahaanDataToPegawaiPerusahaan(pegawaiPerusahaanData);
	}

	
	@Override
	public DeleteResponse delete(String id) {
		PegawaiPerusahaanData pegawaiPerusahaanData = entityManager.find(PegawaiPerusahaanData.class, id);
		entityManager.remove(pegawaiPerusahaanData);	
		return new DeleteResponse(true, id);
	}

	
	@Override
	public List<Pegawai> getDaftarPegawai(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PegawaiPerusahaanData> cq = cb.createQuery(PegawaiPerusahaanData.class);
		Root<PegawaiPerusahaanData> root = cq.from(PegawaiPerusahaanData.class);		
		
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
				daftarPredicate.add(cb.like(cb.lower(root.get("personData").get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "perusahaan":
				daftarPredicate.add(cb.like(cb.lower(root.get("registerPerusahaanData").get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
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
					cq.orderBy(cb.asc(root.get("personData").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("personData").get("nama")));
				}
				break;
			case "perusahaan":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("registerPerusahaanData").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("registerPerusahaanData").get("nama")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<PegawaiPerusahaanData> q = null;		
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
				.map(d -> dataConverter.convertPegawaiPerusahaanDataToPegawaiPerusahaan(d))
				.collect(Collectors.toList());
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<PegawaiPerusahaanData> root = cq.from(PegawaiPerusahaanData.class);		
		
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
				daftarPredicate.add(cb.like(cb.lower(root.get("personData").get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "perusahaan":
				daftarPredicate.add(cb.like(cb.lower(root.get("registerPerusahaanData").get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
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
