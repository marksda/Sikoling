package org.Sikoling.ejb.main.repository.desa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IDesaRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class DesaRepositoryJPA implements IDesaRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;	

	public DesaRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public Desa save(Desa t) throws IOException {
		try {
			DesaData desaData = dataConverter.convertDesaToDesaData(t);
			entityManager.persist(desaData);
			entityManager.flush();		
			return dataConverter.convertDesaDataToDesa(desaData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}
		
	}
	
	@Override
	public Desa update(Desa t) {
		DesaData desaData = dataConverter.convertDesaToDesaData(t);
		DesaData dataTermerge = entityManager.merge(desaData);	
		entityManager.flush();
		return dataConverter.convertDesaDataToDesa(dataTermerge);
	}
	
	@Override
	public Desa updateId(String idLama, Desa t) throws IOException {
		Query query = entityManager.createNamedQuery("DesaData.updateId");
		query.setParameter("idBaru", t.getId());
		query.setParameter("idLama", idLama);
		int updateCount = query.executeUpdate();
		if(updateCount >0 ) {
			DesaData dataLama = entityManager.find(DesaData.class, t.getId());
			DesaData desaData = dataConverter.convertDesaToDesaData(t);
			dataLama.setNama(desaData.getNama());
			dataLama.setKecamatan(desaData.getKecamatan());
			entityManager.flush();
			return dataConverter.convertDesaDataToDesa(dataLama);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public Desa delete(Desa t) throws IOException {
		DesaData desaData = entityManager.find(DesaData.class, t.getId());
		if(desaData != null) {
			entityManager.remove(desaData);	
			entityManager.flush();
			return dataConverter.convertDesaDataToDesa(desaData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public List<Desa> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<DesaData> cq = cb.createQuery(DesaData.class);
		Root<DesaData> root = cq.from(DesaData.class);		
		
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
			case "kecamatan":
				daftarPredicate.add(cb.equal(root.get("kecamatan").get("id"), filter.getValue()));
				break;
			case "kabupaten":
				daftarPredicate.add(cb.equal(root.get("kecamatan").get("kabupaten").get("id"), filter.getValue()));
				break;
			case "propinsi":
				daftarPredicate.add(cb.equal(root.get("kecamatan").get("kabupaten").get("propinsi").get("id"), filter.getValue()));
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
			case "kecamatan":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("kecamatan").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("kecamatan").get("nama")));
				}
				break;
			case "kabupaten":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("kecamatan").get("kabupaten").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("kecamatan").get("kabupaten").get("nama")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<DesaData> q = null;		
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
				.map(d -> dataConverter.convertDesaDataToDesa(d))
				.collect(Collectors.toList());
	}
	
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<DesaData> root = cq.from(DesaData.class);		
		
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
			case "kecamatan":
				daftarPredicate.add(cb.equal(root.get("kecamatan").get("id"), filter.getValue()));
				break;
			case "kabupaten":
				daftarPredicate.add(cb.equal(root.get("kecamatan").get("kabupaten").get("id"), filter.getValue()));
				break;
			case "propinsi":
				daftarPredicate.add(cb.equal(root.get("kecamatan").get("kabupaten").get("propinsi").get("id"), filter.getValue()));
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

	
