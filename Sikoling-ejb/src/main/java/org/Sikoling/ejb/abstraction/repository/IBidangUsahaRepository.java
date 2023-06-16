package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.BidangUsaha;

public interface IBidangUsahaRepository extends IRepository<BidangUsaha> {
	BidangUsaha updateId(String idLama, BidangUsaha t) throws IOException;
}
