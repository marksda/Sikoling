package org.Sikoling.ejb.main.repository.kategoriproduk;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.KategoriProduk;
import org.Sikoling.ejb.abstraction.repository.IKategoriProdukRepository;

import jakarta.persistence.EntityManager;

public class KategoriProdukRepositoryJPA implements IKategoriProdukRepository {
	
	private final EntityManager entityManager;	

	public KategoriProdukRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<KategoriProduk> getAll() {
		return entityManager.createNamedQuery("KategoriProdukData.findAll", KategoriProdukData.class)
				.getResultList()
				.stream()
				.map(d -> convertKategoriProdukDataToKategoriProduk(d))
				.collect(Collectors.toList());
	}

	@Override
	public KategoriProduk save(KategoriProduk t) {
		KategoriProdukData kategoriProdukData = convertKategoriProdukToKategoriProdukData(t);
		entityManager.persist(kategoriProdukData);
		entityManager.flush();
		
		return convertKategoriProdukDataToKategoriProduk(kategoriProdukData);
	}

	@Override
	public KategoriProduk update(KategoriProduk t) {
		KategoriProdukData kategoriProdukData = convertKategoriProdukToKategoriProdukData(t);
		kategoriProdukData = entityManager.merge(kategoriProdukData);
		
		return convertKategoriProdukDataToKategoriProduk(kategoriProdukData);
	}

	@Override
	public List<KategoriProduk> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("KategoriProdukData.findAll", KategoriProdukData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> convertKategoriProdukDataToKategoriProduk(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<KategoriProduk> getByQueryNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("KategoriProdukData.findByQueryNama", KategoriProdukData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(d -> convertKategoriProdukDataToKategoriProduk(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<KategoriProduk> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("KategoriProdukData.findByQueryNama", KategoriProdukData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> convertKategoriProdukDataToKategoriProduk(d))
				.collect(Collectors.toList());
	}

	private KategoriProdukData convertKategoriProdukToKategoriProdukData(KategoriProduk t) {
		KategoriProdukData kategoriProdukData = new KategoriProdukData();
		kategoriProdukData.setId(t.getId());
		kategoriProdukData.setNama(t.getNama());
		
		return kategoriProdukData;
	}
	
	private KategoriProduk convertKategoriProdukDataToKategoriProduk(KategoriProdukData d) {
		return new KategoriProduk(d.getId(), d.getNama());
	}
	
}
