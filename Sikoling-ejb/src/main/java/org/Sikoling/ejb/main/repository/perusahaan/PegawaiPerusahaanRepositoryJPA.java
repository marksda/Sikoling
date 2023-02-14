package org.Sikoling.ejb.main.repository.perusahaan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Pegawai;
import org.Sikoling.ejb.abstraction.repository.IPegawaiPerusahaanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import org.Sikoling.ejb.main.repository.person.PersonData;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class PegawaiPerusahaanRepositoryJPA implements IPegawaiPerusahaanRepository {
	
	private final EntityManager entityManager;	
	private final DataConverter dataConverter;

	public PegawaiPerusahaanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public List<Pegawai> getAll() {
		return entityManager.createNamedQuery("PegawaiPerusahaanData.findAll", PegawaiPerusahaanData.class)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertPegawaiPerusahaanDataToPegawaiPerusahaan(d))
				.collect(Collectors.toList());
	}

	@Override
	public Pegawai save(Pegawai t) {
		PegawaiPerusahaanData pegawaiPerusahaanData = dataConverter.convertPegawaiPerusahaanToPegawaiPerusahaanData(t);
		PersonData personData = null;
		
		try {
			personData = entityManager.createNamedQuery("PersonData.findById", PersonData.class)
					.setParameter("nik", pegawaiPerusahaanData.getPersonData().getId())
					.getSingleResult();
		} catch (NoResultException e) {
			personData = pegawaiPerusahaanData.getPersonData();
			entityManager.persist(personData);
			entityManager.flush();
		}
						
		entityManager.persist(pegawaiPerusahaanData);
		entityManager.flush();
		
		return dataConverter.convertPegawaiPerusahaanDataToPegawaiPerusahaan(pegawaiPerusahaanData);
	}

	@Override
	public Pegawai update(Pegawai t) {
		PegawaiPerusahaanData pegawaiPerusahaanData = dataConverter.convertPegawaiPerusahaanToPegawaiPerusahaanData(t);
		pegawaiPerusahaanData = entityManager.merge(pegawaiPerusahaanData);
		return dataConverter.convertPegawaiPerusahaanDataToPegawaiPerusahaan(pegawaiPerusahaanData);
	}

	@Override
	public List<Pegawai> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("PegawaiPerusahaanData.findAll", PegawaiPerusahaanData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertPegawaiPerusahaanDataToPegawaiPerusahaan(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<Pegawai> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("PegawaiPerusahaanData.findByQueryNama", PegawaiPerusahaanData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertPegawaiPerusahaanDataToPegawaiPerusahaan(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<Pegawai> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("PegawaiPerusahaanData.findByQueryNama", PegawaiPerusahaanData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertPegawaiPerusahaanDataToPegawaiPerusahaan(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<Pegawai> getByRegisterPerusahaan(String idRegisterPerusahaan) {
		return entityManager.createNamedQuery("PegawaiPerusahaanData.findByIdPerusahaan", PegawaiPerusahaanData.class)
				.setParameter("idRegisterPerusahaan", idRegisterPerusahaan)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertPegawaiPerusahaanDataToPegawaiPerusahaanWithOutRegisterPerusahaan(d))
				.collect(Collectors.toList());
	}

}
