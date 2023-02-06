package org.Sikoling.ejb.main.repository.permohonan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatusWali;
import org.Sikoling.ejb.abstraction.repository.IStatusWaliRepository;

import jakarta.persistence.EntityManager;

public class StatusPengurusPermohonanRepositoryJPA implements IStatusWaliRepository {
	
	private final EntityManager entityManager;

	public StatusPengurusPermohonanRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<StatusWali> getAll() {
		return entityManager.createNamedQuery("StatusPengurusPermohonanData.findAll", StatusPengurusPermohonanData.class)
				.getResultList()
				.stream()
				.map(d -> convertStatusPengurusPermohonanDataToStatusPengurusPermohonan(d))
				.collect(Collectors.toList());
	}

	@Override
	public StatusWali save(StatusWali t) {
		StatusPengurusPermohonanData statusPengurusPermohonanData = convertStatusPengurusPermohonanToStatusPengurusPermohonanData(t);
		entityManager.persist(statusPengurusPermohonanData);
		entityManager.flush();
		
		return convertStatusPengurusPermohonanDataToStatusPengurusPermohonan(statusPengurusPermohonanData);
	}

	@Override
	public StatusWali update(StatusWali t) {
		StatusPengurusPermohonanData statusPengurusPermohonanData = convertStatusPengurusPermohonanToStatusPengurusPermohonanData(t);
		statusPengurusPermohonanData = entityManager.merge(statusPengurusPermohonanData);
		return convertStatusPengurusPermohonanDataToStatusPengurusPermohonan(statusPengurusPermohonanData);
	}

	@Override
	public DeleteResponse delete(String id) {
		StatusPengurusPermohonanData statusPengurusPermohonanData = entityManager.find(StatusPengurusPermohonanData.class, id);
		entityManager.remove(statusPengurusPermohonanData);			
		return new DeleteResponse(true, id);
	}

	@Override
	public List<StatusWali> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("StatusPengurusPermohonanData.findAll", StatusPengurusPermohonanData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> convertStatusPengurusPermohonanDataToStatusPengurusPermohonan(d))
				.collect(Collectors.toList());
	}
	
	private StatusWali convertStatusPengurusPermohonanDataToStatusPengurusPermohonan(StatusPengurusPermohonanData d) {
		return new StatusWali(d.getId(), d.getNama());
	}

	private StatusPengurusPermohonanData convertStatusPengurusPermohonanToStatusPengurusPermohonanData(StatusWali t) {
		StatusPengurusPermohonanData statusPengurusPermohonanData = new StatusPengurusPermohonanData();
		statusPengurusPermohonanData.setId(t.getId());
		statusPengurusPermohonanData.setNama(t.getNama());
		
		return statusPengurusPermohonanData;
	}
}
