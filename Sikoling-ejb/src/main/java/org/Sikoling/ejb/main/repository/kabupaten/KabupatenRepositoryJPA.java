package org.Sikoling.ejb.main.repository.kabupaten;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IKabupatenRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class KabupatenRepositoryJPA implements IKabupatenRepository {	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;

	public KabupatenRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public Kabupaten save(Kabupaten t) throws IOException {
		try {
			KabupatenData kabupatenData = dataConverter.convertKabupatenToKabupatenData(t);
			entityManager.persist(kabupatenData);
			entityManager.flush();		
			return dataConverter.convertKabupatenDataToKabupaten(kabupatenData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}
		
	}

	@Override
	public Kabupaten update(Kabupaten t) {
		KabupatenData kabupatenData = dataConverter.convertKabupatenToKabupatenData(t);
		KabupatenData dataTermerge = entityManager.merge(kabupatenData);
		entityManager.flush();
		return dataConverter.convertKabupatenDataToKabupaten(dataTermerge);
	}
	
	@Override
	public Kabupaten updateId(String idLama, Kabupaten t) throws IOException {
		Query query = entityManager.createNamedQuery("KabupatenData.updateId");
		query.setParameter("idBaru", t.getId());
		query.setParameter("idLama", idLama);
		int updateCount = query.executeUpdate();
		if(updateCount > 0) {
			KabupatenData dataLama = entityManager.find(KabupatenData.class, t.getId());
			KabupatenData kabupatenData = dataConverter.convertKabupatenToKabupatenData(t);
			dataLama.setNama(kabupatenData.getNama());
			dataLama.setPropinsi(kabupatenData.getPropinsi());
			entityManager.flush();
			return dataConverter.convertKabupatenDataToKabupaten(dataLama);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public Kabupaten delete(Kabupaten t) throws IOException {
		KabupatenData kabupatenData = entityManager.find(KabupatenData.class, t.getId());
		if(kabupatenData != null) {
			entityManager.remove(kabupatenData);	
			entityManager.flush();
			return dataConverter.convertKabupatenDataToKabupaten(kabupatenData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public List<Kabupaten> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<KabupatenData> cq = cb.createQuery(KabupatenData.class);
		Root<KabupatenData> root = cq.from(KabupatenData.class);		
		
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
			case "propinsi":
				daftarPredicate.add(cb.equal(root.get("propinsi").get("id"), filter.getValue()));
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
			case "propinsi":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("propinsi").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("propinsi").get("nama")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<KabupatenData> q = null;		
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
				.map(d -> dataConverter.convertKabupatenDataToKabupaten(d))
				.collect(Collectors.toList());
	}
		
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<KabupatenData> root = cq.from(KabupatenData.class);		
		
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
			case "propinsi":
				daftarPredicate.add(cb.equal(root.get("propinsi").get("id"), filter.getValue()));
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
