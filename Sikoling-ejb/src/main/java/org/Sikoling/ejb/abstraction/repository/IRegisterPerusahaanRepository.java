package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;

import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;

public interface IRegisterPerusahaanRepository extends IRepository<RegisterPerusahaan> {
	RegisterPerusahaan updateId(String idLama, RegisterPerusahaan t) throws IOException;
//	Boolean cekById(String id);
//	RegisterPerusahaan deleteLinkKepemilikanPerusahaan(OtoritasPerusahaan autorityPerusahaan) throws IOException;
//	RegisterPerusahaan addLinkKepemilanPerusahaan(OtoritasPerusahaan autorityPerusahaan) throws IOException;
}