package org.Sikoling.ejb.main.repository.produk;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Produk;
import org.Sikoling.ejb.abstraction.repository.IProdukRepository;
import org.Sikoling.ejb.main.repository.kategoriproduk.KategoriProdukData;

import jakarta.persistence.EntityManager;

public class ProdukRepositoryJPA implements IProdukRepository {
	
	private final EntityManager entityManager;

	public ProdukRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<Produk> getAll() {
		return entityManager.createNamedQuery("ProdukData.findAll", ProdukData.class)
				.getResultList()
				.stream()
				.map(d -> convertProdukDataToProduk(d))
				.collect(Collectors.toList());
	}

	@Override
	public Produk save(Produk t) {
		ProdukData d = convertProdukToProdukData(t);
		entityManager.persist(d);
		entityManager.flush();
		return convertProdukDataToProduk(d);
	}

	@Override
	public Produk update(Produk t) {
		ProdukData d = convertProdukToProdukData(t);
		d = entityManager.merge(d);
		return convertProdukDataToProduk(d);
	}

	@Override
	public List<Produk> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("ProdukData.findAll", ProdukData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> convertProdukDataToProduk(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<Produk> getByQueryNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("ProdukData.findByNama", ProdukData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(d -> convertProdukDataToProduk(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<Produk> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("ProdukData.findByNama", ProdukData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> convertProdukDataToProduk(d))
				.collect(Collectors.toList());
	}

	private Produk convertProdukDataToProduk(ProdukData d) {
		return new Produk(d.getId(), d.getNama(), d.getKategoriProduk().getId());
	}
	
	private ProdukData convertProdukToProdukData(Produk t) {
		ProdukData d = new ProdukData();
		d.setId(t.getId());
		d.setNama(t.getNama());
		KategoriProdukData kategoriProdukData = new KategoriProdukData();
		kategoriProdukData.setId(t.getIdKategoriProduk());
		
		return d;
	}
}
