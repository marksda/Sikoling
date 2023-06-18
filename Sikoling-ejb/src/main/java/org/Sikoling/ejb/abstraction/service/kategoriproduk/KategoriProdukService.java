package org.Sikoling.ejb.abstraction.service.kategoriproduk;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.KategoriProduk;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IKategoriProdukRepository;

public class KategoriProdukService implements IKategoriProdukService {
	
	private final IKategoriProdukRepository kategoriProdukRepository;	

	public KategoriProdukService(IKategoriProdukRepository kategoriProdukRepository) {
		this.kategoriProdukRepository = kategoriProdukRepository;
	}

	@Override
	public KategoriProduk save(KategoriProduk t) throws IOException {
		return kategoriProdukRepository.save(t);
	}

	@Override
	public KategoriProduk update(KategoriProduk t) {
		return kategoriProdukRepository.update(t);
	}

	@Override
	public KategoriProduk updateId(String idLama, KategoriProduk t) throws IOException {
		return kategoriProdukRepository.updateId(idLama, t);
	}

	@Override
	public KategoriProduk delete(KategoriProduk t) throws IOException {
		return kategoriProdukRepository.delete(t);
	}

	@Override
	public List<KategoriProduk> getDaftarData(QueryParamFilters queryParamFilters) {
		return kategoriProdukRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return kategoriProdukRepository.getJumlahData(queryParamFilters);
	}
	
}
