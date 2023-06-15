package org.Sikoling.ejb.main.repository.perusahaan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.AutorityPerusahaan;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IAutorityPerusahaanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class AutorityPerusahaanRepositoryJPA implements IAutorityPerusahaanRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;

	public AutorityPerusahaanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public List<AutorityPerusahaan> getAll() {
		return entityManager.createNamedQuery("AutorityPerusahaanData.findAll", AutorityPerusahaanData.class)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertAutorityPerusahaanDataToAutorityPerusahaan(d))
				.collect(Collectors.toList());
	}

	@Override
	public AutorityPerusahaan save(AutorityPerusahaan t) {
		AutorityPerusahaanData autorityPerusahaanData = dataConverter.convertAutorityPerusahaanToAutorityPerusahaanData(t);
		entityManager.persist(autorityPerusahaanData);
		entityManager.flush();		
		return dataConverter.convertAutorityPerusahaanDataToAutorityPerusahaan(autorityPerusahaanData);
	}

	@Override
	public AutorityPerusahaan update(AutorityPerusahaan t) {
		AutorityPerusahaanData autorityPerusahaanData = dataConverter.convertAutorityPerusahaanToAutorityPerusahaanData(t);
		autorityPerusahaanData = entityManager.merge(autorityPerusahaanData);		
		return dataConverter.convertAutorityPerusahaanDataToAutorityPerusahaan(autorityPerusahaanData);
	}

	@Override
	public AutorityPerusahaan updateById(String idLamaAutority, String idLamaRegisterPerusahaan, AutorityPerusahaan dataBaru) {
		
		AutorityPerusahaanDataId idLama = new AutorityPerusahaanDataId();
		idLama.setAutority(idLamaAutority);
		idLama.setPerusahaan(idLamaAutority);
		
		AutorityPerusahaanData data = entityManager.find(AutorityPerusahaanData.class, idLama);
		
		AutorisasiData autorisasiData = dataConverter.convertAuthorityToAutorisasiData(dataBaru.getAuthority());
		data.setAutority(autorisasiData);
		RegisterPerusahaanData registerPerusahaanData = dataConverter.convertRegisterPerusahaanToRegisterPerusahaanData(dataBaru.getRegisterPerusahaan());
		data.setPerusahaan(registerPerusahaanData);
		entityManager.flush();
		return dataConverter.convertAutorityPerusahaanDataToAutorityPerusahaan(data);
	}
	
	@Override
	public DeleteResponse delete(String idAutority, String idRegisterPerusahaan) {
		AutorityPerusahaanDataId id = new AutorityPerusahaanDataId();
		id.setAutority(idAutority);
		id.setPerusahaan(idRegisterPerusahaan);
		AutorityPerusahaanData autorityPerusahaanData = entityManager.find(AutorityPerusahaanData.class, id);
		entityManager.remove(autorityPerusahaanData);	
		return new DeleteResponse(true, id.toString());
	}

	@Override
	public List<AutorityPerusahaan> getDaftarAutorityPerusahaan(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AutorityPerusahaanData> cq = cb.createQuery(AutorityPerusahaanData.class);
		Root<AutorityPerusahaanData> root = cq.from(AutorityPerusahaanData.class);		
		
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
		
		TypedQuery<AutorityPerusahaanData> q = null;		
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
	public Long getCount(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<AutorityPerusahaanData> root = cq.from(AutorityPerusahaanData.class);		
		
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
