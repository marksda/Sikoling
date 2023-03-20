package org.Sikoling.ejb.main.repository.permohonan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;
import org.Sikoling.ejb.abstraction.repository.IRegisterPermohonanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class RegisterPermohonanRepositoryJPA implements IRegisterPermohonanRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;

	public RegisterPermohonanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public List<RegisterPermohonan> getAll() {
		return entityManager.createNamedQuery("RegisterPermohonanData.findAll", RegisterPermohonanData.class)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertRegisterPermohonanDataToRegisterPermohonan(d))
				.collect(Collectors.toList());
	}

	@Override
	public RegisterPermohonan save(RegisterPermohonan t) {
		RegisterPermohonanData registerPermohonanData = dataConverter.convertRegisterPermohonanToRegisterPermohonanData(t);
		entityManager.persist(registerPermohonanData);
		entityManager.flush();
		
		return dataConverter.convertRegisterPermohonanDataToRegisterPermohonan(registerPermohonanData);
	}

	@Override
	public RegisterPermohonan update(RegisterPermohonan t) {
		RegisterPermohonanData registerPermohonanData = dataConverter.convertRegisterPermohonanToRegisterPermohonanData(t);
		registerPermohonanData = entityManager.merge(registerPermohonanData);
		return dataConverter.convertRegisterPermohonanDataToRegisterPermohonan(registerPermohonanData);
	}

	@Override
	public List<RegisterPermohonan> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("RegisterPermohonanData.findAll", RegisterPermohonanData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertRegisterPermohonanDataToRegisterPermohonan(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterPermohonan> getByIdPengakses(String idPengakses) {
		return entityManager.createNamedQuery("RegisterPermohonanData.findByPengakses", RegisterPermohonanData.class)
				.setParameter("idKreator", idPengakses)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertRegisterPermohonanDataToRegisterPermohonan(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterPermohonan> getByIdPerusahaan(String idPerusahaan) {
		return entityManager.createNamedQuery("RegisterPermohonanData.findByPerusahaan", RegisterPermohonanData.class)
				.setParameter("idRegisterPerusahaan", idPerusahaan)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertRegisterPermohonanDataToRegisterPermohonan(d))
				.collect(Collectors.toList());
	}

	@Override
	public DeleteResponse delete(String id) {
		RegisterPermohonanData registerPermohonanData = entityManager.find(RegisterPermohonanData.class, id);
		entityManager.remove(registerPermohonanData);			
		return new DeleteResponse(true, id);
	}
	
	@Override
	public List<RegisterPermohonan> getByIdPenerima(String idPenerima) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RegisterPermohonanData> cq = cb.createQuery(RegisterPermohonanData.class);
		Root<RegisterPermohonanData> root = cq.from(RegisterPermohonanData.class);
		cq.select(root);
		cq.where(cb.equal(root.get("posisiTahapPemberkasanPenerimaData").get("id"), idPenerima).isNotNull());
//		cq.where(
//			cb.like(
//				cb.lower(root.get("perusahaanData").get("nama")), 
//				"%"+idPenerima.toLowerCase()+"%"
//			)				
//		);
		TypedQuery<RegisterPermohonanData> q = entityManager.createQuery(cq);
		return q.getResultList()
				.stream()
				.map(d -> dataConverter.convertRegisterPermohonanDataToRegisterPermohonan(d))
				.collect(Collectors.toList());
		
	}

	@Override
	public List<RegisterPermohonan> getByIdPengirim(String idPengirim) {
		return entityManager.createNamedQuery("RegisterPermohonanData.findByPengirim", RegisterPermohonanData.class)
				.setParameter("idPengirim", idPengirim)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertRegisterPermohonanDataToRegisterPermohonan(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterPermohonan> getByIdPengirimAtauPenerima(String idPengirim, String idPenerima) {
		return entityManager.createNamedQuery("RegisterPermohonanData.findByPenerimaAtauPenerima", RegisterPermohonanData.class)
				.setParameter("idPengirim", idPengirim)
				.setParameter("idPenerima", idPenerima)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertRegisterPermohonanDataToRegisterPermohonan(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterPermohonan> getByIdPengirimAtauPenerimaOnProcess(String idPengirim, String idPenerima) {
		List<String> daftarIdFlow = new ArrayList<String>();
		daftarIdFlow.add("0");
		daftarIdFlow.add("1");
		daftarIdFlow.add("3");
		daftarIdFlow.add("4");
		
		return entityManager.createNamedQuery("RegisterPermohonanData.findByPenerimaAtauPenerimaOnProcess", RegisterPermohonanData.class)
				.setParameter("idPengirim", idPengirim)
				.setParameter("idPenerima", idPenerima)
				.setParameter("daftarIdFlow", daftarIdFlow)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertRegisterPermohonanDataToRegisterPermohonan(d))
				.collect(Collectors.toList());
	}
		
}
