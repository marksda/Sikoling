package org.Sikoling.ejb.abstraction.service.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IPelakuUsahaServices {
	List<PelakuUsaha> getALL();
	PelakuUsaha save(PelakuUsaha pelakuUsaha);
	PelakuUsaha update(PelakuUsaha pelakuUsaha);
	PelakuUsaha updateById(String id, PelakuUsaha pelakuUsaha);
	DeleteResponse delete(String id);
//	List<PelakuUsaha> getAllByPage(Integer page, Integer pageSize);
//	List<PelakuUsaha> getByNama(String nama);
//	List<PelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);
//	List<PelakuUsaha> getByKategoriPelakuUsaha(KategoriPelakuUsaha kategoriPelakuUsaha);
//	List<PelakuUsaha> getByKategoriPelakuUsahaAndPage(KategoriPelakuUsaha kategoriPelakuUsaha, Integer page, Integer pageSize);
	List<PelakuUsaha> getDaftarPelakuUsaha(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
