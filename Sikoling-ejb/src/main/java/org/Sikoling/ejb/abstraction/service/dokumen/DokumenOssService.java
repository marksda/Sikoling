package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.DokumenOss;
import org.Sikoling.ejb.abstraction.repository.IDokumenOssRepository;

public class DokumenOssService implements IDokumenOssService {
	
	private final IDokumenOssRepository dokumenOssRepository;

	public DokumenOssService(IDokumenOssRepository dokumenOssRepository) {
		this.dokumenOssRepository = dokumenOssRepository;
	}

	@Override
	public DokumenOss save(DokumenOss dokumenOss, String s) {
		return dokumenOssRepository.save(dokumenOss, s);
	}

	@Override
	public DokumenOss update(DokumenOss dokumenOss, String s) {
		return dokumenOssRepository.update(dokumenOss, s);
	}

	@Override
	public DeleteResponse delete(String nib) {
		return dokumenOssRepository.delete(nib);
	}

	@Override
	public DokumenOss updateById(String nib, DokumenOss dokumenOss, String s) {
		return dokumenOssRepository.updateById(nib, dokumenOss, s);
	}

	@Override
	public List<DokumenOss> getAll() {
		return dokumenOssRepository.getAll();
	}

	@Override
	public List<DokumenOss> getAllByPage(Integer page, Integer pageSize) {
		return dokumenOssRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<DokumenOss> getByNib(String nib) {
		return dokumenOssRepository.getByNib(nib);
	}

	@Override
	public List<DokumenOss> getByNibAndPage(String nib, Integer page, Integer pageSize) {
		return dokumenOssRepository.getByNibAndPage(nib, page, pageSize);
	}

}
