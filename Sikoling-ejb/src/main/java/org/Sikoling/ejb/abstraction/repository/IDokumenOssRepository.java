package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.DokumenOss;

public interface IDokumenOssRepository extends IRepository2<DokumenOss, String> {
	DeleteResponse delete(String nib);
	DokumenOss updateById(String nib, DokumenOss dokumenOss, String s);
	List<DokumenOss> getAllByPage(Integer page, Integer pageSize);
	List<DokumenOss> getByNib(String nib);
	List<DokumenOss> getByNibAndPage(String nib, Integer page, Integer pageSize);
}
