package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriProduk;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.Permohonan;
import org.Sikoling.ejb.abstraction.entity.Produk;
import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.ejb.abstraction.repository.IPermohonanRepository;

public class ServicePermohonan implements IServicePermohonan {
	
	private final IPermohonanRepository permohonanRepository;

	public ServicePermohonan(IPermohonanRepository permohonanRepository) {
		this.permohonanRepository = permohonanRepository;
	}
	
	@Override
	public Permohonan save(Permohonan permohonan) {
		return permohonanRepository.save(permohonan);
	}

	@Override
	public Permohonan update(Permohonan permohonan) {
		return null;
	}

	@Override
	public List<Permohonan> getAll(Integer page, Integer pageSize) {
		return null;
	}

	@Override
	public List<Permohonan> getByUser(User user) {
		return null;
	}

	@Override
	public List<Permohonan> getByPemrakarsa(Perusahaan pemrakarsa) {		return null;
	}

	@Override
	public List<Permohonan> getByProduk(Produk produk) {		
		return null;
	}

	@Override
	public List<Permohonan> getByKategoriProduk(KategoriProduk kategoriProduk) {		
		return null;
	}

	@Override
	public List<Permohonan> getByUserAndProduk(User user, Produk produk) {		
		return null;
	}

	@Override
	public List<Permohonan> getByUserAndKategoriProduk(User user, KategoriProduk kategoriProduk) {		
		return null;
	}

	@Override
	public List<Permohonan> getByUserAndPemrakarsa(User user, Perusahaan pemrakarsa) {		
		return null;
	}

	@Override
	public List<Permohonan> getByUserAndProdukAndPemrakarsa(User user, Produk produk, Perusahaan pemrakarsa) {		
		return null;
	}

	@Override
	public List<Permohonan> getByUserAndKategoriProdukAndPemrakarsa(User user, KategoriProduk kategoriProduk,
			Perusahaan pemrakarsa) {		
		return null;
	}

}
