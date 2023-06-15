package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;

import org.Sikoling.ejb.abstraction.entity.AutorityPerusahaan;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;

public interface IRegisterPerusahaanRepository extends IRepository<RegisterPerusahaan> {
//	Boolean cekById(String id);
	RegisterPerusahaan deleteLinkKepemilikanPerusahaan(AutorityPerusahaan autorityPerusahaan) throws IOException;
	RegisterPerusahaan addLinkKepemilanPerusahaan(AutorityPerusahaan autorityPerusahaan) throws IOException;
}