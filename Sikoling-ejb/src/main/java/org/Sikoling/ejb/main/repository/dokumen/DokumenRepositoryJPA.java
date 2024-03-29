package org.Sikoling.ejb.main.repository.dokumen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.repository.IDokumenRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class DokumenRepositoryJPA implements IDokumenRepository {

	private final EntityManager entityManager;	
	private final DataConverter dataConverter;	
	
	public DokumenRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public Dokumen save(Dokumen t) throws IOException {
		try {
			DokumenData detailDokumenPerusahaanData = dataConverter.convertMasterDokumenToMasterDokumenData(t);
			entityManager.persist(detailDokumenPerusahaanData);
			entityManager.flush();
			return dataConverter.convertMasterDokumenDataToMasterDokumen(detailDokumenPerusahaanData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}
		
	}

	@Override
	public Dokumen update(Dokumen t) {
		DokumenData detailDokumenPerusahaanData = dataConverter.convertMasterDokumenToMasterDokumenData(t);
		DokumenData dataTermerge = entityManager.merge(detailDokumenPerusahaanData);
		entityManager.flush();
		return dataConverter.convertMasterDokumenDataToMasterDokumen(dataTermerge);
	}

	@Override
	public Dokumen updateId(String idLama, Dokumen t) throws IOException {
		Query query = entityManager.createNamedQuery("DokumenData.updateId");
		query.setParameter("idBaru", t.getId());
		query.setParameter("idLama", idLama);
		int updateCount = query.executeUpdate();
		if(updateCount > 0) {			
			DokumenData masterDokumenData = dataConverter.convertMasterDokumenToMasterDokumenData(t);
			DokumenData dataTermerge = entityManager.merge(masterDokumenData);
			entityManager.flush();
			
			return dataConverter.convertMasterDokumenDataToMasterDokumen(dataTermerge);
		} 
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public Dokumen delete(Dokumen t) throws IOException {
		DokumenData dokumenData = entityManager.find(DokumenData.class, t.getId());
		
		if(dokumenData != null) {
			entityManager.remove(dokumenData);
			entityManager.flush();
			return dataConverter.convertMasterDokumenDataToMasterDokumen(dokumenData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
		
	}
	
	@Override
	public List<Dokumen> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<DokumenData> cq = cb.createQuery(DokumenData.class);
		Root<DokumenData> root = cq.from(DokumenData.class);		
		
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
			case "kategori":
				daftarPredicate.add(cb.equal(root.get("kategoriDokumenData").get("id"), filter.getValue()));
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
			case "kategori":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("kategoriDokumenData").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("kategoriDokumenData").get("nama")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<DokumenData> q = null;		
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
				.map(d -> dataConverter.convertMasterDokumenDataToMasterDokumen(d))
				.collect(Collectors.toList());
	}
	
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<DokumenData> root = cq.from(DokumenData.class);		
		
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
			case "kategori":
				daftarPredicate.add(cb.equal(root.get("kategoriDokumenData").get("id"), filter.getValue()));
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
