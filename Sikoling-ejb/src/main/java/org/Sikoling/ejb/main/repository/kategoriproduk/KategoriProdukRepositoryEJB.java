package org.Sikoling.ejb.main.repository.kategoriproduk;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.KategoriProduk;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IKategoriProdukRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class KategoriProdukRepositoryEJB implements IKategoriProdukRepository {
	
	@Inject
	private KategoriProdukRepositoryJPA kategoriProdukRepository;

	@Override
	public KategoriProduk save(KategoriProduk t) throws IOException {
		return kategoriProdukRepository.save(t);
	}

	@Override
	public KategoriProduk update(KategoriProduk t) {
		return kategoriProdukRepository.update(t);
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

	@Override
	public KategoriProduk updateId(String idLama, KategoriProduk t) throws IOException {
		return kategoriProdukRepository.updateId(idLama, t);
	}

}
