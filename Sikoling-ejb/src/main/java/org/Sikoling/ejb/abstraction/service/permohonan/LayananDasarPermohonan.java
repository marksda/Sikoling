package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriProduk;
import org.Sikoling.ejb.abstraction.entity.Pemrakarsa;
import org.Sikoling.ejb.abstraction.entity.Permohonan;
import org.Sikoling.ejb.abstraction.entity.Produk;
import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.ejb.abstraction.repository.PermohonanRepository;

public class LayananDasarPermohonan implements LayananPermohonan {
	private final PermohonanRepository permohonanRepository;

	public LayananDasarPermohonan(PermohonanRepository permohonanRepository) {
		this.permohonanRepository = permohonanRepository;
	}
	
	@Override
	public Permohonan save(Permohonan permohonan) {
		return permohonanRepository.save(permohonan);
	}

	@Override
	public Permohonan update(Permohonan permohonan) {
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
