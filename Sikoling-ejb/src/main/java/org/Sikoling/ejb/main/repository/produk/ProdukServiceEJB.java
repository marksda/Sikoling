package org.Sikoling.ejb.main.repository.produk;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Produk;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IProdukRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class ProdukServiceEJB implements IProdukRepository {
	
	@Inject
	private ProdukRepositoryJPA produkRepository;

	@Override
	public Produk save(Produk t) throws IOException {
		return produkRepository.save(t);
	}

	@Override
	public Produk update(Produk t) {
		return produkRepository.update(t);
	}

	@Override
	public Produk delete(Produk t) throws IOException {
		return produkRepository.delete(t);
	}

	@Override
	public List<Produk> getDaftarData(QueryParamFilters queryParamFilters) {
		return produkRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return produkRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public Produk updateId(String idLama, Produk t) throws IOException {
		return produkRepository.updateId(idLama, t);
	}

}
