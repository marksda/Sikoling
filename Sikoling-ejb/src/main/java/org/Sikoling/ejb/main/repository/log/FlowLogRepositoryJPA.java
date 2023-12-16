package org.Sikoling.ejb.main.repository.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.entity.log.FlowLog;
import org.Sikoling.ejb.abstraction.repository.IFlowLogRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class FlowLogRepositoryJPA implements IFlowLogRepository {
	
	private final EntityManager entityManager;	
	private final DataConverter dataConverter;

	public FlowLogRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public FlowLog save(FlowLog t) throws IOException {
		try {
			FlowLogData flowLogData = dataConverter.convertFlowLogToFlowLogData(t);
			entityManager.persist(flowLogData);
			entityManager.flush();
			return dataConverter.convertFlowLogDataToFlowLog(flowLogData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}
		
	}

	@Override
	public FlowLog update(FlowLog t) {
		FlowLogData flowLogData = dataConverter.convertFlowLogToFlowLogData(t);
		FlowLogData dataTermerge = entityManager.merge(flowLogData);
		entityManager.flush();
		return dataConverter.convertFlowLogDataToFlowLog(dataTermerge);
	}

	@Override
	public FlowLog updateId(String idLama, FlowLog t) throws IOException {
		FlowLogData dataLama = entityManager.find(FlowLogData.class, idLama);
		if(dataLama != null) {
			FlowLogData flowLogData = dataConverter.convertFlowLogToFlowLogData(t);
			entityManager.remove(dataLama);	
			FlowLogData dataTermerge = entityManager.merge(flowLogData);
			entityManager.flush();
			return dataConverter.convertFlowLogDataToFlowLog(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public FlowLog delete(FlowLog d) throws IOException {
		FlowLogData flowLogData = entityManager.find(FlowLogData.class, d.getId());
		if(flowLogData != null) {
			entityManager.remove(flowLogData);		
			entityManager.flush();
			return dataConverter.convertFlowLogDataToFlowLog(flowLogData);
		}
		else {
			throw new IOException("data sudah ada");
		}
	}
	
	@Override
	public List<FlowLog> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<FlowLogData> cq = cb.createQuery(FlowLogData.class);
		Root<FlowLogData> root = cq.from(FlowLogData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.getFilters().iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "tanggal":
				daftarPredicate.add(cb.equal(root.get("tanggal"), filter.getValue()));
				break;
			case "perusahaan":
				daftarPredicate.add(cb.like(cb.lower(root.get("flowLogPermohonanData").get("registerPermohonan").get("perusahaanData").get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "kategori_log":
				daftarPredicate.add(cb.equal(root.get("kategoriLogData").get("id"), filter.getValue()));
				break;
			case "posisi_tahap_pemberkasan_pengirim":
				daftarPredicate.add(cb.equal(root.get("posisiTahapPemberkasanPengirimData").get("id"), filter.getValue()));
				break;
			case "posisi_tahap_pemberkasan_penerima":
				daftarPredicate.add(cb.equal(root.get("posisiTahapPemberkasanPenerimaData").get("id"), filter.getValue()));
				break;
			case "status_flow":
				daftarPredicate.add(cb.equal(root.get("statusFlowData").get("id"), filter.getValue()));
				break;
			case "pengakses":
				daftarPredicate.add(cb.equal(root.get("autorisasiData").get("id"), filter.getValue()));
				break;
			case "kepemilikan_flow_log_permohonan":
				daftarPredicate.add(cb.equal(root.get("flowLogPermohonanData").get("registerPermohonan").get("perusahaanData").get("daftarAutorityPerusahaanData").get("autority").get("id"), filter.getValue()));
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
			case "tanggal":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("tanggal")));
				}
				else {
					cq.orderBy(cb.desc(root.get("tanggal")));
				}
				break;
			case "perusahaan":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("flowLogPermohonanData").get("registerPermohonan").get("perusahaanData").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("flowLogPermohonanData").get("registerPermohonan").get("perusahaanData").get("nama")));
				}
				break;
			case "kategori_log":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("kategoriLogData").get("id")));
				}
				else {
					cq.orderBy(cb.desc(root.get("kategoriLogData").get("id")));
				}
				break;	
			case "posisi_tahap_pemberkasan_pengirim":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("posisiTahapPemberkasanPengirimData").get("id")));
				}
				else {
					cq.orderBy(cb.desc(root.get("posisiTahapPemberkasanPengirimData").get("id")));
				}
				break;
			case "posisi_tahap_pemberkasan_penerima":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("posisiTahapPemberkasanPenerimaData").get("id")));
				}
				else {
					cq.orderBy(cb.desc(root.get("posisiTahapPemberkasanPenerimaData").get("id")));
				}
				break;			
			case "status_flow":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("statusFlowData").get("id")));
				}
				else {
					cq.orderBy(cb.desc(root.get("statusFlowData").get("id")));
				}
				break;
			case "pengakses":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("autorisasiData").get("id")));
				}
				else {
					cq.orderBy(cb.desc(root.get("autorisasiData").get("id")));
				}
				break;		
			default:
				break;
			}			
		}
		
		TypedQuery<FlowLogData> q = null;		
		if( queryParamFilters.getPageSize() != null && queryParamFilters.getPageSize() > 0) { //limit query result
			q = entityManager.createQuery(cq)
					.setMaxResults(queryParamFilters.getPageSize())
					.setFirstResult((queryParamFilters.getPageNumber()-1)*queryParamFilters.getPageSize());
		}
		else {
			q = entityManager.createQuery(cq);
		}
		
		return q.getResultList()
				.stream()
				.map(d -> dataConverter.convertFlowLogDataToFlowLog(d))
				.collect(Collectors.toList());
	}
	
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<FlowLogData> root = cq.from(FlowLogData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "tanggal":
				daftarPredicate.add(cb.equal(root.get("tanggal"), filter.getValue()));
				break;
			case "perusahaan":
				daftarPredicate.add(cb.like(cb.lower(root.get("flowLogPermohonanData").get("registerPermohonan").get("perusahaanData").get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "kategori_log":
				daftarPredicate.add(cb.equal(root.get("kategoriLogData").get("id"), filter.getValue()));
				break;
			case "posisi_tahap_pemberkasan_pengirim":
				daftarPredicate.add(cb.equal(root.get("posisiTahapPemberkasanPengirimData").get("id"), filter.getValue()));
				break;
			case "posisi_tahap_pemberkasan_penerima":
				daftarPredicate.add(cb.equal(root.get("posisiTahapPemberkasanPenerimaData").get("id"), filter.getValue()));
				break;
			case "status_flow":
				daftarPredicate.add(cb.equal(root.get("statusFlowData").get("id"), filter.getValue()));
				break;
			case "pengakses":
				daftarPredicate.add(cb.equal(root.get("autorisasiData").get("id"), filter.getValue()));
				break;
			case "kepemilikan_flow_log_permohonan":
				daftarPredicate.add(cb.equal(root.get("flowLogPermohonanData").get("registerPermohonan").get("autorisasiData").get("daftarAutorityPerusahaanData").get("autority").get("id"), filter.getValue()));
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