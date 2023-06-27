package org.Sikoling.ejb.main.repository.otoritas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.OtoritasPerusahaan;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IOtoritasPerusahaanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class OtoritasPerusahaanRepositoryJPA implements IOtoritasPerusahaanRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;

	public OtoritasPerusahaanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public OtoritasPerusahaan save(OtoritasPerusahaan t) throws IOException {
		try {
			OtoritasPerusahaanData autorityPerusahaanData = dataConverter.convertAutorityPerusahaanToAutorityPerusahaanData(t);
			entityManager.persist(autorityPerusahaanData);
			entityManager.flush();		
			return dataConverter.convertAutorityPerusahaanDataToAutorityPerusahaan(autorityPerusahaanData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}
		
	}

	@Override
	public OtoritasPerusahaan update(OtoritasPerusahaan t) {
		OtoritasPerusahaanData autorityPerusahaanData = dataConverter.convertAutorityPerusahaanToAutorityPerusahaanData(t);
		OtoritasPerusahaanData dataTermerge = entityManager.merge(autorityPerusahaanData);		
		entityManager.flush();
		return dataConverter.convertAutorityPerusahaanDataToAutorityPerusahaan(dataTermerge);
	}

	@Override
	public OtoritasPerusahaan updateId(String idLamaAutority, String idLamaRegisterPerusahaan, OtoritasPerusahaan t) throws IOException {
		OtoritasPerusahaanDataId idLama = new OtoritasPerusahaanDataId();
		idLama.setAutority(idLamaAutority);
		idLama.setPerusahaan(idLamaRegisterPerusahaan);
		
		OtoritasPerusahaanData dataLama = entityManager.find(OtoritasPerusahaanData.class, idLama);
		
		if(dataLama != null) {
			OtoritasPerusahaanData autorityPerusahaanData = dataConverter.convertAutorityPerusahaanToAutorityPerusahaanData(t);
			entityManager.remove(dataLama);
			OtoritasPerusahaanData dataTermerge = entityManager.merge(autorityPerusahaanData);
			entityManager.flush();
			return dataConverter.convertAutorityPerusahaanDataToAutorityPerusahaan(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public OtoritasPerusahaan delete(OtoritasPerusahaan t) throws IOException {
		OtoritasPerusahaanDataId id = new OtoritasPerusahaanDataId();
		id.setAutority(t.getOtoritas().getId());
		id.setPerusahaan(t.getRegisterPerusahaan().getId());
		
		OtoritasPerusahaanData autorityPerusahaanData = entityManager.find(OtoritasPerusahaanData.class, id);
		
		if(autorityPerusahaanData != null) {
			entityManager.remove(autorityPerusahaanData);	
			entityManager.flush();
			return dataConverter.convertAutorityPerusahaanDataToAutorityPerusahaan(autorityPerusahaanData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}		
	}

	@Override
	public List<OtoritasPerusahaan> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<OtoritasPerusahaanData> cq = cb.createQuery(OtoritasPerusahaanData.class);
		Root<OtoritasPerusahaanData> root = cq.from(OtoritasPerusahaanData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.getFilters().iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "user_name":
				daftarPredicate.add(cb.like(cb.lower(root.get("autority").get("userName")), filter.getValue().toLowerCase()+"%"));
				break;
			case "perusahaan":
				daftarPredicate.add(cb.like(cb.lower(root.get("perusahaan").get("nama")), filter.getValue().toLowerCase()+"%"));
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
			case "user_name":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("autority").get("userName")));
				}
				else {
					cq.orderBy(cb.desc(root.get("autority").get("userName")));
				}
				break;
			case "perusahaan":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("perusahaan").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("perusahaan").get("nama")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<OtoritasPerusahaanData> q = null;		
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
				.map(d -> dataConverter.convertAutorityPerusahaanDataToAutorityPerusahaan(d))
				.collect(Collectors.toList());
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<OtoritasPerusahaanData> root = cq.from(OtoritasPerusahaanData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "user_name":
				daftarPredicate.add(cb.like(cb.lower(root.get("autority").get("userName")), filter.getValue().toLowerCase()+"%"));
				break;
			case "perusahaan":
				daftarPredicate.add(cb.like(cb.lower(root.get("perusahaan").get("nama")), filter.getValue().toLowerCase()+"%"));
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
