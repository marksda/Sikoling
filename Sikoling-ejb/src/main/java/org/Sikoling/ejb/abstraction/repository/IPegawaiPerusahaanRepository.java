package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.Pegawai;

public interface IPegawaiPerusahaanRepository extends IRepository<Pegawai> {
	Pegawai updateId(String idLama, Pegawai t) throws IOException;
}
