package org.Sikoling.ejb.main.repository.perusahaan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.repository.IRegisterPerusahaanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class RegisterPerusahaanRepositoryJPA implements IRegisterPerusahaanRepository {
	
	private final EntityManager entityManager;	
	private final DataConverter dataConverter;

	public RegisterPerusahaanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public List<RegisterPerusahaan> getAll() {
		return entityManager.createNamedQuery("RegisterPerusahaanData.findAll", RegisterPerusahaanData.class)
				.getResultList()
				.stream()
				.map(t -> dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public RegisterPerusahaan save(RegisterPerusahaan t) {
		RegisterPerusahaanData registerPerusahaanData = dataConverter.convertRegisterPerusahaanToRegisterPerusahaanData(t);
		entityManager.persist(registerPerusahaanData);
		
		return dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(registerPerusahaanData);
	}
	
	@Override
	public DeleteResponse delete(String id) {
		RegisterPerusahaanData registerPerusahaanData = entityManager.find(RegisterPerusahaanData.class, id);
		entityManager.remove(registerPerusahaanData);			
		return new DeleteResponse(true, id);
	}
	
	@Override
	public DeleteResponse deleteLinkKepemilikanPerusahaan(String idAutority, String idRegisterPerusahaan) {
		AutorityPerusahaanDataId id = new AutorityPerusahaanDataId();
		id.setAutority(idAutority);
		id.setPerusahaan(idRegisterPerusahaan);
		
		AutorityPerusahaanData autorityPerusahaanData = entityManager.find(AutorityPerusahaanData.class, id);
		entityManager.remove(autorityPerusahaanData);			
		return new DeleteResponse(true, idRegisterPerusahaan);
	}
	
	@Override
	public RegisterPerusahaan update(RegisterPerusahaan t) {
		RegisterPerusahaanData registerPerusahaanData = dataConverter.convertRegisterPerusahaanToRegisterPerusahaanData(t);		
		registerPerusahaanData = entityManager.merge(registerPerusahaanData);
		return dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(registerPerusahaanData);
	}
		
	@Override
	public List<RegisterPerusahaan> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("RegisterPerusahaanData.findAll", RegisterPerusahaanData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterPerusahaan> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("RegisterPerusahaanData.findByQueryNama", RegisterPerusahaanData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterPerusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("RegisterPerusahaanData.findByQueryNama", RegisterPerusahaanData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public Boolean cekById(String id) {
		RegisterPerusahaanData perusahaanData = entityManager.find(RegisterPerusahaanData.class, id);
		if(perusahaanData == null) {
			return false;
		}
		else {
			return true;
		}		 
	}
	
	@Override
	public RegisterPerusahaan getByNpwp(String npwp) {		
		try {
			RegisterPerusahaanData registerPerusahaanData = entityManager.createNamedQuery("RegisterPerusahaanData.findByNpwp", RegisterPerusahaanData.class)
					.setParameter("npwp", npwp)
					.getSingleResult();
			return dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(registerPerusahaanData);
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	@Override
	public List<RegisterPerusahaan> getByIdKreator(String idKreator) {
		return entityManager.createNamedQuery("RegisterPerusahaanData.findByIdKreator", AutorityPerusahaanData.class)
				.setParameter("idKreator", idKreator)
				.getResultList()
				.stream()
				.map(t -> dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(t.getPerusahaan()))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterPerusahaan> getByIdLinkKepemilikan(String idAutorisasi) {
		return entityManager.createNamedQuery("AutorityPerusahaanData.findByPemilik", AutorityPerusahaanData.class)
				.setParameter("idAutorisasi", idAutorisasi)
				.getResultList()
				.stream()
				.map(t -> dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(t.getPerusahaan()))
				.collect(Collectors.toList());
	}	
	
	@Override
	public List<RegisterPerusahaan> getByIdLinkKepemilikanTanpaRegisterDokumen(String idAutorisasi) {
		return entityManager.createNamedQuery("AutorityPerusahaanData.findByPemilik", AutorityPerusahaanData.class)
				.setParameter("idAutorisasi", idAutorisasi)
				.getResultList()
				.stream()
				.map(t -> dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaanWithOutRegisterDokumen(t.getPerusahaan()))
				.collect(Collectors.toList());
	}

}

	