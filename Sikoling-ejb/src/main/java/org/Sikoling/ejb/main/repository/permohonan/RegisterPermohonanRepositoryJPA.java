package org.Sikoling.ejb.main.repository.permohonan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;
import org.Sikoling.ejb.abstraction.repository.IRegisterPermohonanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;

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
				.setParameter("idPengakses", idPengakses)
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
		
}
