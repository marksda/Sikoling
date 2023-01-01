package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli2020;
import org.Sikoling.ejb.abstraction.repository.IKbliRepository;

import jakarta.persistence.EntityManager;

public class Kbli2020RepositoryJPA implements IKbliRepository {
	
	private final EntityManager entityManager;

	public Kbli2020RepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Kbli2020> getAll() {
		return entityManager.createNamedQuery("Kbli2020Data.findAll", Kbli2020Data.class)
				.getResultList()
				.stream()
				.map(t -> convertKbliDataToKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public Kbli2020 save(Kbli2020 t) {
		Kbli2020Data kbliData = convertKbliToKbliData(t);
		entityManager.persist(kbliData);
		entityManager.flush();
		return convertKbliDataToKbli(kbliData);
	}

	@Override
	public Kbli2020 update(Kbli2020 t) {
		Kbli2020Data kbliData = convertKbliToKbliData(t);
		kbliData = entityManager.merge(kbliData);
		return convertKbliDataToKbli(kbliData);
	}

	@Override
	public DeleteResponse delete(String kode) {
		Kbli2020Data kbliData = entityManager.find(Kbli2020Data.class, kode);
		entityManager.remove(kbliData);
		return new DeleteResponse(true, kode);
	}

	@Override
	public Kbli2020 updateById(String id, Kbli2020 kbli) {
		Kbli2020Data updateData = convertKbliToKbliData(kbli);
		Kbli2020Data kbliData = entityManager.find(Kbli2020Data.class, id);
		kbliData.setId(updateData.getId());
		kbliData.setNama(updateData.getNama());
		kbliData.setKategori(updateData.getKategori());
		
		return convertKbliDataToKbli(kbliData);
	}

	@Override
	public List<Kbli2020> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("Kbli2020Data.findAll", Kbli2020Data.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKbliDataToKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kbli2020> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("Kbli2020Data.findByNama", Kbli2020Data.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertKbliDataToKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kbli2020> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("Kbli2020Data.findByNama", Kbli2020Data.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKbliDataToKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kbli2020> getByKode(String kode) {
		kode = "%" + kode + "%";
		return entityManager.createNamedQuery("Kbli2020Data.findByKode", Kbli2020Data.class)
				.setParameter("kode", kode)
				.getResultList()
				.stream()
				.map(t -> convertKbliDataToKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kbli2020> getByKodeAndPage(String kode, Integer page, Integer pageSize) {
		kode = "%" + kode + "%";
		return entityManager.createNamedQuery("Kbli2020Data.findByKode", Kbli2020Data.class)
				.setParameter("kode", kode)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKbliDataToKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kbli2020> getByKategori(String kategori) {
		return entityManager.createNamedQuery("Kbli2020Data.findByKategori", Kbli2020Data.class)
				.setParameter("kode", kategori)
				.getResultList()
				.stream()
				.map(t -> convertKbliDataToKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kbli2020> getByKategoriAndPage(String kategori, Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("Kbli2020Data.findByKategori", Kbli2020Data.class)
				.setParameter("kode", kategori)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKbliDataToKbli(t))
				.collect(Collectors.toList());
	}

	private Kbli2020 convertKbliDataToKbli(Kbli2020Data d) {
		return new Kbli2020(d.getId(), d.getNama(), d.getKategori());
	}
	
	private Kbli2020Data convertKbliToKbliData(Kbli2020 t) {
		Kbli2020Data kbliData = new Kbli2020Data();
		kbliData.setId(t.getKode());
		kbliData.setNama(t.getNama());
		kbliData.setKategori(t.getKategori());
		
		return kbliData;
	}
}
