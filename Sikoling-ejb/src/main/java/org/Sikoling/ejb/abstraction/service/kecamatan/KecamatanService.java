package org.Sikoling.ejb.abstraction.service.kecamatan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.repository.IKecamatanRepository;

public class KecamatanService implements IKecamatanService {
	
	private final IKecamatanRepository kecamatanRepository;	

	public KecamatanService(IKecamatanRepository kecamatanRepository) {
		this.kecamatanRepository = kecamatanRepository;
	}

	@Override
	public Kecamatan save(Kecamatan kecamatan, String idKabupaten) {
		return kecamatanRepository.save(kecamatan, idKabupaten);
	}

	@Override
	public Kecamatan update(Kecamatan kecamatan, String idKabupaten) {
		return kecamatanRepository.update(kecamatan, idKabupaten);
	}

	@Override
	public List<Kecamatan> getAll() {
		return kecamatanRepository.getAll();
	}
	
	@Override
	public List<Kecamatan> getAllByPage(Integer page, Integer pageSize) {
		return kecamatanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Kecamatan> getByNama(String nama) {
		return kecamatanRepository.getByNama(nama);
	}

	@Override
	public List<Kecamatan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return kecamatanRepository.getByNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<Kecamatan> getByKabupaten(String idKabupaten) {
		return kecamatanRepository.getByKabupaten(idKabupaten);
	}

	@Override
	public List<Kecamatan> getByKabupatenAndPage(String idKabupaten, Integer page, Integer pageSize) {
		return kecamatanRepository.getByKabupatenAndPage(idKabupaten, page, pageSize);
	}

	@Override
	public List<Kecamatan> getByKabupatenAndNama(String idKabupaten, String nama) {
		return kecamatanRepository.getByKabupatenAndNama(idKabupaten, nama);
	}

	@Override
	public List<Kecamatan> getByKabupatenAndNamaAndPage(String idKabupaten, String nama, Integer page,
			Integer pageSize) {
		return kecamatanRepository.getByKabupatenAndNamaAndPage(idKabupaten, nama, page, pageSize);
	}

	
}
