package org.Sikoling.ejb.main.repository.perusahaan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IRegisterPerusahaanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
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
	public List<RegisterPerusahaan> getAll() {
		return entityManager.createNamedQuery("RegisterPerusahaanData.findAll", RegisterPerusahaanData.class)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(d))
				.collect(Collectors.toList());
	}

	@Override
	public RegisterPerusahaan save(RegisterPerusahaan t) {
		RegisterPerusahaanData registerPerusahaanData = dataConverter.convertRegisterPerusahaanToRegisterPerusahaanData(t);
		entityManager.persist(registerPerusahaanData);
		
		return dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(registerPerusahaanData);
	}
	
	@Override
	public DeleteResponse delete(String id) {
		RegisterPerusahaanData registerPerusahaanData = entityManager.find(RegisterPerusahaanData.class, id);
		entityManager.remove(registerPerusahaanData);			
		return new DeleteResponse(true, id);
	}
	
	@Override
	public DeleteResponse deleteLinkKepemilikanPerusahaan(String idAutority, String idRegisterPerusahaan) {
		AutorityPerusahaanDataId id = new AutorityPerusahaanDataId();
		id.setAutority(idAutority);
		id.setPerusahaan(idRegisterPerusahaan);
		
		AutorityPerusahaanData autorityPerusahaanData = entityManager.find(AutorityPerusahaanData.class, id);
		entityManager.remove(autorityPerusahaanData);			
		return new DeleteResponse(true, idRegisterPerusahaan);
	}
	
	@Override
	public RegisterPerusahaan update(RegisterPerusahaan t) {
		RegisterPerusahaanData registerPerusahaanData = dataConverter.convertRegisterPerusahaanToRegisterPerusahaanData(t);		
		registerPerusahaanData = entityManager.merge(registerPerusahaanData);
		return dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(registerPerusahaanData);
	}

	@Override
	public Boolean cekById(String id) {
		RegisterPerusahaanData perusahaanData = entityManager.find(RegisterPerusahaanData.class, id);
		if(perusahaanData == null) {
			return false;
		}
		else {
			return true;
		}		 
	}

	
	@Override
	public List<RegisterPerusahaan> getDaftarPerusahaan(QueryParamFilters queryParamFilters) {
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
			case "nama":
				daftarPredicate.add(cb.like(cb.lower(root.get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
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
	public Long getCount(List<Filter> queryParamFilters) {
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
			case "nama":
				daftarPredicate.add(cb.like(cb.lower(root.get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
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

	