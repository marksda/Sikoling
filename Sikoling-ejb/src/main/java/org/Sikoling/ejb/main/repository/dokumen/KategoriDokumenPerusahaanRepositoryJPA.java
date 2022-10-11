package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.KategoriDokumenPerusahaan;
import org.Sikoling.ejb.abstraction.repository.IKategoriDokumenPerusahaanRepository;
import jakarta.persistence.EntityManager;

public class KategoriDokumenPerusahaanRepositoryJPA implements IKategoriDokumenPerusahaanRepository {

	private final EntityManager entityManager;	
	
	public KategoriDokumenPerusahaanRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<KategoriDokumenPerusahaan> getAll() {
			return entityManager.createNamedQuery("KategoriDokumenPerusahaanData.findAll", KategoriDokumenPerusahaanData.class)
					.getResultList()
					.stream()
					.map(t -> convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(t))
					.collect(Collectors.toList());
	}

	@Override
	public KategoriDokumenPerusahaan save(KategoriDokumenPerusahaan t) {
		KategoriDokumenPerusahaanData kategoriDokumenPerusahaanData = convertKategoriDokumenPerusahaanToKategoriDokumenPerusahaanData(t);
		entityManager.persist(kategoriDokumenPerusahaanData);
		entityManager.flush();
		
		return convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(kategoriDokumenPerusahaanData);
	}

	@Override
	public KategoriDokumenPerusahaan update(KategoriDokumenPerusahaan t) {
		KategoriDokumenPerusahaanData kategoriDokumenPerusahaanData = convertKategoriDokumenPerusahaanToKategoriDokumenPerusahaanData(t);
		kategoriDokumenPerusahaanData = entityManager.merge(kategoriDokumenPerusahaanData);
		
		return convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(kategoriDokumenPerusahaanData);
	}

	@Override
	public List<KategoriDokumenPerusahaan> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("KategoriDokumenPerusahaanData.findAll", KategoriDokumenPerusahaanData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<KategoriDokumenPerusahaan> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("KategoriDokumenPerusahaanData.findByName", KategoriDokumenPerusahaanData.class)
				.setParameter("nama", nama)				
				.getResultList()
				.stream()
				.map(t -> convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<KategoriDokumenPerusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("KategoriDokumenPerusahaanData.findByName", KategoriDokumenPerusahaanData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)			
				.getResultList()
				.stream()
				.map(t -> convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(t))
				.collect(Collectors.toList());
	}

	private KategoriDokumenPerusahaan convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(KategoriDokumenPerusahaanData d) {
		return new KategoriDokumenPerusahaan(d.getId(), d.getNama(), d.getParent());
	}
	
	private KategoriDokumenPerusahaanData convertKategoriDokumenPerusahaanToKategoriDokumenPerusahaanData(KategoriDokumenPerusahaan t) {
		KategoriDokumenPerusahaanData kategoriDokumenPerusahaanData = new KategoriDokumenPerusahaanData();
		kategoriDokumenPerusahaanData.setId(t.getId());
		kategoriDokumenPerusahaanData.setNama(t.getNama());
		kategoriDokumenPerusahaanData.setParent(t.getIdParent());
		
		return kategoriDokumenPerusahaanData;
	}
}
