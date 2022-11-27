package org.Sikoling.ejb.main.repository.pelakuusaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.repository.IKategoriPelakuUsahaRepository;
import jakarta.persistence.EntityManager;

public class KategoriPelakuUsahaRepositoryJPA implements IKategoriPelakuUsahaRepository {
	
	private final EntityManager entityManager;	

	public KategoriPelakuUsahaRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<KategoriPelakuUsaha> getAll() {
		return entityManager.createNamedQuery("KategoriPelakuUsahaData.findAll", KategoriPelakuUsahaData.class)
				.getResultList()
				.stream()
				.map(t -> convertKategoriPelakuUsahaDataToKategoriPelakuUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<KategoriPelakuUsaha> getALLBySkalaUsaha(String idSkalaUsaha) {
		return entityManager.createNamedQuery("KategoriPelakuUsahaData.findAllBySkalaUsaha", KategoriPelakuUsahaData.class)
				.setParameter("idSkalaUsaha", idSkalaUsaha)
				.getResultList()
				.stream()
				.map(t -> convertKategoriPelakuUsahaDataToKategoriPelakuUsaha(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public KategoriPelakuUsaha save(KategoriPelakuUsaha t) {
		KategoriPelakuUsahaData kategoriPelakuUsahaData = convertKategoriPelakuUsahaToKategoriPelakuUsahaData(t);
		entityManager.persist(kategoriPelakuUsahaData);
		entityManager.flush();
		return convertKategoriPelakuUsahaDataToKategoriPelakuUsaha(kategoriPelakuUsahaData);
	}

	@Override
	public KategoriPelakuUsaha update(KategoriPelakuUsaha t) {
		KategoriPelakuUsahaData kategoriPelakuUsahaData = convertKategoriPelakuUsahaToKategoriPelakuUsahaData(t);
		kategoriPelakuUsahaData = entityManager.merge(kategoriPelakuUsahaData);
		return convertKategoriPelakuUsahaDataToKategoriPelakuUsaha(kategoriPelakuUsahaData);
	}

	@Override
	public DeleteResponse delete(String id) {
		KategoriPelakuUsahaData kategoriPelakuUsahaData = entityManager.find(KategoriPelakuUsahaData.class, id);
		entityManager.remove(kategoriPelakuUsahaData);
		
		return new DeleteResponse(true, id);
	}

	@Override
	public KategoriPelakuUsaha updateById(String id, KategoriPelakuUsaha kategoriPelakuUsaha) {
		String idBaru = kategoriPelakuUsaha.getId();
		KategoriPelakuUsahaData kategoriPelakuUsahaData = convertKategoriPelakuUsahaToKategoriPelakuUsahaData(kategoriPelakuUsaha);
		kategoriPelakuUsahaData.setId(id);
		kategoriPelakuUsahaData = entityManager.merge(kategoriPelakuUsahaData);
		
		if(!idBaru.equals(id)) {
			kategoriPelakuUsahaData.setId(idBaru);
			entityManager.flush();
		}
		
		return null;
	}
	
	@Override
	public List<KategoriPelakuUsaha> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("KategoriPelakuUsahaData.findAll", KategoriPelakuUsahaData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKategoriPelakuUsahaDataToKategoriPelakuUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<KategoriPelakuUsaha> getByNama(String nama) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("KategoriPelakuUsahaData.findByNama", KategoriPelakuUsahaData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertKategoriPelakuUsahaDataToKategoriPelakuUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<KategoriPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("KategoriPelakuUsahaData.findAll", KategoriPelakuUsahaData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertKategoriPelakuUsahaDataToKategoriPelakuUsaha(t))
				.collect(Collectors.toList());
	}
	
	private KategoriPelakuUsaha convertKategoriPelakuUsahaDataToKategoriPelakuUsaha(KategoriPelakuUsahaData d) {
		return new KategoriPelakuUsaha(d.getId(), d.getNama());
	}
	
	private KategoriPelakuUsahaData convertKategoriPelakuUsahaToKategoriPelakuUsahaData(KategoriPelakuUsaha t) {
		KategoriPelakuUsahaData kategoriPelakuUsahaData = new KategoriPelakuUsahaData();
		kategoriPelakuUsahaData.setId(t.getId());
		kategoriPelakuUsahaData.setNama(t.getNama());
		
		return kategoriPelakuUsahaData;
	}

		
}
