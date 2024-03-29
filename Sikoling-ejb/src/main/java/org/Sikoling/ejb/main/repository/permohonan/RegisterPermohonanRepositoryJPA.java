package org.Sikoling.ejb.main.repository.permohonan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;
import org.Sikoling.ejb.abstraction.repository.IRegisterPermohonanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class RegisterPermohonanRepositoryJPA implements IRegisterPermohonanRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;

	public RegisterPermohonanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public RegisterPermohonan save(RegisterPermohonan t) throws IOException {
		try {
			RegisterPermohonanData skalaUsahaData = dataConverter.convertRegisterPermohonanToRegisterPermohonanData(t);
			entityManager.persist(skalaUsahaData);
			entityManager.flush();		
			return dataConverter.convertRegisterPermohonanDataToRegisterPermohonan(skalaUsahaData);
		} catch (Exception e) {
			throw new IOException("data sudah ada");
		}		
	}

	@Override
	public RegisterPermohonan update(RegisterPermohonan t) {
		RegisterPermohonanData skalaUsahaData = dataConverter.convertRegisterPermohonanToRegisterPermohonanData(t);
		RegisterPermohonanData dataTermerge = entityManager.merge(skalaUsahaData);	
		entityManager.flush();
		return dataConverter.convertRegisterPermohonanDataToRegisterPermohonan(dataTermerge);
	}

	@Override
	public RegisterPermohonan updateId(String idLama, RegisterPermohonan t) throws IOException {
		RegisterPermohonanData dataLama = entityManager.find(RegisterPermohonanData.class, idLama);
		if(dataLama != null) {
			RegisterPermohonanData skalaUsahaData = dataConverter.convertRegisterPermohonanToRegisterPermohonanData(t);
			entityManager.remove(dataLama);	
			RegisterPermohonanData dataTermerge = entityManager.merge(skalaUsahaData);
			entityManager.flush();
			return dataConverter.convertRegisterPermohonanDataToRegisterPermohonan(dataTermerge);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}

	@Override
	public RegisterPermohonan delete(RegisterPermohonan t) throws IOException {
		RegisterPermohonanData skalaUsahaData = entityManager.find(RegisterPermohonanData.class, t.getId());
		if(skalaUsahaData != null) {
			entityManager.remove(skalaUsahaData);	
			entityManager.flush();
			return dataConverter.convertRegisterPermohonanDataToRegisterPermohonan(skalaUsahaData);
		}
		else {
			throw new IOException("Data tidak ditemukan");
		}
	}
	
	@Override
	public List<RegisterPermohonan> getDaftarData(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RegisterPermohonanData> cq = cb.createQuery(RegisterPermohonanData.class);
		Root<RegisterPermohonanData> root = cq.from(RegisterPermohonanData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.getFilters().iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "kategori_permohonan":
				daftarPredicate.add(cb.equal(root.get("kategoriPermohonanData").get("id"), filter.getValue()));
				break;
			case "tanggal_registrasi":
				daftarPredicate.add(cb.equal(root.get("tanggalRegistrasi"), filter.getValue()));
				break;
			case "perusahaan":
				daftarPredicate.add(cb.like(cb.lower(root.get("perusahaanData").get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "pengakses":
				daftarPredicate.add(cb.equal(root.get("autorisasiData").get("id"), filter.getValue()));
				break;
			case "kategori_pengurus_permohonan":
				daftarPredicate.add(cb.equal(root.get("kategoriPengurusPermohonanData").get("id"), filter.getValue()));
				break;
			case "posisi_tahap_pemberkasan_pengirim":
				daftarPredicate.add(cb.equal(root.get("posisiTahapPemberkasanPengirimData").get("id"), filter.getValue()));
				break;
			case "posisi_tahap_pemberkasan_penerima":
				daftarPredicate.add(cb.equal(root.get("posisiTahapPemberkasanPenerimaData").get("id"), filter.getValue()));
				break;
			case "penanggung_jawab":
				daftarPredicate.add(cb.equal(root.get("penanggungJawab").get("id"), filter.getValue()));
				break;
			case "status_flow":
				daftarPredicate.add(cb.equal(root.get("statusFlowData").get("id"), filter.getValue()));
				break;
			case "kepemilikan":
				daftarPredicate.add(cb.equal(root.get("perusahaanData").get("daftarAutorityPerusahaanData").get("autority").get("id"), filter.getValue()));
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
			case "kategori_permohonan":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("kategoriPermohonanData").get("id")));
				}
				else {
					cq.orderBy(cb.desc(root.get("kategoriPermohonanData").get("id")));
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
			case "perusahaan":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("perusahaanData").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("perusahaanData").get("nama")));
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
			case "kategori_pengurus_permohonan":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("kategoriPengurusPermohonanData").get("id")));
				}
				else {
					cq.orderBy(cb.desc(root.get("kategoriPengurusPermohonanData").get("id")));
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
			case "penanggung_jawab":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("penanggungJawab").get("personData").get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("penanggungJawab").get("personData").get("nama")));
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
			default:
				break;
			}			
		}
		
		TypedQuery<RegisterPermohonanData> q = null;		
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
				.map(d -> dataConverter.convertRegisterPermohonanDataToRegisterPermohonan(d))
				.collect(Collectors.toList());
	}
	
	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<RegisterPermohonanData> root = cq.from(RegisterPermohonanData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "kategori_permohonan":
				daftarPredicate.add(cb.equal(root.get("kategoriPermohonanData").get("id"), filter.getValue()));
				break;
			case "tanggal_registrasi":
				daftarPredicate.add(cb.equal(root.get("tanggalRegistrasi"), filter.getValue()));
				break;
			case "perusahaan":
				daftarPredicate.add(cb.like(cb.lower(root.get("perusahaanData").get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "pengakses":
				daftarPredicate.add(cb.equal(root.get("autorisasiData").get("id"), filter.getValue()));
				break;
			case "kategori_pengurus_permohonan":
				daftarPredicate.add(cb.equal(root.get("kategoriPengurusPermohonanData").get("id"), filter.getValue()));
				break;
			case "posisi_tahap_pemberkasan_pengirim":
				daftarPredicate.add(cb.equal(root.get("posisiTahapPemberkasanPengirimData").get("id"), filter.getValue()));
				break;
			case "posisi_tahap_pemberkasan_penerima":
				daftarPredicate.add(cb.equal(root.get("posisiTahapPemberkasanPenerimaData").get("id"), filter.getValue()));
				break;
			case "penanggung_jawab":
				daftarPredicate.add(cb.equal(root.get("penanggungJawab").get("id"), filter.getValue()));
				break;
			case "status_flow":
				daftarPredicate.add(cb.equal(root.get("statusFlowData").get("id"), filter.getValue()));
				break;
			case "kepemilikan":
				daftarPredicate.add(cb.equal(root.get("perusahaanData").get("daftarAutorityPerusahaanData").get("autority").get("id"), filter.getValue()));
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
