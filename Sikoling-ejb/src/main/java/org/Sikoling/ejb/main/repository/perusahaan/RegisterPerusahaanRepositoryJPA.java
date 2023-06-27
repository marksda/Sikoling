package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.OtoritasPerusahaan;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IRegisterPerusahaanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import org.Sikoling.ejb.main.repository.otoritas.OtoritasPerusahaanData;
import org.Sikoling.ejb.main.repository.otoritas.OtoritasPerusahaanDataId;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class RegisterPerusahaanRepositoryJPA implements IRegisterPerusahaanRepository {
	
	private final EntityManager entityManager;	
	private final DataConverter dataConverter;

	public RegisterPerusahaanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public RegisterPerusahaan save(RegisterPerusahaan t) throws IOException {
		RegisterPerusahaanData registerPerusahaanData = dataConverter.convertRegisterPerusahaanToRegisterPerusahaanData(t);
		try {
			entityManager.persist(registerPerusahaanData);
			entityManager.flush();
			return dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(registerPerusahaanData);
		} catch (EntityExistsException e) {
			throw new IOException("data sudah ada");
		}		
	}

	@Override
	public RegisterPerusahaan update(RegisterPerusahaan t) {
		RegisterPerusahaanData registerPerusahaanData = dataConverter.convertRegisterPerusahaanToRegisterPerusahaanData(t);		
		RegisterPerusahaanData dataTermerge = entityManager.merge(registerPerusahaanData);
		entityManager.flush();
		return dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(dataTermerge);
	}
	
	@Override
	public RegisterPerusahaan delete(RegisterPerusahaan t) throws IOException {
		RegisterPerusahaanData registerPerusahaanData = entityManager.find(RegisterPerusahaanData.class, t.getId());
		if(registerPerusahaanData != null) {
			entityManager.remove(registerPerusahaanData);
			entityManager.flush();
			return dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(registerPerusahaanData);
		}			
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
//	@Override
//	public DeleteResponse delete(String id) {
//		RegisterPerusahaanData registerPerusahaanData = entityManager.find(RegisterPerusahaanData.class, id);
//		entityManager.remove(registerPerusahaanData);			
//		entityManager.flush();
//		return new DeleteResponse(true, id);
//	}
	
	@Override
	public RegisterPerusahaan addLinkKepemilanPerusahaan(OtoritasPerusahaan autorityPerusahaan) throws IOException {
		OtoritasPerusahaanData autorityPerusahaanData = dataConverter.convertAutorityPerusahaanToAutorityPerusahaanData(autorityPerusahaan);
		try {
			OtoritasPerusahaanData newAddData = entityManager.merge(autorityPerusahaanData);
			entityManager.flush();
			RegisterPerusahaanData registerPerusahaanDataTerupdate = entityManager.find(RegisterPerusahaanData.class, newAddData.getPerusahaan().getId()); 
			return dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(registerPerusahaanDataTerupdate);
		} catch (EntityExistsException e) {
			throw new IOException("data sudah ada");
		}
		
	}
	
	
	@Override
	public RegisterPerusahaan deleteLinkKepemilikanPerusahaan(OtoritasPerusahaan autorityPerusahaan) throws IOException {
		OtoritasPerusahaanDataId id = new OtoritasPerusahaanDataId();
		id.setAutority(autorityPerusahaan.getOtoritas().getId());
		id.setPerusahaan(autorityPerusahaan.getRegisterPerusahaan().getId());
		
		OtoritasPerusahaanData autorityPerusahaanData = entityManager.find(OtoritasPerusahaanData.class, id);
		
		if(autorityPerusahaanData != null) {
			entityManager.remove(autorityPerusahaanData);
			entityManager.flush();
			RegisterPerusahaanData registerPerusahaanDataTerupdate = entityManager.find(RegisterPerusahaanData.class, autorityPerusahaanData.getPerusahaan().getId()); 
			return dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(registerPerusahaanDataTerupdate);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	
//	@Override
//	public Boolean cekById(String id) {
//		RegisterPerusahaanData perusahaanData = entityManager.find(RegisterPerusahaanData.class, id);
//		if(perusahaanData == null) {
//			return false;
//		}
//		else {
//			return true;
//		}		 
//	}
	
	
	@Override
	public List<RegisterPerusahaan> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RegisterPerusahaanData> cq = cb.createQuery(RegisterPerusahaanData.class);
		Root<RegisterPerusahaanData> root = cq.from(RegisterPerusahaanData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.getFilters().iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "tanggal_registrasi":
				daftarPredicate.add(cb.equal(root.get("tanggalRegistrasi"), filter.getValue()));
				break;
			case "nama":
				daftarPredicate.add(cb.like(cb.lower(root.get("nama")), filter.getValue().toLowerCase()+"%"));
				break;
			case "npwp":
				daftarPredicate.add(cb.equal(root.get("npwp"), filter.getValue()));
				break;
			case "kreator":
				daftarPredicate.add(cb.equal(root.get("kreator").get("id"), filter.getValue()));
				break;			
			case "kepemilikan":
				daftarPredicate.add(cb.equal(root.get("daftarPersonPerusahaanData").get("autority").get("id"), filter.getValue()));
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
			case "tanggal_registrasi":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("tanggalRegistrasi")));
				}
				else {
					cq.orderBy(cb.desc(root.get("tanggalRegistrasi")));
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
			case "npwp":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("npwp")));
				}
				else {
					cq.orderBy(cb.desc(root.get("npwp")));
				}
				break;
			case "kreator":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("kreator").get("id")));
				}
				else {
					cq.orderBy(cb.desc(root.get("kreator").get("id")));
				}
				break;
			case "kepemilikan":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("daftarPersonPerusahaanData").get("autority").get("id")));
				}
				else {
					cq.orderBy(cb.desc(root.get("daftarPersonPerusahaanData").get("autority").get("id")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<RegisterPerusahaanData> q = null;		
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
				.map(d -> dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(d))
				.collect(Collectors.toList());
	}
		
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<RegisterPerusahaanData> root = cq.from(RegisterPerusahaanData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "tanggal_registrasi":
				daftarPredicate.add(cb.equal(root.get("tanggalRegistrasi"), filter.getValue()));
				break;
			case "nama":
				daftarPredicate.add(cb.like(cb.lower(root.get("nama")), filter.getValue().toLowerCase()+"%"));
				break;
			case "npwp":
				daftarPredicate.add(cb.equal(root.get("npwp"), filter.getValue()));
				break;
			case "kreator":
				daftarPredicate.add(cb.equal(root.get("kreator").get("id"), filter.getValue()));
				break;			
			case "kepemilikan":
				daftarPredicate.add(cb.equal(root.get("daftarPersonPerusahaanData").get("autority").get("id"), filter.getValue()));
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

	