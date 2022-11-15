package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.KategoriDokumen;
import org.Sikoling.ejb.abstraction.repository.IKategoriDokumenRepository;
import jakarta.persistence.EntityManager;

public class KategoriDokumenRepositoryJPA implements IKategoriDokumenRepository {

	private final EntityManager entityManager;	
	
	public KategoriDokumenRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<KategoriDokumen> getAll() {
			return entityManager.createNamedQuery("KategoriDokumenPerusahaanData.findAll", KategoriDokumenData.class)
					.getResultList()
					.stream()
					.map(t -> convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(t))
					.collect(Collectors.toList());
	}

	@Override
	public KategoriDokumen save(KategoriDokumen t) {
		KategoriDokumenData kategoriDokumenPerusahaanData = convertKategoriDokumenPerusahaanToKategoriDokumenPerusahaanData(t);
		entityManager.persist(kategoriDokumenPerusahaanData);
		entityManager.flush();
		
		return convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(kategoriDokumenPerusahaanData);
	}

	@Override
	public KategoriDokumen update(KategoriDokumen t) {
		KategoriDokumenData kategoriDokumenPerusahaanData = convertKategoriDokumenPerusahaanToKategoriDokumenPerusahaanData(t);
		kategoriDokumenPerusahaanData = entityManager.merge(kategoriDokumenPerusahaanData);
		
		return convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(kategoriDokumenPerusahaanData);
	}

	@Override
	public List<KategoriDokumen> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("KategoriDokumenPerusahaanData.findAll", KategoriDokumenData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<KategoriDokumen> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("KategoriDokumenPerusahaanData.findByName", KategoriDokumenData.class)
				.setParameter("nama", nama)				
				.getResultList()
				.stream()
				.map(t -> convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<KategoriDokumen> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
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

	private KategoriDokumen convertKategoriDokumenPerusahaanDataToKategoriDokumenPerusahaan(KategoriDokumenData d) {
		return new KategoriDokumen(d.getId(), d.getNama(), d.getParent());
	}
	
	private KategoriDokumenData convertKategoriDokumenPerusahaanToKategoriDokumenPerusahaanData(KategoriDokumen t) {
		KategoriDokumenData kategoriDokumenPerusahaanData = new KategoriDokumenData();
		kategoriDokumenPerusahaanData.setId(t.getId());
		kategoriDokumenPerusahaanData.setNama(t.getNama());
		kategoriDokumenPerusahaanData.setParent(t.getParent());
		
		return kategoriDokumenPerusahaanData;
	}
}
