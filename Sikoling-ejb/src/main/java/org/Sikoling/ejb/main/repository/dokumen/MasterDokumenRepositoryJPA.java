package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.KategoriDokumen;
import org.Sikoling.ejb.abstraction.repository.IMasterDokumenRepository;

import jakarta.persistence.EntityManager;

public class MasterDokumenRepositoryJPA implements IMasterDokumenRepository {

	private final EntityManager entityManager;	
	
	public MasterDokumenRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<String> getAll() {
		return entityManager.createNamedQuery("DetailDokumenPerusahaanData.findAll", MasterDokumenData.class)
				.getResultList()
				.stream()
				.map(t -> convertDokumenDataToDokumen(t))
				.collect(Collectors.toList());
	}

	@Override
	public String save(String t) {
		MasterDokumenData detailDokumenPerusahaanData = convertDokumenToDokumenData(t);
		entityManager.persist(detailDokumenPerusahaanData);
		entityManager.flush();
		return convertDokumenDataToDokumen(detailDokumenPerusahaanData);
	}

	@Override
	public String update(String t) {
		MasterDokumenData detailDokumenPerusahaanData = convertDokumenToDokumenData(t);
		detailDokumenPerusahaanData = entityManager.merge(detailDokumenPerusahaanData);
		return convertDokumenDataToDokumen(detailDokumenPerusahaanData);
	}

	@Override
	public List<String> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("DetailDokumenPerusahaanData.findAll", MasterDokumenData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertDokumenDataToDokumen(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<String> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("DetailDokumenPerusahaanData.findByNama", MasterDokumenData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertDokumenDataToDokumen(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<String> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("DetailDokumenPerusahaanData.findByNama", MasterDokumenData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertDokumenDataToDokumen(t))
				.collect(Collectors.toList());
	}
	
	private String convertDokumenDataToDokumen(MasterDokumenData d) {
		KategoriDokumen kategoriDokumen = new KategoriDokumen(
				d.getKategoriDokumenData().getId(), 
				d.getKategoriDokumenData().getNama(), 
				d.getKategoriDokumenData().getParent());
		return new String(d.getId(), d.getNama(), kategoriDokumen);
	}
	
	private MasterDokumenData convertDokumenToDokumenData(String t) {
		MasterDokumenData dokumenData = new MasterDokumenData();
		dokumenData.setId(t.getId());
		dokumenData.setNama(t.getNama());
		
		KategoriDokumenData kategoriDokumenData = new KategoriDokumenData();
		kategoriDokumenData.setId(t.getKategoriDokumen().getId());
		kategoriDokumenData.setNama(t.getKategoriDokumen().getNama());
		kategoriDokumenData.setParent(t.getKategoriDokumen().getParent());
		dokumenData.setKategoriDokumenData(kategoriDokumenData);
		
		return dokumenData;
	}
	
	@Override
	public DeleteResponse delete(String id) {
		MasterDokumenData dokumenData = entityManager.find(MasterDokumenData.class, id);
		entityManager.remove(dokumenData);
		return new DeleteResponse(true, id);
	}

	@Override
	public String updateById(String id, String dokumen) {
		MasterDokumenData updateData = convertDokumenToDokumenData(dokumen);
		MasterDokumenData dokumenData = entityManager.find(MasterDokumenData.class, id);
		dokumenData.setId(updateData.getId());
		dokumenData.setKategoriDokumenData(updateData.getKategoriDokumenData());
		dokumenData.setNama(updateData.getNama());
		dokumenData = entityManager.merge(dokumenData);
		return convertDokumenDataToDokumen(dokumenData);
	}

}
