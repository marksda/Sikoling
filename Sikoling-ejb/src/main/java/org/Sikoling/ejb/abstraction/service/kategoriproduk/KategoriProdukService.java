package org.Sikoling.ejb.abstraction.service.kategoriproduk;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriProduk;
import org.Sikoling.ejb.abstraction.repository.IKategoriProdukRepository;

public class KategoriProdukService implements IKategoriProdukService {
	
	private final IKategoriProdukRepository kategoriProdukRepository;	

	public KategoriProdukService(IKategoriProdukRepository kategoriProdukRepository) {
		super();
		this.kategoriProdukRepository = kategoriProdukRepository;
	}

	@Override
	public KategoriProduk save(KategoriProduk t) {
		return kategoriProdukRepository.save(t);
	}

	@Override
	public KategoriProduk update(KategoriProduk t) {
		return kategoriProdukRepository.update(t);
	}

	@Override
	public List<KategoriProduk> getAllByPage(Integer page, Integer pageSize) {
		return kategoriProdukRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<KategoriProduk> getByQueryNama(String nama) {
		return kategoriProdukRepository.getByQueryNama(nama);
	}

	@Override
	public List<KategoriProduk> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return kategoriProdukRepository.getByQueryNamaAndPage(nama, page, pageSize);
	}

	
	@Override
	public List<KategoriProduk> getAll() {
		return kategoriProdukRepository.getAll();
	}

}
