package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Dokumen;
import org.Sikoling.ejb.abstraction.entity.KategoriDokumen;
import org.Sikoling.ejb.abstraction.repository.IDokumenRepository;

import jakarta.persistence.EntityManager;

public class DokumenRepositoryJPA implements IDokumenRepository {

	private final EntityManager entityManager;	
	
	public DokumenRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Dokumen> getAll() {
		return entityManager.createNamedQuery("DetailDokumenPerusahaanData.findAll", DokumenData.class)
				.getResultList()
				.stream()
				.map(t -> convertDokumenDataToDokumen(t))
				.collect(Collectors.toList());
	}

	@Override
	public Dokumen save(Dokumen t) {
		DokumenData detailDokumenPerusahaanData = convertDokumenToDokumenData(t);
		entityManager.persist(detailDokumenPerusahaanData);
		entityManager.flush();
		return convertDokumenDataToDokumen(detailDokumenPerusahaanData);
	}

	@Override
	public Dokumen update(Dokumen t) {
		DokumenData detailDokumenPerusahaanData = convertDokumenToDokumenData(t);
		detailDokumenPerusahaanData = entityManager.merge(detailDokumenPerusahaanData);
		return convertDokumenDataToDokumen(detailDokumenPerusahaanData);
	}

	@Override
	public List<Dokumen> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("DetailDokumenPerusahaanData.findAll", DokumenData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertDokumenDataToDokumen(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Dokumen> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("DetailDokumenPerusahaanData.findByNama", DokumenData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertDokumenDataToDokumen(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Dokumen> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("DetailDokumenPerusahaanData.findByNama", DokumenData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertDokumenDataToDokumen(t))
				.collect(Collectors.toList());
	}
	
	private Dokumen convertDokumenDataToDokumen(DokumenData d) {
		KategoriDokumen kategoriDokumen = new KategoriDokumen(
				d.getKategori().getId(), 
				d.getKategori().getNama(), 
				d.getKategori().getParent());
		return new Dokumen(d.getId(), d.getNama(), kategoriDokumen);
	}
	
	private DokumenData convertDokumenToDokumenData(Dokumen t) {
		DokumenData dokumenData = new DokumenData();
		dokumenData.setId(t.getId());
		dokumenData.setNama(t.getNama());
		
		KategoriDokumenData kategoriDokumenData = new KategoriDokumenData();
		kategoriDokumenData.setId(t.getKategoriDokumen().getId());
		kategoriDokumenData.setNama(t.getKategoriDokumen().getNama());
		kategoriDokumenData.setParent(t.getKategoriDokumen().getParent());
		dokumenData.setKategori(kategoriDokumenData);
		
		return dokumenData;
	}
	
	@Override
	public DeleteResponse delete(String id) {
		DokumenData dokumenData = entityManager.find(DokumenData.class, id);
		entityManager.remove(dokumenData);		
		return new DeleteResponse(true, id);
	}

	@Override
	public Dokumen updateById(String id, Dokumen dokumen) {
		DokumenData updateData = convertDokumenToDokumenData(dokumen);
		DokumenData dokumenData = entityManager.find(DokumenData.class, id);
		dokumenData.setId(updateData.getId());
		dokumenData.setKategori(updateData.getKategori());
		dokumenData.setNama(updateData.getNama());
		dokumenData = entityManager.merge(dokumenData);
		return convertDokumenDataToDokumen(dokumenData);
	}

}
