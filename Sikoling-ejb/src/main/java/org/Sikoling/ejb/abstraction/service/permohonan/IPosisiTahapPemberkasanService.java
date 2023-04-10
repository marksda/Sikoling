package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;

public interface IPosisiTahapPemberkasanService {
	DeleteResponse delete(String id);
	PosisiTahapPemberkasan save(PosisiTahapPemberkasan t);
	PosisiTahapPemberkasan update(PosisiTahapPemberkasan t);
	List<PosisiTahapPemberkasan> getAll();
}
