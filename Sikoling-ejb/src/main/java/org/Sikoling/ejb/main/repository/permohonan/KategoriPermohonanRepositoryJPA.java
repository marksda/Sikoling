package org.Sikoling.ejb.main.repository.permohonan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.KategoriPermohonan;
import org.Sikoling.ejb.abstraction.repository.IKategoriPermohonanRepository;
import jakarta.persistence.EntityManager;

public class KategoriPermohonanRepositoryJPA implements IKategoriPermohonanRepository {

	private final EntityManager entityManager;
	
	public KategoriPermohonanRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<KategoriPermohonan> getAll() {
		return entityManager.createNamedQuery("KategoriPermohonanData.findAll", KategoriPermohonanData.class)
				.getResultList()
				.stream()
				.map(d -> convertKategoriPermohonanDataToKategoriPermohonan(d))
				.collect(Collectors.toList());
	}

	@Override
	public KategoriPermohonan save(KategoriPermohonan t) {
		KategoriPermohonanData kategoriPermohonanData = convertKategoriPermohonanToKategoriPermohonanData(t);
		entityManager.persist(kategoriPermohonanData);
		entityManager.flush();
		return convertKategoriPermohonanDataToKategoriPermohonan(kategoriPermohonanData);
	}

	@Override
	public KategoriPermohonan update(KategoriPermohonan t) {
		KategoriPermohonanData kategoriPermohonanData = convertKategoriPermohonanToKategoriPermohonanData(t);
		kategoriPermohonanData = entityManager.merge(kategoriPermohonanData);
		
		return convertKategoriPermohonanDataToKategoriPermohonan(kategoriPermohonanData);
	}

	@Override
	public List<KategoriPermohonan> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("KategoriPermohonanData.findByQueryNama", KategoriPermohonanData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertKategoriPermohonanDataToKategoriPermohonan(t))
				.collect(Collectors.toList());
	}

	private KategoriPermohonan convertKategoriPermohonanDataToKategoriPermohonan(KategoriPermohonanData d) {
		return new KategoriPermohonan(d.getId(), d.getNama());		
	}
	
	private KategoriPermohonanData convertKategoriPermohonanToKategoriPermohonanData(KategoriPermohonan t) {
		KategoriPermohonanData kategoriPermohonanData = new KategoriPermohonanData();
		kategoriPermohonanData.setId(t.getId());
		kategoriPermohonanData.setNama(t.getNama());
		
		return kategoriPermohonanData;		
	}
	
	@Override
	public DeleteResponse delete(String id) {
		KategoriPermohonanData kategoriPermohonanData = entityManager.find(KategoriPermohonanData.class, id);
		entityManager.remove(kategoriPermohonanData);
		return new DeleteResponse(true, id);
	}
	
}
