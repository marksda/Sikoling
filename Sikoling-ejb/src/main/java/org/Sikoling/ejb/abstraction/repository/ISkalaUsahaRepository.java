package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;

import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;

public interface ISkalaUsahaRepository extends IRepository<SkalaUsaha> {
	SkalaUsaha updateId(String idLama, SkalaUsaha t) throws IOException;
}