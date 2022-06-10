package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.PaketPermohonan;

public interface IKategoriPaketRepository extends IRepository<PaketPermohonan> {
	List<PaketPermohonan> getAll(Integer page, Integer pageSize);
}
