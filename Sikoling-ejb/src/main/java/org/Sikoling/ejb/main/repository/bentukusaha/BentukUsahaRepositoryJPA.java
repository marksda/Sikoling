package org.Sikoling.ejb.main.repository.bentukusaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.BentukUsaha;
import org.Sikoling.ejb.abstraction.repository.IBentukUsahaRepository;
import org.Sikoling.ejb.main.repository.kelompokbentukusaha.KelompokBentukUsahaData;

import jakarta.persistence.EntityManager;

public class BentukUsahaRepositoryJPA implements IBentukUsahaRepository {
	
	private final EntityManager entityManager;	

	public BentukUsahaRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public BentukUsaha save(BentukUsaha t) {
		BentukUsahaData bentukUsahaData = convertBentukUsahaToBentukUsahaData(t);
		entityManager.persist(bentukUsahaData);
		entityManager.flush();
		return convertBentukUsahaDataToBentukUsaha(bentukUsahaData);
	}

	@Override
	public BentukUsaha update(BentukUsaha t) {
		BentukUsahaData bentukUsahaData = convertBentukUsahaToBentukUsahaData(t);
		bentukUsahaData = entityManager.merge(bentukUsahaData);
		return convertBentukUsahaDataToBentukUsaha(bentukUsahaData);
	}

	@Override
	public List<BentukUsaha> getAll() {
		return entityManager.createNamedQuery("BentukUsahaData.findAll", BentukUsahaData.class)
				.getResultList()
				.stream()
				.map(t -> convertBentukUsahaDataToBentukUsaha(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<BentukUsaha> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("BentukUsahaData.findAll", BentukUsahaData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertBentukUsahaDataToBentukUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<BentukUsaha> getByQueryNama(String nama) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("BentukUsahaData.findAllByQueryNama", BentukUsahaData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertBentukUsahaDataToBentukUsaha(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<BentukUsaha> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("BentukUsahaData.findAllByQueryNama", BentukUsahaData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertBentukUsahaDataToBentukUsaha(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<BentukUsaha> getByKelompokBentukUsaha(String idKelompokBentukUsaha) {
		return entityManager.createNamedQuery("BentukUsahaData.findAllByKelompokUsaha", BentukUsahaData.class)
				.setParameter("idKelompok", idKelompokBentukUsaha)
				.getResultList()
				.stream()
				.map(t -> convertBentukUsahaDataToBentukUsaha(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<BentukUsaha> getByKelompokBentukUsahaAndPage(String idKelompokBentukUsaha, Integer page,
			Integer pageSize) {
		return entityManager.createNamedQuery("BentukUsahaData.findAllByKelompokUsaha", BentukUsahaData.class)
				.setParameter("idKelompok", idKelompokBentukUsaha)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertBentukUsahaDataToBentukUsaha(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<BentukUsaha> getByKelompokBentukUsahaAndNama(String idKelompokBentukUsaha, String nama) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("BentukUsahaData.findAllByKelompokUsaha", BentukUsahaData.class)
				.setParameter("nama", nama)
				.setParameter("idKelompok", idKelompokBentukUsaha)
				.getResultList()
				.stream()
				.map(t -> convertBentukUsahaDataToBentukUsaha(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<BentukUsaha> getByKelompokBentukUsahaAndNamaAndPage(String idKelompokBentukUsaha, String nama,
			Integer page, Integer pageSize) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("BentukUsahaData.findAllByKelompokUsaha", BentukUsahaData.class)
				.setParameter("nama", nama)
				.setParameter("idKelompok", idKelompokBentukUsaha)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertBentukUsahaDataToBentukUsaha(t))
				.collect(Collectors.toList());
	}

	private BentukUsahaData convertBentukUsahaToBentukUsahaData(BentukUsaha t) {
		BentukUsahaData bentukUsahaData = new BentukUsahaData();
		bentukUsahaData.setId(t.getId());
		bentukUsahaData.setNama(t.getNama());
		bentukUsahaData.setSingkatan(t.getSingkatan());
		KelompokBentukUsahaData kelompokBentukUsahaData = new KelompokBentukUsahaData();
		kelompokBentukUsahaData.setId(t.getIdKelompok());
		bentukUsahaData.setKelompokBentukUsaha(kelompokBentukUsahaData);
		
		return bentukUsahaData;
	}
	
	private BentukUsaha convertBentukUsahaDataToBentukUsaha(BentukUsahaData d) {
		return new BentukUsaha(d.getId(), d.getNama(), d.getSingkatan(), d.getKelompokBentukUsaha().getId());
	}

}
