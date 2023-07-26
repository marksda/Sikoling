package org.Sikoling.ejb.main.repository.dokumen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IRegisterDokumenRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class RegisterDokumenRepositoryJPA implements IRegisterDokumenRepository {

	private final EntityManager entityManager;	
	private final DataConverter dataConverter;
	
	public RegisterDokumenRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public RegisterDokumen save(RegisterDokumen t) throws IOException {		
		try {
			RegisterDokumenData registerDokumenData = dataConverter.convertRegisterDokumenToRegisterDokumenData(t);
			entityManager.persist(registerDokumenData);
			entityManager.flush();
			return dataConverter.convertRegisterDokumenDataToRegisterDokumenWithPerusahaan(registerDokumenData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}		
	}
	
	@Override
	public RegisterDokumen update(RegisterDokumen t) {
		RegisterDokumenData registerDokumenData = dataConverter.convertRegisterDokumenToRegisterDokumenData(t);
		RegisterDokumenData dataTermerge = entityManager.merge(registerDokumenData);
		entityManager.flush();
		return dataConverter.convertRegisterDokumenDataToRegisterDokumenWithPerusahaan(dataTermerge);
	}
	
	@Override
	public RegisterDokumen updateId(String idLama, RegisterDokumen t) throws IOException {
		Query query = entityManager.createNamedQuery("RegisterDokumenData.updateId");
		query.setParameter("idBaru", t.getId());
		query.setParameter("idLama", idLama);
		int updateCount = query.executeUpdate();
		if(updateCount > 0) {
			RegisterDokumenData registerDokumenData = dataConverter.convertRegisterDokumenToRegisterDokumenData(t);
			RegisterDokumenData dataTermerge = entityManager.merge(registerDokumenData);
			entityManager.flush();
			return dataConverter.convertRegisterDokumenDataToRegisterDokumenWithOutPerusahaan(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public RegisterDokumen delete(RegisterDokumen t) throws IOException {
		RegisterDokumenData registerDokumenData = entityManager.find(RegisterDokumenData.class, t.getId());
		if(registerDokumenData != null) {
			entityManager.remove(registerDokumenData);	
			entityManager.flush();
			return dataConverter.convertRegisterDokumenDataToRegisterDokumenWithOutPerusahaan(registerDokumenData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public List<RegisterDokumen> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RegisterDokumenData> cq = cb.createQuery(RegisterDokumenData.class);
		Root<RegisterDokumenData> root = cq.from(RegisterDokumenData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.getFilters().iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "id_perusahaan":
				daftarPredicate.add(cb.equal(root.get("perusahaanData").get("id"), filter.getValue()));
				break;
			case "npwp_perusahaan":
				daftarPredicate.add(cb.equal(root.get("perusahaanData").get("npwp"), filter.getValue()));
				break;
			case "nama_perusahaan":
				daftarPredicate.add(cb.like(cb.lower(root.get("perusahaanData").get("nama")), filter.getValue().toLowerCase()+"%"));
				break;
			case "id_dokumen":
				daftarPredicate.add(cb.equal(root.get("dokumenData").get("id"), filter.getValue()));
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
			case "nama_perusahaan":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("perusahaanData").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("perusahaanData").get("nama")));
				}
				break;
			case "nama_dokumen":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("dokumenData").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("dokumenData").get("nama")));
				}
				break;
			case "tanggalRegistrasi":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("tanggalRegistrasi")));
				}
				else {
					cq.orderBy(cb.desc(root.get("tanggalRegistrasi")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<RegisterDokumenData> q = null;		
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
				.map(d -> dataConverter.convertRegisterDokumenDataToRegisterDokumenWithPerusahaan(d))
				.collect(Collectors.toList());
	}
	
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<RegisterDokumenData> root = cq.from(RegisterDokumenData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "id_perusahaan":
				daftarPredicate.add(cb.equal(root.get("perusahaanData").get("id"), filter.getValue()));
				break;
			case "npwp_perusahaan":
				daftarPredicate.add(cb.equal(root.get("perusahaanData").get("npwp"), filter.getValue()));
				break;
			case "nama_perusahaan":
				daftarPredicate.add(cb.like(cb.lower(root.get("perusahaanData").get("nama")), filter.getValue().toLowerCase()+"%"));
				break;
			case "id_dokumen":
				daftarPredicate.add(cb.equal(root.get("dokumenData").get("id"), filter.getValue()));
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
