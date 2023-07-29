package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Pegawai;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IPegawaiPerusahaanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
	public Pegawai save(Pegawai t) throws IOException {
		try {
			PegawaiData pegawaiData = dataConverter.convertPegawaiToPegawaiData(t);
			entityManager.persist(pegawaiData);
			entityManager.flush();		
			return dataConverter.convertPegawaiDataToPegawai(pegawaiData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}		
	}

	@Override
	public Pegawai update(Pegawai t) {
		PegawaiData pegawaiData = dataConverter.convertPegawaiToPegawaiData(t);
		PegawaiData dataTermerge = entityManager.merge(pegawaiData);	
		entityManager.flush();
		return dataConverter.convertPegawaiDataToPegawai(dataTermerge);
	}

	@Override
	public Pegawai updateId(String idLama, Pegawai t) throws IOException {
		Query query = entityManager.createNamedQuery("PegawaiData.updateId");
		query.setParameter("idBaru", t.getId());
		query.setParameter("idLama", idLama);
		int updateCount = query.executeUpdate();
		if(updateCount > 0) {
			PegawaiData dataLama = entityManager.find(PegawaiData.class, t.getId());
			PegawaiData pegawaiData = dataConverter.convertPegawaiToPegawaiData(t);
			dataLama.setRegisterPerusahaanData(pegawaiData.getRegisterPerusahaanData());
			dataLama.setPersonData(pegawaiData.getPersonData());
			dataLama.setJabatanData(pegawaiData.getJabatanData());
			entityManager.flush();
			return dataConverter.convertPegawaiDataToPegawai(dataLama);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public Pegawai delete(Pegawai t) throws IOException {
		PegawaiData pegawaiData = entityManager.find(PegawaiData.class, t.getId());
		if(pegawaiData != null) {
			entityManager.remove(pegawaiData);	
			entityManager.flush();
			return dataConverter.convertPegawaiDataToPegawai(pegawaiData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public List<Pegawai> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PegawaiData> cq = cb.createQuery(PegawaiData.class);
		Root<PegawaiData> root = cq.from(PegawaiData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.getFilters().iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "nik":
				daftarPredicate.add(cb.equal(root.get("personData").get("id"), filter.getValue()));
				break;
			case "pegawai":
				daftarPredicate.add(cb.like(cb.lower(root.get("personData").get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "perusahaan":
				daftarPredicate.add(cb.like(cb.lower(root.get("registerPerusahaanData").get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "perusahaan_id":
				daftarPredicate.add(cb.equal(cb.lower(root.get("registerPerusahaanData").get("id")), filter.getValue()));
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
			case "pegawai":
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
		
		TypedQuery<PegawaiData> q = null;		
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
				.map(d -> dataConverter.convertPegawaiDataToPegawai(d))
				.collect(Collectors.toList());
	}
	
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<PegawaiData> root = cq.from(PegawaiData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "nik":
				daftarPredicate.add(cb.equal(root.get("personData").get("id"), filter.getValue()));
				break;
			case "pegawai":
				daftarPredicate.add(cb.like(cb.lower(root.get("personData").get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "perusahaan":
				daftarPredicate.add(cb.like(cb.lower(root.get("registerPerusahaanData").get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "perusahaan_id":
				daftarPredicate.add(cb.equal(cb.lower(root.get("registerPerusahaanData").get("id")), filter.getValue()));
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
