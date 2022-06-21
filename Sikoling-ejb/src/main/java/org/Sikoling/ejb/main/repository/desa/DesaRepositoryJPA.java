package org.Sikoling.ejb.main.repository.desa;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.repository.IDesaRepository;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;

import jakarta.persistence.EntityManager;

public class DesaRepositoryJPA implements IDesaRepository {
	
	private final EntityManager entityManager;

	public DesaRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Desa save(Desa t, String s) {
		DesaData desaData = convertDesaToDesaData(t, s);
		entityManager.persist(desaData);
		entityManager.flush();
		return convertDesaDataToDesa(desaData);
	}
	
	@Override
	public Desa update(Desa t, String s) {
		DesaData desaData = convertDesaToDesaData(t, s);
		desaData = entityManager.merge(desaData);
		return convertDesaDataToDesa(desaData);
	}
	
	@Override
	public List<Desa> getAll() {
		return entityManager.createNamedQuery("DesaData.findAll", DesaData.class)
				.getResultList()
				.stream()
				.map(t -> convertDesaDataToDesa(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Desa> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("DesaData.findAll", DesaData.class)
			.setMaxResults(pageSize)
			.setFirstResult((page-1)*pageSize)
			.getResultList()
			.stream()
			.map(d -> convertDesaDataToDesa(d))
			.collect(Collectors.toList());
	}

	@Override
	public List<Desa> getByNama(String nama) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("DesaData.findByNama", DesaData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(d -> convertDesaDataToDesa(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<Desa> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("DesaData.findByNama", DesaData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> convertDesaDataToDesa(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<Desa> getByKecamatan(String idKecamatan) {
		return entityManager.createNamedQuery("DesaData.findByKecamatan", DesaData.class)
				.setParameter("idKecamatan", idKecamatan)
				.getResultList()
				.stream()
				.map(t -> convertDesaDataToDesa(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Desa> getByKecamatanAndPage(String idKecamatan, Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("DesaData.findByKecamatan", DesaData.class)
				.setParameter("idKecamatan", idKecamatan)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> convertDesaDataToDesa(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<Desa> getByKecamatanAndNama(String idKecamatan, String nama) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("DesaData.findByKecamatanAndNama", DesaData.class)
				.setParameter("nama", nama)
				.setParameter("idKecamatan", idKecamatan)
				.getResultList()
				.stream()
				.map(d -> convertDesaDataToDesa(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<Desa> getByKecamatanAndNamaAndPage(String idKecamatan, String nama, Integer page,
			Integer pageSize) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("DesaData.findByKecamatanAndNama", DesaData.class)
				.setParameter("nama", nama)
				.setParameter("idKecamatan", idKecamatan)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> convertDesaDataToDesa(d))
				.collect(Collectors.toList());
	}

	private DesaData convertDesaToDesaData(Desa desa, String idKecamatan) {
		DesaData desaData = new DesaData();
		desaData.setId(desa.getId());
		desaData.setNama(desa.getNama());
		KecamatanData kecamatanData = new KecamatanData();
		kecamatanData.setId(idKecamatan);
		desaData.setKecamatan(kecamatanData);
		return desaData;
	}
	
	private Desa convertDesaDataToDesa(DesaData desaData) {
		return new Desa(desaData.getId(), desaData.getNama());
	}
	
}

	
