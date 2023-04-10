package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;
import org.Sikoling.ejb.abstraction.repository.IPosisiTahapPemberkasanRepository;

public class PosisiTahapPemberkasanService implements IPosisiTahapPemberkasanService {
	
	private final IPosisiTahapPemberkasanRepository posisiTahapPemberkasanRepository;

	public PosisiTahapPemberkasanService(IPosisiTahapPemberkasanRepository posisiTahapPemberkasanRepository) {
		this.posisiTahapPemberkasanRepository = posisiTahapPemberkasanRepository;
	}

	@Override
	public DeleteResponse delete(String id) {
		return posisiTahapPemberkasanRepository.delete(id);
	}

	@Override
	public PosisiTahapPemberkasan save(PosisiTahapPemberkasan t) {
		return posisiTahapPemberkasanRepository.save(t);
	}

	@Override
	public PosisiTahapPemberkasan update(PosisiTahapPemberkasan t) {
		return posisiTahapPemberkasanRepository.update(t);
	}

	@Override
	public List<PosisiTahapPemberkasan> getAll() {
		return posisiTahapPemberkasanRepository.getAll();
	}

}
