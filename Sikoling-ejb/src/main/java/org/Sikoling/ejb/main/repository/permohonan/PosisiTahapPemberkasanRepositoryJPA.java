package org.Sikoling.ejb.main.repository.permohonan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;
import org.Sikoling.ejb.abstraction.repository.IPosisiTahapPemberkasanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class PosisiTahapPemberkasanRepositoryJPA implements IPosisiTahapPemberkasanRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;

	public PosisiTahapPemberkasanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public PosisiTahapPemberkasan save(PosisiTahapPemberkasan t) throws IOException {
		try {
			PosisiTahapPemberkasanData posisiTahapPemberkasanData = dataConverter.convertStatusTahapPemberkasanToStatusTahapPemberkasanData(t);
			entityManager.persist(posisiTahapPemberkasanData);
			entityManager.flush();		
			return dataConverter.convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(posisiTahapPemberkasanData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}		
	}

	@Override
	public PosisiTahapPemberkasan update(PosisiTahapPemberkasan t) {
		PosisiTahapPemberkasanData posisiTahapPemberkasanData = dataConverter.convertStatusTahapPemberkasanToStatusTahapPemberkasanData(t);
		PosisiTahapPemberkasanData dataTermerge = entityManager.merge(posisiTahapPemberkasanData);	
		entityManager.flush();
		return dataConverter.convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(dataTermerge);
	}

	@Override
	public PosisiTahapPemberkasan updateId(String idLama, PosisiTahapPemberkasan t) throws IOException {		
		Query query = entityManager.createNamedQuery("PosisiTahapPemberkasanData.updateId");
		query.setParameter("idBaru", t.getId());
		query.setParameter("idLama", idLama);
		int updateCount = query.executeUpdate();
		if(updateCount > 0) {
			try {
				PosisiTahapPemberkasanData posisiTahapPemberkasanData = dataConverter.convertStatusTahapPemberkasanToStatusTahapPemberkasanData(t);
				PosisiTahapPemberkasanData dataTermerge = entityManager.merge(posisiTahapPemberkasanData);
				entityManager.flush();
				return dataConverter.convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(dataTermerge);
			} catch (Exception e) {
				throw new IOException("data id sudah ada");
			}
			
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public PosisiTahapPemberkasan delete(PosisiTahapPemberkasan t) throws IOException {
		PosisiTahapPemberkasanData posisiTahapPemberkasanData = entityManager.find(PosisiTahapPemberkasanData.class, t.getId());
		if(posisiTahapPemberkasanData != null) {
			entityManager.remove(posisiTahapPemberkasanData);	
			entityManager.flush();
			return dataConverter.convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(posisiTahapPemberkasanData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public List<PosisiTahapPemberkasan> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PosisiTahapPemberkasanData> cq = cb.createQuery(PosisiTahapPemberkasanData.class);
		Root<PosisiTahapPemberkasanData> root = cq.from(PosisiTahapPemberkasanData.class);		
		
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
			case "keterangan":
				daftarPredicate.add(cb.like(cb.lower(root.get("keterangan")), "%"+filter.getValue().toLowerCase()+"%"));
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
			case "keterangan":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("keterangan")));
				}
				else {
					cq.orderBy(cb.desc(root.get("keterangan")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<PosisiTahapPemberkasanData> q = null;		
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
				.map(d -> dataConverter.convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(d))
				.collect(Collectors.toList());
	}
	
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<PosisiTahapPemberkasanData> root = cq.from(PosisiTahapPemberkasanData.class);		
		
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
			case "keterangan":
				daftarPredicate.add(cb.like(cb.lower(root.get("keterangan")), "%"+filter.getValue().toLowerCase()+"%"));
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
