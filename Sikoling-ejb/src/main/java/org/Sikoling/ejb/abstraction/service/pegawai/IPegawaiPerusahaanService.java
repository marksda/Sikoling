package org.Sikoling.ejb.abstraction.service.pegawai;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Pegawai;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IPegawaiPerusahaanService {
	Pegawai save(Pegawai t) throws IOException;
	Pegawai update(Pegawai t);
	Pegawai updateId(String idLama, Pegawai t) throws IOException;
	Pegawai delete(Pegawai t) throws IOException;
	List<Pegawai> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
