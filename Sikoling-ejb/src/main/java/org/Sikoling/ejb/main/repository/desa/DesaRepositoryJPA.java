package org.Sikoling.ejb.main.repository.desa;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.repository.IDesaRepository;
import jakarta.persistence.EntityManager;

public class DesaRepositoryJPA implements IDesaRepository {
	
	private final EntityManager entityManager;

	public DesaRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Desa save(Desa t) {
		return null;
	}
	
	@Override
	public Desa update(Desa t) {
		return null;
	}
	
	@Override
	public Desa save(Desa t, Kecamatan k) {
		DesaData desaData = new DesaData(t.getId(), t.getNama(), k.getId());
		entityManager.persist(desaData);
		entityManager.flush();
		return convertDesaDataToDesa(desaData);
	}

	@Override
	public Desa update(Desa desa, Kecamatan kecamatan) {
		DesaData desaData = convertDesaToDesaData(desa, kecamatan);
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
			.map(t -> convertDesaDataToDesa(t))
			.collect(Collectors.toList());
	}

	@Override
	public List<Desa> getByQueryNama(String nama) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("DesaData.findAllByQueryNama", DesaData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertDesaDataToDesa(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Desa> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("DesaData.findAllByQueryNama", DesaData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertDesaDataToDesa(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Desa> getByKecamatan(String idKecamatan) {
		return entityManager.createNamedQuery("DesaData.findAllByIdKecamatan", DesaData.class)
				.setParameter("idKecamatan", idKecamatan)
				.getResultList()
				.stream()
				.map(t -> convertDesaDataToDesa(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Desa> getByKecamatanAndPage(String idKecamatan, Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("DesaData.findAllByIdKecamatan", DesaData.class)
				.setParameter("idKecamatan", idKecamatan)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertDesaDataToDesa(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Desa> getByKecamatanAndQueryNama(String idKecamatan, String nama) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("DesaData.findAllByIdKecamatanAndQueryNama", DesaData.class)
				.setParameter("nama", nama)
				.setParameter("idKecamatan", idKecamatan)
				.getResultList()
				.stream()
				.map(t -> convertDesaDataToDesa(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Desa> getByKecamatanAndQueryNamaAndPage(String idKecamatan, String nama, Integer page,
			Integer pageSize) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("DesaData.findAllByIdKecamatanAndQueryNama", DesaData.class)
				.setParameter("nama", nama)
				.setParameter("idKecamatan", idKecamatan)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertDesaDataToDesa(t))
				.collect(Collectors.toList());
	}

	private DesaData convertDesaToDesaData(Desa desa, Kecamatan kecamatan) {
		return new DesaData(desa.getId(), desa.getNama(), kecamatan.getId());
	}
	
	private Desa convertDesaDataToDesa(DesaData desaData) {
		return new Desa(desaData.getId(), desaData.getNama());
	}
	
}

	
