package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.KategoriDokumenPerusahaan;
import org.Sikoling.ejb.abstraction.repository.IKategoriDokumenRepository;
import jakarta.persistence.EntityManager;

public class KategoriDokumenPerusahaanRepositoryJPA implements IKategoriDokumenRepository {

	private final EntityManager entityManager;	
	
	public KategoriDokumenPerusahaanRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<KategoriDokumenPerusahaan> getAll() {
			return entityManager.createNamedQuery("KategoriDokumenPerusahaanData.findAll", KategoriDokumenData.class)
					.getResultList()
					.stream()
					.map(t -> convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(t))
					.collect(Collectors.toList());
	}

	@Override
	public KategoriDokumenPerusahaan save(KategoriDokumenPerusahaan t, String t2) {
		KategoriDokumenData kategoriDokumenPerusahaanData = convertKategoriDokumenPerusahaanToKategoriDokumenPerusahaanData(t, t2);
		entityManager.persist(kategoriDokumenPerusahaanData);
		entityManager.flush();
		
		return convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(kategoriDokumenPerusahaanData);
	}

	@Override
	public KategoriDokumenPerusahaan update(KategoriDokumenPerusahaan t, String t2) {
		KategoriDokumenData kategoriDokumenPerusahaanData = convertKategoriDokumenPerusahaanToKategoriDokumenPerusahaanData(t, t2);
		kategoriDokumenPerusahaanData = entityManager.merge(kategoriDokumenPerusahaanData);
		
		return convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(kategoriDokumenPerusahaanData);
	}

	@Override
	public List<KategoriDokumenPerusahaan> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("KategoriDokumenPerusahaanData.findAll", KategoriDokumenData.class)
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
		return entityManager.createNamedQuery("KategoriDokumenPerusahaanData.findByName", KategoriDokumenData.class)
				.setParameter("nama", nama)				
				.getResultList()
				.stream()
				.map(t -> convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<KategoriDokumenPerusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("KategoriDokumenPerusahaanData.findByName", KategoriDokumenData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)			
				.getResultList()
				.stream()
				.map(t -> convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(t))
				.collect(Collectors.toList());
	}

	private KategoriDokumenPerusahaan convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(KategoriDokumenData d) {
		return new KategoriDokumenPerusahaan(d.getId(), d.getNama());
	}
	
	private KategoriDokumenData convertKategoriDokumenPerusahaanToKategoriDokumenPerusahaanData(KategoriDokumenPerusahaan t, String t2) {
		KategoriDokumenData kategoriDokumenPerusahaanData = new KategoriDokumenData();
		kategoriDokumenPerusahaanData.setId(t.getId());
		kategoriDokumenPerusahaanData.setNama(t.getNama());
		kategoriDokumenPerusahaanData.setParent(t2);
		
		return kategoriDokumenPerusahaanData;
	}
}
