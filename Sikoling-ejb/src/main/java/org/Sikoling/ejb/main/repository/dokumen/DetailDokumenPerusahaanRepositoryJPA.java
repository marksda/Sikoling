package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DetailDokumenPerusahaan;
import org.Sikoling.ejb.abstraction.entity.KategoriDokumenPerusahaan;
import org.Sikoling.ejb.abstraction.repository.IDetailDokumenPerusahaanRepository;

import jakarta.persistence.EntityManager;

public class DetailDokumenPerusahaanRepositoryJPA implements IDetailDokumenPerusahaanRepository {

	private final EntityManager entityManager;	
	
	public DetailDokumenPerusahaanRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<DetailDokumenPerusahaan> getAll() {
		return entityManager.createNamedQuery("DetailDokumenPerusahaanData.findAll", DetailDokumenPerusahaanData.class)
				.getResultList()
				.stream()
				.map(t -> convertDetailDokumenPerusahaanDataToDetailDokumenPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public DetailDokumenPerusahaan save(DetailDokumenPerusahaan t) {
		DetailDokumenPerusahaanData detailDokumenPerusahaanData = convertDetailDokumenPerusahaanToDetailDokumenPerusahaanData(t);
		entityManager.persist(detailDokumenPerusahaanData);
		entityManager.flush();
		return convertDetailDokumenPerusahaanDataToDetailDokumenPerusahaan(detailDokumenPerusahaanData);
	}

	@Override
	public DetailDokumenPerusahaan update(DetailDokumenPerusahaan t) {
		DetailDokumenPerusahaanData detailDokumenPerusahaanData = convertDetailDokumenPerusahaanToDetailDokumenPerusahaanData(t);
		detailDokumenPerusahaanData = entityManager.merge(detailDokumenPerusahaanData);
		return convertDetailDokumenPerusahaanDataToDetailDokumenPerusahaan(detailDokumenPerusahaanData);
	}

	@Override
	public List<DetailDokumenPerusahaan> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("DetailDokumenPerusahaanData.findAll", DetailDokumenPerusahaanData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertDetailDokumenPerusahaanDataToDetailDokumenPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<DetailDokumenPerusahaan> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("DetailDokumenPerusahaanData.findByNama", DetailDokumenPerusahaanData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertDetailDokumenPerusahaanDataToDetailDokumenPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<DetailDokumenPerusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("DetailDokumenPerusahaanData.findByNama", DetailDokumenPerusahaanData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertDetailDokumenPerusahaanDataToDetailDokumenPerusahaan(t))
				.collect(Collectors.toList());
	}
	
	private DetailDokumenPerusahaan convertDetailDokumenPerusahaanDataToDetailDokumenPerusahaan(DetailDokumenPerusahaanData d) {
		return new DetailDokumenPerusahaan(
				d.getId(), 
				d.getNama(), 
				new KategoriDokumenPerusahaan(
						d.getKategori().getId(), 
						d.getKategori().getNama(), 
						d.getKategori().getParent())
				);
	}
	
	private DetailDokumenPerusahaanData convertDetailDokumenPerusahaanToDetailDokumenPerusahaanData(DetailDokumenPerusahaan t) {
		DetailDokumenPerusahaanData detailDokumenPerusahaanData = new DetailDokumenPerusahaanData();
		detailDokumenPerusahaanData.setId(t.getId());
		detailDokumenPerusahaanData.setNama(t.getNama());
		
		KategoriDokumenPerusahaanData kategoriDokumenPerusahaanData = new KategoriDokumenPerusahaanData();
		kategoriDokumenPerusahaanData.setId(t.getKategoriDokumenPerusahaan().getId());
		kategoriDokumenPerusahaanData.setNama(t.getKategoriDokumenPerusahaan().getNama());
		kategoriDokumenPerusahaanData.setParent(t.getKategoriDokumenPerusahaan().getIdParent());
		detailDokumenPerusahaanData.setKategori(kategoriDokumenPerusahaanData);
		
		return detailDokumenPerusahaanData;
	}

}
