package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;

public interface IPosisiTahapPemberkasanRepository extends IRepository<PosisiTahapPemberkasan> {
	PosisiTahapPemberkasan updateId(String idLama, PosisiTahapPemberkasan t) throws IOException;
}
