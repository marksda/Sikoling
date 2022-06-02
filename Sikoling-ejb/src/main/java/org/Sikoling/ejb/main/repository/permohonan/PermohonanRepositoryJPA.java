package org.Sikoling.ejb.main.repository.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriProduk;
import org.Sikoling.ejb.abstraction.entity.Pemrakarsa;
import org.Sikoling.ejb.abstraction.entity.Permohonan;
import org.Sikoling.ejb.abstraction.entity.Produk;
import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.ejb.abstraction.repository.PermohonanRepository;
import jakarta.persistence.EntityManager;

public class PermohonanRepositoryJPA implements PermohonanRepository {
	private final EntityManager entityManager;	

	public PermohonanRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Permohonan> getAll() {		
//		return entityManager.createNamedQuery("PermohonanData.findAll", PermohonanData.class)
//				.getResultList()
//				.stream()
		return null;
	}
				

	@Override
	public Permohonan save(Permohonan t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permohonan update(Permohonan t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permohonan> getAll(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permohonan> getByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permohonan> getByPemrakarsa(Pemrakarsa pemrakarsa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permohonan> getByProduk(Produk produk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permohonan> getByKategoriProduk(KategoriProduk kategoriProduk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permohonan> getByUserAndProduk(User user, Produk produk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permohonan> getByUserAndKategoriProduk(User user, KategoriProduk kategoriProduk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permohonan> getByUserAndPemrakarsa(User user, Pemrakarsa pemrakarsa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permohonan> getByUserAndProdukAndPemrakarsa(User user, Produk produk, Pemrakarsa pemrakarsa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permohonan> getByUserAndKategoriProdukAndPemrakarsa(User user, KategoriProduk kategoriProduk,
			Pemrakarsa pemrakarsa) {
		// TODO Auto-generated method stub
		return null;
	}

}
