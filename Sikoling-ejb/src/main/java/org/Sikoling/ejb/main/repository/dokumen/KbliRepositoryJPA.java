package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Kbli;
import org.Sikoling.ejb.abstraction.repository.IKbliRepository;

import jakarta.persistence.EntityManager;

public class KbliRepositoryJPA implements IKbliRepository {
	
	private final EntityManager entityManager;

	public KbliRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Kbli> getAll() {
		return entityManager.createNamedQuery("KbliData.findAll", KbliData.class)
				.getResultList()
				.stream()
				.map(t -> convertKbliDataToKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public Kbli save(Kbli t) {
		KbliData kbliData = convertKbliToKbliData(t);
		entityManager.persist(kbliData);
		entityManager.flush();
		return convertKbliDataToKbli(kbliData);
	}

	@Override
	public Kbli update(Kbli t) {
		KbliData kbliData = convertKbliToKbliData(t);
		kbliData = entityManager.merge(kbliData);
		return convertKbliDataToKbli(kbliData);
	}

	@Override
	public DeleteResponse delete(String kode) {
		KbliData kbliData = entityManager.find(KbliData.class, kode);
		entityManager.remove(kbliData);
		return new DeleteResponse(true, kode);
	}

	@Override
	public Kbli updateById(String id, Kbli kbli) {
		KbliData updateData = convertKbliToKbliData(kbli);
		KbliData kbliData = entityManager.find(KbliData.class, id);
		kbliData.setId(updateData.getId());
		kbliData.setNama(updateData.getNama());
		kbliData.setKategori(updateData.getKategori());
		kbliData = entityManager.merge(kbliData);
		
		return convertKbliDataToKbli(kbliData);
	}

	@Override
	public List<Kbli> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("KbliData.findAll", KbliData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKbliDataToKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kbli> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("KbliData.findByNama", KbliData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertKbliDataToKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kbli> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("KbliData.findByNama", KbliData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKbliDataToKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kbli> getByKode(String kode) {
		kode = "%" + kode + "%";
		return entityManager.createNamedQuery("KbliData.findByKode", KbliData.class)
				.setParameter("kode", kode)
				.getResultList()
				.stream()
				.map(t -> convertKbliDataToKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kbli> getByKodeAndPage(String kode, Integer page, Integer pageSize) {
		kode = "%" + kode + "%";
		return entityManager.createNamedQuery("KbliData.findByKode", KbliData.class)
				.setParameter("kode", kode)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKbliDataToKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kbli> getByKategori(String kategori) {
		return entityManager.createNamedQuery("KbliData.findByKategori", KbliData.class)
				.setParameter("kode", kategori)
				.getResultList()
				.stream()
				.map(t -> convertKbliDataToKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kbli> getByKategoriAndPage(String kategori, Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("KbliData.findByKategori", KbliData.class)
				.setParameter("kode", kategori)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKbliDataToKbli(t))
				.collect(Collectors.toList());
	}

	private Kbli convertKbliDataToKbli(KbliData d) {
		return new Kbli(d.getId(), d.getNama(), d.getKategori());
	}
	
	private KbliData convertKbliToKbliData(Kbli t) {
		KbliData kbliData = new KbliData();
		kbliData.setId(t.getKode());
		kbliData.setNama(t.getNama());
		kbliData.setKategori(t.getKategori());
		
		return kbliData;
	}
}
