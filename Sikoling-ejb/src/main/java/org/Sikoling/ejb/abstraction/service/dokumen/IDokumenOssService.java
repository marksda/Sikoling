package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.DokumenOss;

public interface IDokumenOssService {
	DokumenOss save(DokumenOss dokumenOss, String s);
	DokumenOss update(DokumenOss dokumenOss, String s);	
	DeleteResponse delete(String nib);
	DokumenOss updateById(String nib, DokumenOss dokumenOss, String s);
	List<DokumenOss> getAll();
	List<DokumenOss> getAllByPage(Integer page, Integer pageSize);
	List<DokumenOss> getByNib(String nib);
	List<DokumenOss> getByNibAndPage(String nib, Integer page, Integer pageSize);
	
}
