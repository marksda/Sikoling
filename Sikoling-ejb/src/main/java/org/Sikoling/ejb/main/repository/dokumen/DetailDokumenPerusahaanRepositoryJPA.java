package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Dokumen;
import org.Sikoling.ejb.abstraction.entity.KategoriDokumenPerusahaan;
import org.Sikoling.ejb.abstraction.repository.IDetailDokumenPerusahaanRepository;

import jakarta.persistence.EntityManager;

public class DetailDokumenPerusahaanRepositoryJPA implements IDetailDokumenPerusahaanRepository {

	private final EntityManager entityManager;	
	
	public DetailDokumenPerusahaanRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Dokumen> getAll() {
		return entityManager.createNamedQuery("DetailDokumenPerusahaanData.findAll", DetailDokumenPerusahaanData.class)
				.getResultList()
				.stream()
				.map(t -> convertDetailDokumenPerusahaanDataToDokumen(t))
				.collect(Collectors.toList());
	}

	@Override
	public Dokumen save(Dokumen t) {
		DetailDokumenPerusahaanData detailDokumenPerusahaanData = convertDokumenToDetailDokumenPerusahaanData(t);
		entityManager.persist(detailDokumenPerusahaanData);
		entityManager.flush();
		return convertDetailDokumenPerusahaanDataToDokumen(detailDokumenPerusahaanData);
	}

	@Override
	public Dokumen update(Dokumen t) {
		DetailDokumenPerusahaanData detailDokumenPerusahaanData = convertDokumenToDetailDokumenPerusahaanData(t);
		detailDokumenPerusahaanData = entityManager.merge(detailDokumenPerusahaanData);
		return convertDetailDokumenPerusahaanDataToDokumen(detailDokumenPerusahaanData);
	}

	@Override
	public List<Dokumen> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("DetailDokumenPerusahaanData.findAll", DetailDokumenPerusahaanData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertDetailDokumenPerusahaanDataToDokumen(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Dokumen> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("DetailDokumenPerusahaanData.findByNama", DetailDokumenPerusahaanData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertDetailDokumenPerusahaanDataToDokumen(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Dokumen> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("DetailDokumenPerusahaanData.findByNama", DetailDokumenPerusahaanData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertDetailDokumenPerusahaanDataToDokumen(t))
				.collect(Collectors.toList());
	}
	
	private Dokumen convertDetailDokumenPerusahaanDataToDokumen(DetailDokumenPerusahaanData d) {
		KategoriDokumenPerusahaan kategoriDokumenPerusahaan = 
				new KategoriDokumenPerusahaan(d.getKategori().getId(), d.getKategori().getNama());
		return new Dokumen(d.getId(), d.getNama(), kategoriDokumenPerusahaan);
	}
	
	private DetailDokumenPerusahaanData convertDokumenToDetailDokumenPerusahaanData(Dokumen t) {
		DetailDokumenPerusahaanData detailDokumenPerusahaanData = new DetailDokumenPerusahaanData();
		detailDokumenPerusahaanData.setId(t.getId());
		detailDokumenPerusahaanData.setNama(t.getNama());
		
		KategoriDokumenPerusahaanData kategoriDokumenPerusahaanData = new KategoriDokumenPerusahaanData();
		kategoriDokumenPerusahaanData.setId(t.getKategoriDokumenPerusahaan().getId());
		kategoriDokumenPerusahaanData.setNama(t.getKategoriDokumenPerusahaan().getNama());
		detailDokumenPerusahaanData.setKategori(kategoriDokumenPerusahaanData);
		
		return detailDokumenPerusahaanData;
	}

}
