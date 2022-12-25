package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;
import org.Sikoling.ejb.abstraction.repository.IMasterDokumenRepository;

import jakarta.persistence.EntityManager;

public class MasterDokumenRepositoryJPA implements IMasterDokumenRepository {

	private final EntityManager entityManager;	
	
	public MasterDokumenRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Dokumen> getAll() {
		return entityManager.createNamedQuery("MasterDokumenData.findAll", MasterDokumenData.class)
				.getResultList()
				.stream()
				.map(t -> convertDokumenDataToDokumen(t))
				.collect(Collectors.toList());
	}

	@Override
	public Dokumen save(Dokumen t) {
		MasterDokumenData detailDokumenPerusahaanData = convertDokumenToDokumenData(t);
		entityManager.persist(detailDokumenPerusahaanData);
		entityManager.flush();
		return convertDokumenDataToDokumen(detailDokumenPerusahaanData);
	}

	@Override
	public Dokumen update(Dokumen t) {
		MasterDokumenData detailDokumenPerusahaanData = convertDokumenToDokumenData(t);
		detailDokumenPerusahaanData = entityManager.merge(detailDokumenPerusahaanData);
		return convertDokumenDataToDokumen(detailDokumenPerusahaanData);
	}

	@Override
	public List<Dokumen> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("MasterDokumenData.findAll", MasterDokumenData.class)
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
		return entityManager.createNamedQuery("MasterDokumenData.findByNama", MasterDokumenData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertDokumenDataToDokumen(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Dokumen> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("MasterDokumenData.findByNama", MasterDokumenData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertDokumenDataToDokumen(t))
				.collect(Collectors.toList());
	}
	
	private Dokumen convertDokumenDataToDokumen(MasterDokumenData d) {
		KategoriDokumenData kategoriDokumenData = d.getKategoriDokumenData();
		KategoriDokumen kategoriDokumen = new KategoriDokumen(
												kategoriDokumenData.getId(), 
												kategoriDokumenData.getNama(), 
												kategoriDokumenData.getParent());
		return new Dokumen(d.getId(), d.getNama(), kategoriDokumen, null);
	}
	
	private MasterDokumenData convertDokumenToDokumenData(Dokumen t) {
		MasterDokumenData dokumenData = new MasterDokumenData();
		dokumenData.setId(t.getId());
		dokumenData.setNama(t.getNama());
		
		KategoriDokumenData kategoriDokumenData = new KategoriDokumenData();
		kategoriDokumenData.setId(t.getKategoriDokumen().getId());
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
	public Dokumen updateById(String id, Dokumen dokumen) {
		String idBaru = dokumen.getId();
		MasterDokumenData masterDokumenData = convertDokumenToDokumenData(dokumen);
		masterDokumenData.setId(id);
		masterDokumenData = entityManager.merge(masterDokumenData);
		if(!idBaru.equals(id)) {
			masterDokumenData.setId(idBaru);
		}
		
		return convertDokumenDataToDokumen(masterDokumenData);
	}

}
