package org.Sikoling.ejb.main.repository.kecamatan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.repository.IKecamatanRepository;
import jakarta.persistence.EntityManager;

public class KecamatanRepositoryJPA implements IKecamatanRepository {
	
	private final EntityManager entityManager;

	public KecamatanRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Kecamatan save(Kecamatan t) {
		KecamatanData kecamatanData = convertKecamatanToKecamatanData(t);
		entityManager.persist(kecamatanData);
		entityManager.flush();
		return convertKecamatanDataToKecamatan(kecamatanData);
	}

	@Override
	public Kecamatan update(Kecamatan t) {
		KecamatanData kecamatanData = convertKecamatanToKecamatanData(t);
		kecamatanData = entityManager.merge(kecamatanData);
		return convertKecamatanDataToKecamatan(kecamatanData);
	}

	@Override
	public List<Kecamatan> getAll() {
		return entityManager.createNamedQuery("KecamatanData.findAll", KecamatanData.class)
				.getResultList()
				.stream()
				.map(t -> convertKecamatanDataToKecamatan(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Kecamatan> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("KecamatanData.findAll", KecamatanData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKecamatanDataToKecamatan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kecamatan> getByQueryNama(String nama) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("KecamatanData.findAllByQueryNama", KecamatanData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertKecamatanDataToKecamatan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kecamatan> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama +"%";
		return entityManager.createNamedQuery("KecamatanData.findAllByQueryNama", KecamatanData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKecamatanDataToKecamatan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kecamatan> getByKabupaten(String idKabupaten) {
		return entityManager.createNamedQuery("KecamatanData.findAllByIdKabupaten", KecamatanData.class)
				.setParameter("idKabupaten", idKabupaten)
				.getResultList()
				.stream()
				.map(t -> convertKecamatanDataToKecamatan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kecamatan> getByKabupatenAndPage(String idKabupaten, Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("KecamatanData.findAllByIdKabupaten", KecamatanData.class)
				.setParameter("idKabupaten", idKabupaten)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKecamatanDataToKecamatan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kecamatan> getByKabupatenAndQueryNama(String idKabupaten, String nama) {
		return entityManager.createNamedQuery("KecamatanData.findAllByIdKabupatenAndQueryNama", KecamatanData.class)
				.setParameter("nama", nama)
				.setParameter("idKabupaten", idKabupaten)
				.getResultList()
				.stream()
				.map(t -> convertKecamatanDataToKecamatan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Kecamatan> getByKabupatenAndQueryNamaAndPage(String idKabupaten, String nama, Integer page,
			Integer pageSize) {
		return entityManager.createNamedQuery("KecamatanData.findAllByIdKabupatenAndQueryNama", KecamatanData.class)
				.setParameter("nama", nama)
				.setParameter("idKabupaten", idKabupaten)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertKecamatanDataToKecamatan(t))
				.collect(Collectors.toList());
	}

	private KecamatanData convertKecamatanToKecamatanData(Kecamatan kecamatan) {
		KecamatanData kecamatanData = new KecamatanData();
		kecamatanData.setId(kecamatan.getId());
		kecamatanData.setNama(kecamatan.getNama());
		kecamatanData.setIdKabupaten(kecamatan.getIdKabupaten());
		return kecamatanData;
	}
	
	private Kecamatan convertKecamatanDataToKecamatan(KecamatanData kecamatanData) {
		return new Kecamatan(kecamatanData.getId(), kecamatanData.getNama(), kecamatanData.getIdKabupaten());
	}
}
