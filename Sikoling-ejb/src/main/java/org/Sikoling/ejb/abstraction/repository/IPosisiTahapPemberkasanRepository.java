package org.Sikoling.ejb.abstraction.repository;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;

public interface IPosisiTahapPemberkasanRepository extends IRepository<PosisiTahapPemberkasan> {
	DeleteResponse delete(String id);
}
